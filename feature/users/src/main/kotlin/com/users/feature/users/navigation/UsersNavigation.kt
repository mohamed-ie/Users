package com.users.feature.users.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.users.feature.users.UsersRoute
import kotlinx.serialization.Serializable

@Serializable
data object UsersNavigation

@Serializable
data object Users

fun NavGraphBuilder.usersScreen(
    onShowSnackbar: suspend (message: String) -> Unit
) = navigation<UsersNavigation>(startDestination = Users::class) {
    composable<Users> {
        UsersRoute(onShowSnackbar=onShowSnackbar)
    }
}