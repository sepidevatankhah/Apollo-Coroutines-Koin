package ir.nwise.app.domain.usecase

import ir.nwise.app.data.repository.AppRepository
import ir.nwise.app.domain.models.PostResponse
import ir.nwise.app.domain.usecase.base.UseCase

class GetAllPostUseCase(private val appRepository: AppRepository) :
    UseCase<Any, List<PostResponse>>() {
    override suspend fun executeOnBackground(param: Any?): List<PostResponse> {
        return appRepository.getAllPosts()
    }
}