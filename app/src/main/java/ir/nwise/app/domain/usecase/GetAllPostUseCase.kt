package ir.nwise.app.domain.usecase

import ir.nwise.app.data.DefaultDispatcherProvider
import ir.nwise.app.data.DispatcherProvider
import ir.nwise.app.data.repository.AppRepository
import ir.nwise.app.domain.models.PostResponse
import ir.nwise.app.domain.usecase.base.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class GetAllPostUseCase(
    private val appRepository: AppRepository,
    coroutineScope: CoroutineScope = MainScope(),
    dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) : UseCase<Any, List<PostResponse>>(coroutineScope, dispatchers) {
    override suspend fun executeOnBackground(param: Any?): List<PostResponse> {
        return appRepository.getAllPosts()
    }
}