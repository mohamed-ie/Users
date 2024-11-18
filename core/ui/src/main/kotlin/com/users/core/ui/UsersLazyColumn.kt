package com.users.core.ui

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.users.core.model.User

@Composable
fun UsersLazyColumn(
    users: List<User>,
    modifier: Modifier = Modifier,
    onLikeClick: (User) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(items = users, key = User::id) { user ->
            UserListItem(
                user = user,
                onLikeClick = { onLikeClick(user) }
            )
        }

        if (users.isEmpty())
            item {
                Text(
                    modifier = Modifier
                        .fillParentMaxSize()
                        .wrapContentHeight(Alignment.CenterVertically),
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.core_ui_message_no_data)
                )
            }
    }
}