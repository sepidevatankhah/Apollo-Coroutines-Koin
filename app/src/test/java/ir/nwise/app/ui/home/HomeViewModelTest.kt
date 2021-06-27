package ir.nwise.app.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.whenever
import ir.nwise.app.utils.captureEmittedData
import ir.nwise.app.data.repository.AppRepository
import ir.nwise.app.domain.models.PostResponse
import ir.nwise.app.domain.models.UserResponse
import ir.nwise.app.domain.usecase.GetAllPostUseCase
import ir.nwise.app.utils.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    private lateinit var testViewModel: HomeViewModel

    private lateinit var getAllPostUseCase: GetAllPostUseCase

    @Mock
    private lateinit var appRepository: AppRepository

    @Before
    fun setUp() {
        getAllPostUseCase = GetAllPostUseCase(
            appRepository = appRepository,
            coroutineScope = TestCoroutineScope(testCoroutineDispatcher),
            dispatchers = coroutinesTestRule.testDispatcherProvider
        )
        testViewModel = HomeViewModel(getAllPostUseCase)
    }

    @Test
    fun `#getAllPost() must emit #Loading and #Loaded ViewStates`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val postList = listOf(
                PostResponse(
                    id = "1",
                    title = "title",
                    body = "body",
                    user = UserResponse(
                        id = "1",
                        name = "Sepideh Vatankhah",
                        username = "sun.vatankhah",
                    )
                )
            )
            //given
            val observer = testViewModel.liveData.captureEmittedData()
            whenever(appRepository.getAllPosts()).thenAnswer {
                postList
            }

            //when
            testViewModel.getAllPost()

            //Then
            Assert.assertNotNull(testViewModel.liveData.value)
            Assert.assertEquals(
                listOf(HomeViewState.Loading, HomeViewState.Loaded(postList)),
                observer.invoke()
            )
        }


    @Test
    fun `when fetching results fails then return an error and must emit #Loading and #Error ViewStates`() {
        val exception = Mockito.mock(HttpException::class.java)
        coroutinesTestRule.testDispatcher.runBlockingTest {
            //given
            val observer = testViewModel.liveData.captureEmittedData()
            whenever(appRepository.getAllPosts()).thenAnswer {
                throw (exception)
            }

            //when
            testViewModel.getAllPost()

            //Then
            Assert.assertNotNull(testViewModel.liveData.value)
            Assert.assertEquals(
                listOf(HomeViewState.Loading,HomeViewState.Error(exception)),
                observer.invoke()
            )
        }
    }
}