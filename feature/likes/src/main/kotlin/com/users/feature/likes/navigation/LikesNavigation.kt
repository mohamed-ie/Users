package com.users.feature.likes.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.users.feature.likes.LikesRoute
import kotlinx.serialization.Serializable

@Serializable
data object LikesNavigation

@Serializable
data object Likes

fun NavGraphBuilder.likesScreen(
    onShowSnackbar: suspend (message: String) -> Unit
) = navigation<LikesNavigation>(startDestination = Likes::class) {
    composable<Likes> {
        LikesRoute(onShowSnackbar=onShowSnackbar)
    }
}