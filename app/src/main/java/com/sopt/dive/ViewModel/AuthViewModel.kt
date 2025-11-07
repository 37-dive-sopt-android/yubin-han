package com.sopt.dive.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    // 1. 유저 인증 정보 상태 (회원가입 성공 시 여기에 저장)
    var savedId by mutableStateOf("")
        private set
    var savedPw by mutableStateOf("")
        private set
    var savedNickname by mutableStateOf("")
        private set
    var savedHobby by mutableStateOf("")
        private set

    // 2. 로그인 상태 (Main Activity에서 네비게이션 결정에 사용)
    var isLoggedIn by mutableStateOf(false)
        private set

    // 회원가입 성공 시 호출될 함수 (데이터 저장)
    fun saveCredentials(id: String, pw: String, nickname: String, hobby: String) {
        savedId = id
        savedPw = pw
        savedNickname = nickname
        savedHobby = hobby
    }

    // 로그인 시도 로직
    fun attemptLogin(id: String, pw: String): Boolean {
        if (id == savedId && id.isNotBlank() && pw == savedPw && pw.isNotBlank()) {
            isLoggedIn = true // 로그인 성공
            return true
        }
        return false // 로그인 실패
    }

}