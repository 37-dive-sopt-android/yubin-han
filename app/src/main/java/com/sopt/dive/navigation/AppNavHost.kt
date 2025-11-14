package com.sopt.dive.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.sopt.dive.screens.AnimationScreen
import com.sopt.dive.screens.HomeScreen
import com.sopt.dive.screens.LoginScreen
import com.sopt.dive.screens.Myscreen
import com.sopt.dive.screens.SignupScreen
import com.sopt.dive.viewmodel.AuthViewModel

interface MainTabRoute : Route
interface Route

enum class Screen(val route: String) {
    Login("login"), // 로그인 화면
    Signup("signup"), // 회원가입 화면
    Home("home"), // 메인 탭 - 홈
    Animation("Animation"), // 메인 탭 - 애니메이션
    My("my"), // 메인 탭 - 마이

    MainGraph("main_graph?id={id}&pw={pw}&nickname={nickname}&hobby={hobby}")
}

const val MAIN_TAB_ROOT_ROUTE = "main_tab_root"

@Composable
fun AppNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
    authViewModel: AuthViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        // ----------------------------------------------------
        // 1. 인증 화면 (BottomBar 없음)
        // ----------------------------------------------------
        composable(Screen.Login.route) {
            LoginScreen(
                viewModel = authViewModel,
                onNavigateToSignup = {
                    navController.navigate(Screen.Signup.route)
                },
                onNavigateToMain = { id, pw, nickname, hobby ->
                    navController.navigate(
                        route = "main_graph?id=$id&pw=$pw&nickname=$nickname&hobby=$hobby",
                        navOptions = navOptions {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    )
                }
            )
        }

        composable(Screen.Signup.route) {
            SignupScreen(
                viewModel = authViewModel,
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        // ----------------------------------------------------
        // 2. 메인 화면 그룹 (MainGraph) - BottomBar 포함
        // ----------------------------------------------------
        composable(
            route = Screen.MainGraph.route,
            arguments = listOf(
                navArgument("id") { type = NavType.StringType },
                navArgument("pw") { type = NavType.StringType },
                navArgument("nickname") { type = NavType.StringType },
                navArgument("hobby") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            // Nav Arguments에서 사용자 정보를 추출
            val userId = backStackEntry.arguments?.getString("id") ?: ""
            val userPw = backStackEntry.arguments?.getString("pw") ?: ""
            val userNickname = backStackEntry.arguments?.getString("nickname") ?: ""
            val userHobby = backStackEntry.arguments?.getString("hobby") ?: ""

            val mainTabNavController = rememberNavController()

            Scaffold(
                bottomBar = {
                    AppBottomBar(
                        navController = mainTabNavController
                    )
                }
            ) { innerPadding ->
                NavHost(
                    navController = mainTabNavController,
                    startDestination = Screen.Home.route,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    // 홈
                    composable(Screen.Home.route) {
                        HomeScreen(userNickname = userNickname, contentPadding = innerPadding)
                    }

                    // 애니메이션
                    composable(Screen.Animation.route) {
                        AnimationScreen()
                    }

                    // 마이
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
        }
    }
}
