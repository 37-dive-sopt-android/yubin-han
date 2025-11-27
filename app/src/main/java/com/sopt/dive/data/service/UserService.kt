package com.sopt.dive.data.service

import com.sopt.dive.data.remote.LoginRequestDto
import com.sopt.dive.data.remote.LoginResponseDto
import com.sopt.dive.data.remote.SignUpRequestDto
import com.sopt.dive.data.remote.SignUpResponseDto
import com.sopt.dive.data.remote.UserInfoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Header
import retrofit2.http.Path

interface UserService {
    //회원가입
    @POST("api/v1/users")
    suspend fun postSignUp(
        @Body requestBody:
        SignUpRequestDto
    ): Response<SignUpResponseDto>

    //로그인
    @POST("api/v1/auth/login") 
    suspend fun postLogin(
        @Body requestBody: LoginRequestDto
    ): Response<LoginResponseDto>

    //정보 조회
    @GET("api/v1/users/{userId}")
    suspend fun getUserInfo(
        @Path("userId")userId:Long,
        @Header("Authorization")token: String
    ): Response<UserInfoDto>

}

