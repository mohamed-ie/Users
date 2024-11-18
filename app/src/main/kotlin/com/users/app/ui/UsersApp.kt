package com.users.app.ui

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.users.app.navigation.UsersNavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun UsersApp(
    appState: UsersAppState,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    scope: CoroutineScope = rememberCoroutineScope()
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = { AppBottomBar(appState = appState) }
    ) { innerPadding ->
        UsersNavigation(
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding),
            appState = appState,
            onShowSnackbar = { scope.launch { snackbarHostState.showSnackbar(it) } }
        )
    }
}
