package com.sopt.dive.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class Music(
    val ranking: Int,
    val title: String,
    val singer: String
)


class MainViewModel : ViewModel() {
    // count 자체는 여기서만 변경이 가능하게
    var count by mutableIntStateOf(0)
        private set

    fun onButtonClick() {
        count = count + 1
    }

    fun onClearCount() {
        count = 0
    }

    val musics = listOf<Music>(
        Music(
            ranking = 1,
            title = "HAPPY",
            singer = "DAY6 (데이식스)"
        ),
        Music(
            ranking = 2,
            title = "UP (KARINA Solo)",
            singer = "aespa"
        ),
        Music(
            ranking = 3,
            title = "내 이름 맑음",
            singer = "QWER"
        ),
        Music(
            ranking = 4,
            title = "Welcome to the Show",
            singer = "DAY6 (데이식스)"
        ),
        Music(
            ranking = 5,
            title = "Supernova",
            singer = "aespa"
        ),
        Music(
            ranking = 6,
            title = "한 페이지가 될 수 있게",
            singer = "DAY6 (데이식스)"
        ),
    )
}