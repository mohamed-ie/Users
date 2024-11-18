package com.users.feature.likes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.users.feature.likes.LikesUiEvent.ShowSnackbar
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun LikesRoute(
    viewModel: LikesViewModel = koinViewModel(),
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

    LikesScreen(
        uiState = uiState,
        onLikeClick = viewModel::dislike
    )
}