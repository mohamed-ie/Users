package com.users.feature.likes

import com.users.core.data.repository.UserRepository
import com.users.core.model.User
import com.users.core.test.MainDispatcherRule
import com.users.core.test.model.user
import com.users.core.test.model.users
import com.users.core.ui.R
import com.users.feature.likes.LikesUiState.Success
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertIs

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LikesViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val testScope = TestScope(UnconfinedTestDispatcher())

    @Mock
    private lateinit var userRepository: UserRepository

    private val usersFlow = MutableSharedFlow<List<User>>()

    private lateinit var viewModel: LikesViewModel

    @Before
    fun setup() {
        whenever(userRepository.likedUsers).thenReturn(usersFlow)
        viewModel = LikesViewModel(userRepository, testScope)
        testScope.launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
    }

    @After
    fun tearDown() = testScope.cancel()

    @Test
    fun `observe uiState listen to likedUsers from repository`() = runTest {
        val expectedUsers = users(5)

        usersFlow.emit(expectedUsers)

        val uiState = assertIs<Success>(viewModel.uiState.value)

        val actual = uiState.users

        assertEquals(expectedUsers, actual)
    }

    @Test
    fun `dislikeUser calls repository dislike and updates likedUsers`() = runTest {
        val dislikedUser = user()

        usersFlow.emit(users(1))

        viewModel.dislike(dislikedUser)
        verify(userRepository).dislike(dislikedUser.id)

        usersFlow.emit(emptyList())

        val uiState = assertIs<Success>(viewModel.uiState.value)

        assertTrue(uiState.users.isEmpty())
    }

    @Test
    fun `dislikeUser emits uiEvent`() = runTest {
        val user = user()

        val uiEvent = mutableListOf<LikesUiEvent>()

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.uiEvent.toList(uiEvent)
        }

        viewModel.dislike(user)

        val expected = LikesUiEvent.ShowSnackbar(R.string.core_ui_message_disliked)

        val actual = uiEvent.first()

        assertEquals(expected, actual)
    }
}