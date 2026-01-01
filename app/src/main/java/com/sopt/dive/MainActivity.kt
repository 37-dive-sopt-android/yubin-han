package com.sopt.dive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.data.local.AuthStorage
import com.sopt.dive.data.local.DiveSharedPreferences
import com.sopt.dive.navigation.AppNavHost
import com.sopt.dive.ui.theme.DiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DiveSharedPreferences.init(this)
        AuthStorage.init(this)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            paddingValues = innerPadding
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    DiveTheme {
        MainScreen()
    }
}
