package com.sopt.dive.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.domain.model.BottomNavItem


@Composable
fun AppBottomBar(navController: NavHostController) {
    val screens = BottomNavItem.getItems()

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { item ->
            val isSelected = currentRoute == item.screen.route

            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = isSelected, // Home, Search, My
                onClick = {
                    navController.navigate(item.screen.route) {
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
private fun AppBottomBarPreview() {
    val navController = rememberNavController()
    AppBottomBar(navController = navController)
}
