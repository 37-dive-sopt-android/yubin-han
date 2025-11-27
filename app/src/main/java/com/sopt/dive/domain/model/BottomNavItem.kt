package com.sopt.dive.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.sopt.dive.navigation.Screen

enum class BottomNavItem(
    val screen: Screen,          // 연결된 화면 객체
    val label: String,           // 표시할 텍스트
    val icon: ImageVector        // 표시할 아이콘
) {
    Home(
        screen = Screen.Home,
        label = "Home",
        icon = Icons.Filled.Home
    ),
    Animation(
        screen = Screen.Animation,
        label = "Animation",
        icon = Icons.Filled.Star
    ),
    My(
        screen = Screen.My,
        label = "My",
        icon = Icons.Filled.Person
    );

    companion object {
        fun getItems(): List<BottomNavItem> = entries
    }
}
