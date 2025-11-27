package com.sopt.dive.viewmodel

import androidx.lifecycle.ViewModel
import com.sopt.dive.data.CardState
import com.sopt.dive.data.RotationAxis
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AnimationViewModel : ViewModel() {

    //현재 카드 뒤집힘 여부
    private val _cardState = MutableStateFlow(CardState())
    val cardState: StateFlow<CardState> = _cardState.asStateFlow()


    val cardFrontResId = com.sopt.dive.R.drawable.front
    val cardBacktResId = com.sopt.dive.R.drawable.back


    //앞뒤 카드 뒤집기
    fun flipCard() {
        _cardState.update { currentState ->
            currentState.copy(isFront = !currentState.isFront)
        }
    }

    //뒤집는 축 변경
    fun setRotationAxis(axis: RotationAxis) {
        _cardState.update { currentState ->
            currentState.copy(rotationAxis = axis)
        }
    }
}
