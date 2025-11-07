package com.sopt.dive.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.MainScreen // MainScreen이 사용되지 않으면 삭제 가능
import com.sopt.dive.screens.AnimationScreen
import com.sopt.dive.screens.HomeScreen
import com.sopt.dive.screens.Myscreen
import com.sopt.dive.data.BottomNavItem

//화면 경로

enum class Screen(val route: String) {
    Home ("home"),
    Animation ("Animation"),
    My ("my")
}
@Composable
fun AppNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
    userId: String,
    userPw: String,
    userNickname: String,
    userHobby: String
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = Modifier.padding((paddingValues))
    ) {
        //홈
        composable(Screen.Home.route) {
            HomeScreen(
                userNickname = userNickname, contentPadding = paddingValues
            )
        }
        //애니메이션
        composable(Screen.Animation.route)
        {
            AnimationScreen()
        }
        //마이
        composable(Screen.My.route) {
            Myscreen(
                userId = userId,
                userPw = userPw,
                userNickname = userNickname,
                userHobby = userHobby
            )

        }
    }
}



