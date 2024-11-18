package com.users.feature.users

import androidx.annotation.StringRes
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.users.core.model.Address
import com.users.core.model.Company
import com.users.core.model.Geolocation
import com.users.core.model.User
import kotlin.random.Random

internal sealed interface UsersUiState {
    data object Loading : UsersUiState
    data object LoadFailed : UsersUiState
    data class Success(val users: List<User>) : UsersUiState
}

internal sealed interface UsersUiEvent {
    data class ShowSnackbar(@StringRes val messageId: Int) : UsersUiEvent
}

internal class UsersPreviewParameterProvider : PreviewParameterProvider<UsersUiState> {
    override val values: Sequence<UsersUiState>
        get() = sequenceOf(
            UsersUiState.Loading,
            UsersUiState.LoadFailed,
            UsersUiState.Success(
                users = List(50) {
                    User(
                        id = it.toLong(),
                        name = "User $it",
                        username = "Username $it",
                        email = "user$it@example.com",
                        isLiked = Random.nextBoolean(),
                        address = Address(
                            street = "Address $it",
                            suite = "Suite $it",
                            city = "City $it",
                            zipcode = "$it",
                            geolocation = Geolocation(
                                latitude = Random.nextDouble().times(it),
                                longitude = Random.nextDouble().times(it)
                            )
                        ),
                        phone = "(554) 621-2066",
                        website = "Website $it",
                        company = Company(
                            name = "Company $it",
                            catchPhrase = "Catch phrase $it",
                            businessStuff = "Business stuff $it"
                        )
                    )
                }
            ),
            UsersUiState.Success(emptyList())
        )
}