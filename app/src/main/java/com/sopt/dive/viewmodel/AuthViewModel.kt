package com.sopt.dive.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// 1. 유저 인증 정보 상태 (회원가입 성공 시 여기에 저장)
data class AuthState(
    val savedId: MutableState<String> = mutableStateOf(""),
    val savedPw: MutableState<String> = mutableStateOf(""),
    val savedNickname: MutableState<String> = mutableStateOf(""),
    val savedHobby: MutableState<String> = mutableStateOf("")

    )
class AuthViewModel : ViewModel() {
    // 2. 로그인 상태 (Main Activity에서 네비게이션 결정에 사용)
    var savedState by mutableStateOf(AuthState())
        private set
    var isLoggedIn by mutableStateOf(false)
        private set


    // 회원가입 성공 시 호출될 함수 (데이터 저장)
    fun saveCredentials(id: String, pw: String, nickname: String, hobby: String) {
        savedState.savedId.value=id
        savedState.savedPw.value=pw
        savedState.savedNickname.value=nickname
        savedState.savedHobby.value=hobby

    }

    // 로그인 시도 로직
    fun attemptLogin(id: String, pw: String): Boolean {
        if (id == savedState.savedId.value && id.isNotBlank() && pw == savedState.savedId.value && pw.isNotBlank()) {
            isLoggedIn = true // 로그인 성공
            return true
        }
        return false // 로그인 실패
    }

}
