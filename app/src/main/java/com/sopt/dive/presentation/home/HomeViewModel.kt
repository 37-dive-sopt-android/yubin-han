package com.sopt.dive.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.dto.response.UserInfoDto
import com.sopt.dive.data.local.AuthStorage
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

            //저장소에서 아이디 꺼내기
            val myId= AuthStorage.getUserId()
            if(myId==-1L){
                _profileState.value=UiState.Error("로그인 정보가 없습니다.")
                return@launch
            }

            userRepository.getUserInfo(myId)
                .onSuccess { response ->
                    _profileState.value = UiState.Success(response.data)
                }
                .onFailure {
                    _profileState.value = UiState.Error("프로필 로드 실패")
                }
        }
    }
}
