package com.users.feature.users

import com.users.core.data.repository.UserRepository
import com.users.core.model.User
import com.users.core.test.MainDispatcherRule
import com.users.core.test.model.user
import com.users.core.test.model.users
import com.users.core.ui.R
import com.users.feature.users.UsersUiEvent.ShowSnackbar
import com.users.feature.users.UsersUiState.LoadFailed
import com.users.feature.users.UsersUiState.Loading
import com.users.feature.users.UsersUiState.Success
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success
import kotlin.test.assertIs

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UsersViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val testScope = TestScope(UnconfinedTestDispatcher())

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var viewModel: UsersViewModel

    private val usersFlow = MutableSharedFlow<Result<List<User>>>()

    @Before
    fun setup() {
        whenever(userRepository.users()).thenReturn(usersFlow)
        viewModel = UsersViewModel(userRepository, testScope)
        testScope.launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
    }

    @After
    fun tearDown() = testScope.cancel()

    @Test
    fun `observe uiState retrieve users from repository`() = runTest {
        assertIs<Loading>(viewModel.uiState.value)

        val expectedUsers = users(5)
        usersFlow.emit(success(expectedUsers))

        val uiState = assertIs<Success>(viewModel.uiState.value)

        val actual = uiState.users

        assertEquals(expectedUsers, actual)
    }

    @Test
    fun `reloadUsers reloads users from repository`() = runTest {
        usersFlow.emit(failure(Throwable()))
        assertIs<LoadFailed>(viewModel.uiState.value)

        whenever(userRepository.users()).thenReturn(flowOf(success(emptyList())))
        assertIs<LoadFailed>(viewModel.uiState.value)
        viewModel.reloadUsers()
        assertIs<Loading>(viewModel.uiState.value)
    }

    @Test
    fun `toggleLike calls repository like and updates users`() = runTest {
        val dislikedUser = user()
        val expected = listOf(dislikedUser.copy(isLiked = true))

        usersFlow.emit(success(users(1)))

        viewModel.toggleLike(dislikedUser)

        usersFlow.emit(success(expected))

        val uiState = assertIs<Success>(viewModel.uiState.value)

        val actual = uiState.users

        assertEquals(expected, actual)
    }


    @Test
    fun `toggleLike calls like or dislike emits uiEvent`() = runTest {
        val likedUser = user()
        val notLikedUser = likedUser.copy(isLiked = false)

        val uiEvents = mutableListOf<UsersUiEvent>()

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.uiEvent.toList(uiEvents)
        }

        viewModel.toggleLike(likedUser)
        verify(userRepository).dislike(likedUser.id)

        var expected = ShowSnackbar(R.string.core_ui_message_disliked)
        var actual = uiEvents.first()
        assertEquals(expected, actual)

        viewModel.toggleLike(notLikedUser)
        verify(userRepository).like(notLikedUser)

        expected = ShowSnackbar(R.string.core_ui_message_liked)
        actual = uiEvents[1]
        assertEquals(expected, actual)
    }
}