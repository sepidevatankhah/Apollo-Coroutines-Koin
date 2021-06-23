package ir.nwise.app.data.repository

import ir.nwise.app.domain.models.PostResponse

interface AppRepository {
    suspend fun getAllPosts(): List<PostResponse>
}