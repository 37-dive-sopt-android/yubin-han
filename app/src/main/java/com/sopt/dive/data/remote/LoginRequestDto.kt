package com.sopt.dive.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//직렬화해서 서버와 통신할 수 있게끔 함
@Serializable
data class LoginRequestDto(
    //서버에 있는 value와 맵핑
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
)

@Serializable
data class SignUpRequestDto(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("age")
    val age: Int,
)
