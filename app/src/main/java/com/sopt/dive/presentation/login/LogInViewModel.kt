package com.sopt.dive.presentation.login

import com.sopt.dive.domain.repository.UserRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.dto.request.LoginRequestDto
import com.sopt.dive.data.dto.response.LoginResponseDto
import com.sopt.dive.data.local.AuthStorage
import com.sopt.dive.data.local.DiveSharedPreferences
import com.sopt.dive.data.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val userRepository = UserRepository()

    // 로그인 입력 데이터
    //내부에서만 수정 가능한 변수
    private val _username = MutableStateFlow("")

    // 외부에는 읽기 전용으로만 공개 (StateFlow)
    val username = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    // 로그인 상태 관리
    private val _loginState = MutableStateFlow<UiState<LoginResponseDto>>(UiState.Idle)
    val loginState = _loginState.asStateFlow()

    fun login() {
        viewModelScope.launch {
            _loginState.value = UiState.Loading
            val request = LoginRequestDto(username.value, password.value)

            userRepository.login(request)
                .onSuccess { response ->
                    val loginData = response.data
                    //로그인 성공하면 아이디를 저장
                    AuthStorage.setUserId(loginData.userId)
                    //자동 로그인 설정: true로 저장
                    DiveSharedPreferences.isLogin = true


                    // response.data는 loginResponseDto
                    _loginState.value = UiState.Success(response.data)
                }
                .onFailure { error ->
                    _loginState.value = UiState.Error(error.message ?: "알 수 없는 에러")
                }
        }
    }

    fun updateUsername(newUsername: String) {
        _username.value = newUsername
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }


}
