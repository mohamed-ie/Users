package com.users.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mohammedie.desgin_system.theme.UsersTheme
import com.users.core.data.utils.NetworkMonitor
import com.users.app.ui.UsersApp
import com.users.app.ui.rememberUsersAppState
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val networkMonitor by inject<NetworkMonitor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberUsersAppState(networkMonitor = networkMonitor)
            UsersTheme {
                UsersApp(appState = appState)
            }
        }
    }
}