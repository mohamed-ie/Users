package com.users.app.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.ThumbUpAlt
import androidx.compose.material.icons.twotone.People
import androidx.compose.material.icons.twotone.ThumbUpAlt
import androidx.compose.ui.graphics.vector.ImageVector
import com.users.app.R
import com.users.feature.likes.navigation.LikesNavigation
import com.users.feature.users.navigation.UsersNavigation

enum class BottomBarDestination(
    val route: Any,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val textId: Int
) {
    USERS(
        route = UsersNavigation,
        selectedIcon = Icons.Filled.People,
        unselectedIcon = Icons.TwoTone.People,
        textId = R.string.bottom_bar_users
    ),
    LIKES(
        route = LikesNavigation,
        selectedIcon = Icons.Filled.ThumbUpAlt,
        unselectedIcon = Icons.TwoTone.ThumbUpAlt,
        textId = R.string.bottom_bar_likes
    )
}