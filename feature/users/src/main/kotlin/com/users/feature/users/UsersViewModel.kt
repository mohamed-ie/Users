package com.users.feature.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.users.core.common.di.ApplicationScope
import com.users.core.data.repository.UserRepository
import com.users.core.model.User
import com.users.core.ui.R
import com.users.feature.users.UsersUiEvent.ShowSnackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class UsersViewModel(
    private val userRepository: UserRepository,
    @ApplicationScope
    private val applicationScope: CoroutineScope
) : ViewModel() {
    private val _uiEvent = MutableSharedFlow<UsersUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val shouldReloadUsers = MutableSharedFlow<Unit>()

    val uiState = uiState(
        shouldReloadUsers = shouldReloadUsers,
        users = userRepository.users()
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UsersUiState.Loading
    )

    fun reloadUsers() {
        viewModelScope.launch {
            shouldReloadUsers.emit(Unit)
        }
    }

    fun toggleLike(user: User) {
        applicationScope.launch {
            if (user.isLiked)
                userRepository.dislike(user.id)
            else
                userRepository.like(user)

            val messageId = if (user.isLiked)
                R.string.core_ui_message_disliked
            else
                R.string.core_ui_message_liked

            _uiEvent.emit(ShowSnackbar(messageId))
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
private fun uiState(
    shouldReloadUsers: Flow<Unit>,
    users: Flow<Result<List<User>>>,
) = shouldReloadUsers
    .onStart { emit(Unit) }
    .flatMapLatest {
        users.mapLatest(Result<List<User>>::asUiState)
            .onStart { emit(UsersUiState.Loading) }
    }

private fun Result<List<User>>.asUiState() =
    map(UsersUiState::Success).getOrElse { UsersUiState.LoadFailed }