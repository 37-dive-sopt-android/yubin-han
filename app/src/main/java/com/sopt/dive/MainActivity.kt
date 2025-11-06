package com.sopt.dive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.navigation.AppBottomBar
import com.sopt.dive.navigation.AppNavHost
import com.sopt.dive.ui.theme.DiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val userId = intent.getStringExtra("ID") ?: "GUEST_ID"
        val userPw = intent.getStringExtra("PW") ?: "손님"
        val userNickname = intent.getStringExtra("NICKNAME") ?: "손님"
        val userHobby= intent.getStringExtra("HOBBY") ?: "손님"

        setContent {
            DiveTheme {
                MainScreen(userId,userPw,userNickname,userHobby)
                }
            }
        }
    }

@Composable
fun MainScreen(userId: String,userPw: String,userNickname: String,userHobby: String){
    val navController=rememberNavController()
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        bottomBar = { AppBottomBar(navController=navController) }
    ){
        paddingValues ->
        AppNavHost(
            navController=navController,
            paddingValues=paddingValues,
            userId=userId,
            userPw=userPw,
            userNickname=userNickname,
            userHobby=userHobby
         )
    }

}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    DiveTheme {
        MainScreen(
            userId = "01yubin",
            userPw = "123456789",
            userNickname = "유콩이야이야",
            userHobby = "운동",

            )
    }
}