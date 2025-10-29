package com.sopt.dive.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.Week1assignment
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun SearchScreen() {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {
        Text("여기는 탐색창입니다",fontSize = 38.sp)


    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreen1() {
    DiveTheme {
        SearchScreen(
        )
    }
}