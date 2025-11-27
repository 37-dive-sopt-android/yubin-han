package com.sopt.dive.presentation.animation

import androidx.lifecycle.ViewModel
import com.sopt.dive.R
import com.sopt.dive.domain.model.CardState
import com.sopt.dive.domain.model.RotationAxis
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AnimationViewModel : ViewModel() {

    //현재 카드 뒤집힘 여부
    private val _cardState = MutableStateFlow(CardState())
    val cardState: StateFlow<CardState> = _cardState.asStateFlow()


    val cardFrontResId = R.drawable.front
    val cardBacktResId = R.drawable.back


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
