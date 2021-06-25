package ir.nwise.app.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostResponse(
    val id: String?,
    val title: String?,
    val body: String?,
    val user: UserResponse?
) : Parcelable

@Parcelize
data class UserResponse(
    val id: String?,
    val name: String?= null,
    val username: String?= null
) : Parcelable

