package com.sopt.dive.presentation.login
import com.sopt.dive.domain.repository.UserRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.dto.request.LoginRequestDto
import com.sopt.dive.data.dto.response.LoginResponseDto
import com.sopt.dive.data.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val userRepository = UserRepository()

    // 로그인 입력 데이터
    val username = MutableStateFlow("")
    val password = MutableStateFlow("")

    // 로그인 상태 관리
    private val _loginState = MutableStateFlow<UiState<LoginResponseDto>>(UiState.Idle)
    val loginState = _loginState.asStateFlow()

    fun login() {
        viewModelScope.launch {
            _loginState.value = UiState.Loading
            val request = LoginRequestDto(username.value, password.value)

            userRepository.login(request)
                .onSuccess { response ->
                    // response.data는 loginResponseDto
                    _loginState.value = UiState.Success(response.data)
                }
                .onFailure { error ->
                    _loginState.value = UiState.Error(error.message ?: "알 수 없는 에러")
                }
        }
    }
}
