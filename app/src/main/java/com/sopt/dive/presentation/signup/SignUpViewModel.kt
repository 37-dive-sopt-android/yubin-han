package com.sopt.dive.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.dto.request.SignUpRequestDto
import com.sopt.dive.data.dto.response.SignUpResponseDto
import com.sopt.dive.data.util.UiState
import com.sopt.dive.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val userRepository = UserRepository()

    // 입력값들
    val username = MutableStateFlow("")
    val password = MutableStateFlow("")
    val name = MutableStateFlow("")
    val email = MutableStateFlow("")
    val age = MutableStateFlow("")

    //성공하면 SignUpResponseDto 줌
    private val _signUpState = MutableStateFlow<UiState<SignUpResponseDto>>(UiState.Idle)
    val signUpState = _signUpState.asStateFlow()
    val ageInt = age.value.toIntOrNull() ?: 0

    fun signUp() {
        viewModelScope.launch {
            _signUpState.value = UiState.Loading

            val request = SignUpRequestDto(
                username = username.value,
                password = password.value,
                name = name.value,
                email = email.value,
                age = ageInt
            )
            userRepository.signUp(request)
                .onSuccess { response ->
                    // response.data는 SignUpResponseDto
                    _signUpState.value = UiState.Success(response.data)
                }
                .onFailure { error ->
                    _signUpState.value = UiState.Error(error.message ?: "알 수 없는 에러")
                }


        }
    }
}
