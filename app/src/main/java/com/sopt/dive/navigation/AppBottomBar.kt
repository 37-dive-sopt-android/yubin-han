package com.sopt.dive.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.Week1assignment
import com.sopt.dive.ui.theme.DiveTheme


@Composable
fun AppBottomBar(navController: NavHostController) {
    val screens = listOf(
        Screen.Home,
        Screen.Search,
        Screen.My
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRout = navBackStackEntry?.destination?.route

        screens.forEach { screen ->
            val icon = when (screen) {
                Screen.Home -> Icons.Filled.Home
                Screen.Search -> Icons.Filled.Search
                Screen.My -> Icons.Filled.Person
            }

            NavigationBarItem(
                icon = { Icon(icon, contentDescription = null) },
                label = { Text(screen.route.replaceFirstChar { it.uppercase() }) }, // Home, Search, My
                selected = currentRout == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        // 탭 선택 시 백 스택을 관리하는 표준 로직 (시작 지점 유지)
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun AppBottomBarPreview() {
    val navController = rememberNavController()
    AppBottomBar(navController = navController)
}