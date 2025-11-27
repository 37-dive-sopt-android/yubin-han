package com.sopt.dive.domain.model

data class ProfileSummary(
    val username: String,
    val statusMessage: String,
    val profileImageResId: Int
)

data class UserModel(
    val id: Long,
    val username: String,
    val name: String,
    val email: String,
    val age: Int,
    val profileImageUrl: String?
)
