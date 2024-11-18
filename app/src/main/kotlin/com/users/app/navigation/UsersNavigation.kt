package com.users.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.users.app.ui.UsersAppState
import com.users.feature.likes.navigation.likesScreen
import com.users.feature.users.navigation.UsersNavigation
import com.users.feature.users.navigation.usersScreen
import kotlinx.serialization.Serializable

@Serializable
data object UsersNavHost

@Composable
fun UsersNavigation(
    appState: UsersAppState,
    modifier: Modifier = Modifier,
    onShowSnackbar: suspend (String) -> Unit
) {
    val navController = appState.navController

    NavHost(
        modifier = modifier,
        route = UsersNavHost::class,
        navController = navController,
        startDestination = UsersNavigation::class
    ) {
        usersScreen(onShowSnackbar = onShowSnackbar)
        likesScreen(onShowSnackbar = onShowSnackbar)
    }
}