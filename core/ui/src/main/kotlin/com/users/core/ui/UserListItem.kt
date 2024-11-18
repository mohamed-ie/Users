package com.users.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUpAlt
import androidx.compose.material.icons.filled.ThumbUpOffAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mohammedie.desgin_system.theme.UsersTheme
import com.users.core.model.Address
import com.users.core.model.Company
import com.users.core.model.Geolocation
import com.users.core.model.User
import kotlin.random.Random

@Composable
fun UserListItem(
    user: User,
    modifier: Modifier = Modifier,
    onLikeClick: () -> Unit
) {
    var selectedUser by remember { mutableStateOf<User?>(null) }

    ListItem(
        modifier = modifier.clickable { selectedUser = user },
        headlineContent = { Text(text = user.name) },
        supportingContent = { Text(text = user.username) },
        overlineContent = { Text(text = user.email) },
        trailingContent = {
            IconButton(onClick = onLikeClick) {
                if (user.isLiked)
                    Icon(
                        imageVector = Icons.Filled.ThumbUpAlt,
                        contentDescription = stringResource(R.string.core_ui_content_description_like)
                    )
                else
                    Icon(
                        imageVector = Icons.Filled.ThumbUpOffAlt,
                        contentDescription = stringResource(R.string.core_ui_content_description_dislike)
                    )
            }
        }
    )

    selectedUser?.let {
        UserDetailsAlertDialog(user = it) { selectedUser = null }
    }
}

@Preview
@Composable
private fun Preview() {
    UsersTheme {
        UserListItem(
            user = User(
                id = 0,
                name = "User",
                username = "Username",
                email = "user@example.com",
                isLiked = Random.nextBoolean(),
                address = Address(
                    street = "Address",
                    suite = "Suite",
                    city = "City",
                    zipcode = "1325456",
                    geolocation = Geolocation(
                        latitude = Random.nextDouble().times(10),
                        longitude = Random.nextDouble().times(20)
                    )
                ),
                phone = "(554) 621-2066",
                website = "Website",
                company = Company(
                    name = "Company",
                    catchPhrase = "Catch phrase",
                    businessStuff = "Business stuff"
                )
            ),
            onLikeClick = { }
        )
    }
}