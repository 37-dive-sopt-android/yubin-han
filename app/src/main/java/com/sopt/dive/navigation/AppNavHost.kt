package com.sopt.dive.navigation

import android.app.admin.TargetUser
import android.hardware.biometrics.BiometricManager
import android.provider.ContactsContract
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.MainScreen
import com.sopt.dive.screens.AnimationScreen
import com.sopt.dive.screens.HomeScreen
import com.sopt.dive.screens.Myscreen
import com.sopt.dive.ui.theme.DiveTheme

//화면 경로
sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Animation : Screen("animation")
    data object My : Screen("my")
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
            HomeScreen(userNickname = userNickname)
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



