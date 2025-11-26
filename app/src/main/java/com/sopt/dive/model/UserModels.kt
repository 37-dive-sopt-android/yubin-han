package com.sopt.dive.model

data class ProfileSummary(
    val nickname: String,
    val statusMessage: String,
    val profileImageResId: Int
)

data class FeedItem(
    val userNickname: String,
    val content: String,
    val profileImageResId: Int
)

