package com.sopt.dive.domain.repository

import com.sopt.dive.data.dto.base.BaseResponse
import com.sopt.dive.data.dto.request.LoginRequestDto
import com.sopt.dive.data.dto.request.SignUpRequestDto
import com.sopt.dive.data.dto.response.LoginResponseDto
import com.sopt.dive.data.dto.response.SignUpResponseDto
import com.sopt.dive.data.dto.response.UserInfoDto
import com.sopt.dive.data.remote.ServicePool

class UserRepository{
    //ServicePool에서 객체를 가져옴
    private val userService= ServicePool.userService

    //회원가입 함수
    suspend fun signUp(request: SignUpRequestDto): Result<BaseResponse<SignUpResponseDto>>{
        return runCatching {
            userService.postSignUp(request)
        }
    }

    //로그인 함수
    suspend fun login(request: LoginRequestDto): Result<BaseResponse<LoginResponseDto>>{
        return runCatching {
            userService.postLogin(request)
        }
    }

    //유저 정보 조회 함수
    suspend fun getUserInfo(id: Long): Result<BaseResponse<UserInfoDto>>{
        return runCatching {
            userService.getUserInfo(id)
        }
    }
}
