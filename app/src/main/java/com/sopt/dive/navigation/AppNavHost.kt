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
import com.sopt.dive.screens.HomeScreen
import com.sopt.dive.screens.Myscreen
import com.sopt.dive.screens.SearchScreen
import com.sopt.dive.ui.theme.DiveTheme

//화면 경로
sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Search : Screen("search")
    data object My : Screen("my")
}
@Composable
fun AppNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
    userId: String,
    userPw:String,
    userNickname: String,
    userHobby: String
){
    NavHost(
        navController=navController,
        startDestination = Screen.Home.route,
        modifier = Modifier.padding((paddingValues))
    ){
        //홈
        composable(Screen.Home.route){
            HomeScreen()
        }
        //서치
        composable(Screen.Search.route)
        {
            SearchScreen()
        }
        //마이
        composable(Screen.My.route){
            Myscreen(
                userId=userId,
                userPw=userPw,
                userNickname=userNickname,
                userHobby=userHobby
            )

        }
    }
}



@Preview(showBackground = true)
@Composable
fun AppNavHostPreview() {
    DiveTheme {
        AppNavHost(
            rememberNavController(),
            paddingValues=PaddingValues(),
            userId = "01yubin",
            userPw = "123456789",
            userNickname = "유콩이야이야",
            userHobby = "운동"
        )
    }
}
