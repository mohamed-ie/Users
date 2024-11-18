package com.users.app.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.users.app.R
import com.users.app.navigation.BottomBarDestination

@Composable
fun AppBottomBar(appState: UsersAppState) {
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()

    val insets = BottomAppBarDefaults.windowInsets.only(WindowInsetsSides.Bottom)

    Column(Modifier.consumeWindowInsets(insets)) {
        BottomBar(appState = appState)

        InternetConnectionStatus(isOffline = isOffline)

        Box(
            modifier = Modifier
                .background(NavigationBarDefaults.containerColor)
                .fillMaxWidth()
                .padding(NavigationBarDefaults.windowInsets.asPaddingValues())
        )
    }
}

@Composable
private fun InternetConnectionStatus(
    isOffline: Boolean,
    modifier: Modifier = Modifier
) {
    val color by animateColorAsState(
        targetValue = if (isOffline) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.primaryContainer,
        label = "internet connection container color"
    )

    val contentColor by animateColorAsState(
        targetValue = if (isOffline) MaterialTheme.colorScheme.onErrorContainer else MaterialTheme.colorScheme.onPrimaryContainer,
        label = "internet connection content color"
    )

    val text = stringResource(if (isOffline) R.string.text_offline else R.string.text_back_online)

    AnimatedVisibility(
        modifier = modifier,
        visible = isOffline,
        enter = expandVertically(expandFrom = Alignment.Bottom),
        exit = shrinkVertically(
            shrinkTowards = Alignment.Bottom,
            animationSpec = tween(delayMillis = 1000)
        )
    ) {
        Surface(
            color = color,
            contentColor = contentColor
        ) {
            Crossfade(targetState = text, label = "internet connection text") {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    text = it,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun BottomBar(appState: UsersAppState) {
    val currentDestination = appState.currentDestination

    NavigationBar {
        BottomBarDestination.entries.forEach { destination ->
            val selected = currentDestination?.isBottomDestinationInHierarchy(destination) == true

            NavigationBarItem(
                selected = true,
                onClick = { appState.navigateToBottomBarDestination(destination.route) },
                icon = {
                    Crossfade(targetState = selected, label = "bottom bar icon") {
                        if (it)
                            Icon(
                                imageVector = destination.selectedIcon,
                                contentDescription = null
                            )
                        else
                            Icon(
                                imageVector = destination.unselectedIcon,
                                contentDescription = null
                            )
                    }
                },
                label = { Text(text = stringResource(id = destination.textId)) }
            )
        }
    }
}

private fun NavDestination?.isBottomDestinationInHierarchy(destination: BottomBarDestination) =
    this?.hierarchy?.any { it.hasRoute(destination.route::class) } ?: false