package com.sopt.dive.presentation.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.dto.response.UserInfoDto
import com.sopt.dive.data.util.UiState
import com.sopt.dive.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val userRepository = UserRepository()

    // 내 정보 상태 관리 (UserInfoDto)
    private val _myInfoState = MutableStateFlow<UiState<UserInfoDto>>(UiState.Loading)
    val myInfoState = _myInfoState.asStateFlow()

    init {
        fetchUserInfo()
    }

    fun fetchUserInfo() {
        viewModelScope.launch {
            _myInfoState.value = UiState.Loading

            // ⭐️ 주의: 실제로는 로그인 때 받은 ID나 토큰을 저장해뒀다가 여기 써야 합니다.
            // 지금은 테스트를 위해 임의의 ID나 "1" 등을 넣겠습니다.
            userRepository.getUserInfo(1)
                .onSuccess { response ->
                    _myInfoState.value = UiState.Success(response.data)
                }
                .onFailure {
                    _myInfoState.value = UiState.Error("정보 불러오기 실패")
                }
        }
    }
}
