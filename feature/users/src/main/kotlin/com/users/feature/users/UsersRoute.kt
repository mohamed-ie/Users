package com.users.feature.users

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.users.feature.users.UsersUiEvent.ShowSnackbar
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun UsersRoute(
    viewModel: UsersViewModel = koinViewModel(),
    onShowSnackbar: suspend (message: String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is ShowSnackbar -> onShowSnackbar(context.getString(uiEvent.messageId))
            }
        }
    }

    UsersScreen(
        uiState = uiState,
        onRetryClick = viewModel::reloadUsers,
        onLikeClick = viewModel::toggleLike
    )
}