package com.sopt.dive.data

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

