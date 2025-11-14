package com.sopt.dive.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    @SerialName("userId")
    val userId: Long,
    @SerialName("message")
    val message: String
)
