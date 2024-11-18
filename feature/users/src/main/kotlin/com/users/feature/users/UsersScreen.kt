package com.users.feature.users

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.mohammedie.desgin_system.theme.UsersTheme
import com.users.core.model.User
import com.users.core.ui.ErrorState
import com.users.core.ui.LoadingState
import com.users.core.ui.R
import com.users.core.ui.UserListItem
import com.users.core.ui.UsersLazyColumn
import com.users.feature.users.UsersUiState.LoadFailed
import com.users.feature.users.UsersUiState.Loading
import com.users.feature.users.UsersUiState.Success

@Composable
internal fun UsersScreen(
    uiState: UsersUiState,
    onLikeClick: (User) -> Unit,
    onRetryClick: () -> Unit
) {
    AnimatedContent(
        targetState = uiState,
        label = "screen state",
        contentKey = { it::class.simpleName }
    ) {
        val modifier = Modifier.fillMaxSize()
        when (it) {
            Loading ->
                LoadingState(modifier = modifier)

            LoadFailed ->
                ErrorState(
                    modifier = modifier,
                    onRetryClick = onRetryClick
                )

            is Success -> UsersScreen(
                uiState = it,
                modifier = modifier,
                onLikeClick = onLikeClick
            )
        }
    }
}

@Composable
internal fun UsersScreen(
    uiState: Success,
    modifier: Modifier = Modifier,
    onLikeClick: (User) -> Unit
) {
    UsersLazyColumn(
        users = uiState.users,
        modifier = modifier,
        onLikeClick = onLikeClick
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview(
    @PreviewParameter(UsersPreviewParameterProvider::class) uiState: UsersUiState
) {
    UsersTheme {
        UsersScreen(
            uiState = uiState,
            onRetryClick = {},
            onLikeClick = {}
        )
    }
}