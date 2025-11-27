package com.sopt.dive.data.util

sealed interface UiState<out T>{
    //아무것도 안하고 있는 상태
    object Idle: UiState<Nothing>

    //로딩중
    object Loading: UiState<Nothing>

    //성공
    data class Success<T>(val data: T): UiState<T>

    //실패
    data class Error(val message:String):UiState<Nothing>

}
