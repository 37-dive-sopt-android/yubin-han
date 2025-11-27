package com.sopt.dive.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.dto.response.UserInfoDto
import com.sopt.dive.data.util.UiState
import com.sopt.dive.domain.model.FeedItem
import com.sopt.dive.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val userRepository = UserRepository()

    // 피드 목록 상태
    private val _feedState = MutableStateFlow<UiState<List<FeedItem>>>(UiState.Loading)
    val feedState = _feedState.asStateFlow()

    //내 프로필 정보 상태
    private val _profileState = MutableStateFlow<UiState<UserInfoDto>>(UiState.Loading)
    val profileState = _profileState.asStateFlow()

    init {
        fetchHomeData()
    }

    fun fetchHomeData() {
        viewModelScope.launch {
            _feedState.value = UiState.Loading
            _feedState.value = UiState.Success(FeedItem.dummyFeeds)
        }
        // 내 정보 가져오기 (실제 서버 통신)
        viewModelScope.launch {
            _profileState.value = UiState.Loading

            // "1"번 유저라고 가정하고 요청 (실제론 로그인 시 저장한 ID 사용)
            userRepository.getUserInfo(1)
                .onSuccess { response ->
                    _profileState.value = UiState.Success(response.data)
                }
                .onFailure {
                    _profileState.value = UiState.Error("프로필 로드 실패")
                }
        }
    }
}
