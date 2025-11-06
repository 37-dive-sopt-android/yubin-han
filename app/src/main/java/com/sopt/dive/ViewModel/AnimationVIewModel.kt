package com.sopt.dive.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sopt.dive.data.CardState
import com.sopt.dive.data.RotationAxis
import com.sopt.dive.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AnimationVIewModel : ViewModel() {

    //현재 카드 뒤집힘 여부
    private val _isFlipped = MutableStateFlow(CardState())
    val isFlipped: StateFlow<CardState> = _isFlipped.asStateFlow()


    val cardFrontResId = com.sopt.dive.R.drawable.front
    val cardBacktResId = com.sopt.dive.R.drawable.back


    //앞뒤 카드 뒤집기
    fun flipCard() {
        _isFlipped.update { currentState ->
            currentState.copy(isFront = !currentState.isFront)
        }
    }

    //뒤집는 축 변경
    fun setRotationAxis(axis: RotationAxis) {
        _isFlipped.update { currentState ->
            currentState.copy(rotationAxis = axis)
        }
    }
}
