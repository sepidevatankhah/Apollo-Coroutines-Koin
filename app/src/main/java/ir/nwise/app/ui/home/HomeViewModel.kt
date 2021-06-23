package ir.nwise.app.ui.home

import ir.nwise.app.domain.models.PostResponse
import ir.nwise.app.domain.usecase.GetAllPostUseCase
import ir.nwise.app.domain.usecase.base.UseCaseResult
import ir.nwise.app.ui.base.BaseViewModel


class HomeViewModel(private val getAllPostUseCase: GetAllPostUseCase) :
    BaseViewModel<HomeViewState>() {
    fun getAllPost() {
        getAllPostUseCase.execute {
            when (this) {
                is UseCaseResult.Loading -> liveData.postValue(HomeViewState.Loading)
                is UseCaseResult.Success -> liveData.postValue(HomeViewState.Loaded(this.data))
                is UseCaseResult.Error -> liveData.postValue(HomeViewState.Error(this.exception))
            }
        }
    }
}

sealed class HomeViewState {
    object Loading : HomeViewState()
    data class Loaded(val albums: List<PostResponse>) : HomeViewState()
    data class Error(val throwable: Throwable) : HomeViewState()
}