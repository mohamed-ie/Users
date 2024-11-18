package com.users.core.data.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.users.core.common.UsersDispatcher
import com.users.core.common.di.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Single

@Single
internal class ConnectivityManagerNetworkMonitor(
    context: Context,
    @Dispatcher(UsersDispatcher.IO) ioDispatcher: CoroutineDispatcher
) : NetworkMonitor {
    override val isOnline: Flow<Boolean> = callbackFlow {
        val connectivityManager = context.getSystemService(ConnectivityManager::class.java)
        if (connectivityManager == null) {
            trySend(false)
            close()
            return@callbackFlow
        }

        val networks = mutableSetOf<Network>()

        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                networks += network
                trySend(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                networks -= network
                trySend(networks.isNotEmpty())
            }
        }

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        //first time
        connectivityManager.activeNetwork
            ?.let(connectivityManager::getNetworkCapabilities)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .run { trySend(this ?: false) }

        connectivityManager.registerNetworkCallback(networkRequest, callback)

        awaitClose { connectivityManager.unregisterNetworkCallback(callback) }
    }
        .distinctUntilChanged()
        .conflate()
        .flowOn(ioDispatcher)
}