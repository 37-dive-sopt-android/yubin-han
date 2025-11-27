package com.sopt.dive.data.service

import com.sopt.dive.data.dto.base.BaseResponse
import com.sopt.dive.data.dto.request.LoginRequestDto
import com.sopt.dive.data.dto.response.LoginResponseDto
import com.sopt.dive.data.dto.request.SignUpRequestDto
import com.sopt.dive.data.dto.response.SignUpResponseDto
import com.sopt.dive.data.dto.response.UserInfoDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApiService {
    //회원가입
    @POST("api/v1/users")
    suspend fun postSignUp(
        @Body requestBody:
        SignUpRequestDto
    ): BaseResponse<SignUpResponseDto>

    //로그인
    @POST("api/v1/auth/login")
    suspend fun postLogin(
        @Body requestBody: LoginRequestDto
    ): BaseResponse<LoginResponseDto>

    //정보 조회
    @GET("api/v1/users/{id}")
    suspend fun getUserInfo(
        @Path("id") id: Long,
    ): BaseResponse<UserInfoDto>

}

