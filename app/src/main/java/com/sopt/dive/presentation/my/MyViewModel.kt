package com.sopt.dive.presentation.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.dto.response.UserInfoDto
import com.sopt.dive.data.local.AuthStorage
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

            //저장소에서 아이디 꺼내기
            val myId = AuthStorage.getUserId()
            if (myId == -1L) {
                _myInfoState.value = UiState.Error("로그인 정보가 없습니다.")
                return@launch
            }
            userRepository.getUserInfo(myId)
                .onSuccess { response ->
                    _myInfoState.value = UiState.Success(response.data)
                }
                .onFailure {
                    _myInfoState.value = UiState.Error("정보 불러오기 실패")
                }
        }
    }
}
