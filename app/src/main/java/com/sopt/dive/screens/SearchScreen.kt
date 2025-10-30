package com.sopt.dive.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun SearchScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("여기는 탐색창입니다",fontSize = 38.sp)
            Image(
                painter = painterResource(id =R.drawable.dotbogi),
                contentDescription = "돋보기",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
        }

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