package com.sopt.dive.data

data class CardState(
    val isFront: Boolean = true, //카드 앞뒤 구분
    val rotationAxis: RotationAxis = RotationAxis.AxisY

)


enum class RotationAxis {
    AxisX,//상하
    AxisY //좌우
}