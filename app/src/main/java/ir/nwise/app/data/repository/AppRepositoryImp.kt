package ir.nwise.app.data.repository

import GetAllPostsQuery
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import ir.nwise.app.data.mapper.toDomainModel
import ir.nwise.app.domain.models.PostResponse


class AppRepositoryImp(private val apolloClient: ApolloClient) : AppRepository {

    override suspend fun getAllPosts(): List<PostResponse> {
        val response = apolloClient.query(GetAllPostsQuery()).await()
        return response.data?.posts?.data?.mapNotNull { it?.toDomainModel() }
            ?: error("Missing data")
    }
}
