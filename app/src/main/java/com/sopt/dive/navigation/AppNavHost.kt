package com.sopt.dive.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.components.AppBottomBar
import com.sopt.dive.data.local.DiveSharedPreferences
import com.sopt.dive.presentation.animation.AnimationScreen
import com.sopt.dive.presentation.home.HomeRoute
import com.sopt.dive.presentation.login.LoginRoute
import com.sopt.dive.presentation.my.MyRoute
import com.sopt.dive.presentation.signup.SignUpRoute

enum class Screen(val route: String) {
    Login("login"), // 로그인 화면
    Signup("signup"), // 회원가입 화면
    Home("home"), // 메인 탭 - 홈
    Animation("Animation"), // 메인 탭 - 애니메이션
    My("my"), // 메인 탭 - 마이

    MainGraph("main_graph")
}


@Composable
fun AppNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    //현재 저장된 로그인 상태 확인(true/false)
    val isLogin= DiveSharedPreferences.isLogin
    //로그인 상태에 따라 시작 지점 결정 로그인 되어있다면 MainGraph로 아니면 Login 화면으로
    val startDestination = if (isLogin) Screen.MainGraph.route else Screen.Login.route

    NavHost(
        navController = navController,
        startDestination =startDestination,
        modifier = Modifier.padding(paddingValues)
    ) {
        //로그인 화면
        composable(Screen.Login.route) {
            LoginRoute(
                onNavigateToSignup = {
                    navController.navigate(Screen.Signup.route)
                },
                onNavigateToMain = {
                    navController.navigate(Screen.MainGraph.route) {
                        //로그인 후 마이로 갈 때, 뒤로가기 누르면 로그인 화면 안 나오게 스택 제거
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        //회원가입 화면
        composable(Screen.Signup.route) {
            SignUpRoute(
                onNavigateToLogin = {
                    //회원가입 후 로그인 화면으로 돌아감
                    navController.popBackStack()
                }
            )
        }

        //메인 화면 그룹 (홈, 애니메이션, 마이)
        composable(
            route = Screen.MainGraph.route

        ) {
            val mainTabNavController = rememberNavController()
            Scaffold(
                bottomBar = { AppBottomBar(navController =mainTabNavController) }
            ) { innerPadding ->
                NavHost(
                    navController = mainTabNavController,
                    startDestination = Screen.Home.route,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    // 홈
                    composable(Screen.Home.route) {
                        HomeRoute(contentPadding = innerPadding)
                    }

                    // 애니메이션
                    composable(Screen.Animation.route) {
                        AnimationScreen()
                    }

                    // 마이
                    composable(Screen.My.route) {
                        MyRoute()
                    }
                }
            }
        }
    }
}
