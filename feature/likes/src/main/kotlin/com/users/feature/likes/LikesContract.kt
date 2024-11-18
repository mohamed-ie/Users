package com.users.feature.likes

import androidx.annotation.StringRes
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.users.core.model.Address
import com.users.core.model.Company
import com.users.core.model.Geolocation
import com.users.core.model.User
import kotlin.random.Random

internal sealed interface LikesUiState {
    data object Loading : LikesUiState
    data class Success(val users: List<User>) : LikesUiState
}

internal sealed interface LikesUiEvent {
    data class ShowSnackbar(@StringRes val messageId: Int) : LikesUiEvent
}

internal class LikesPreviewParameterProvider : PreviewParameterProvider<LikesUiState> {
    override val values: Sequence<LikesUiState>
        get() = sequenceOf(
            LikesUiState.Loading,
            LikesUiState.Success(
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
            LikesUiState.Success(emptyList())
        )
}