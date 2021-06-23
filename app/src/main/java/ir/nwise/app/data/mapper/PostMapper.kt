package ir.nwise.app.data.mapper

import GetAllPostsQuery
import ir.nwise.app.domain.models.PostResponse
import ir.nwise.app.domain.models.UserResponse

fun GetAllPostsQuery.Data1.toDomainModel(): PostResponse {
    return PostResponse(
        id = this.id,
        title = this.title,
        body = this.body,
        user = this.user?.let { mapUser(it) }
    )
}

fun mapUser(user: GetAllPostsQuery.User) =
    UserResponse(
        id = user.id,
        name = user.name,
        username = user.username,
        phone = user.phone,
        email = user.email
    )