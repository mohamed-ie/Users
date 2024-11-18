package com.users.feature.likes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.users.core.common.di.ApplicationScope
import com.users.core.data.repository.UsersRepository
import com.users.core.model.User
import com.users.core.ui.R
import com.users.feature.likes.LikesUiEvent.ShowSnackbar
import com.users.feature.likes.LikesUiState.Loading
import com.users.feature.likes.LikesUiState.Success
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class LikesViewModel(
    private val usersRepository: UsersRepository,
    @ApplicationScope
    private val applicationScope: CoroutineScope
) : ViewModel() {
    private val _uiEvent = MutableSharedFlow<LikesUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    val uiState: StateFlow<LikesUiState> = usersRepository.likedUsers
        .map<List<User>, LikesUiState>(::Success)
        .onStart { emit(Loading) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Loading
        )

    fun dislike(user: User) {
        applicationScope.launch {
            usersRepository.dislike(user.id)
            _uiEvent.emit(ShowSnackbar(R.string.core_ui_message_disliked))
        }
    }
}