package com.sopt.dive.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.R
import com.sopt.dive.components.ProfileInfoItemComponent
import com.sopt.dive.navigation.AppBottomBar
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun Myscreen(
    userId: String,
    userPw: String,
    userNickname: String,
    userHobby: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .padding(16.dp)

    ) {
        Spacer(modifier = Modifier.height(25.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.yangpa), // R.drawable.yangpa는 실제 리소스 ID로 대체 필요
                contentDescription = "프로필사진",
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "한유빈",
                color = Color.Black,
                fontSize = 20.sp,
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "안녕하세요! 한유빈입니다.",
            color = Color.Black,
            fontSize = 20.sp,

            )
        Spacer(modifier = Modifier.height(50.dp))

        ProfileInfoItemComponent(
            title = "ID",
            value = userId
        )

        ProfileInfoItemComponent(
            title = "PW",
            value = userPw
        )

        ProfileInfoItemComponent(
            title = "NICKNAME",
            value = userNickname
        )

        ProfileInfoItemComponent(
            title = "HOBBY",
            value = userHobby
        )
    }
}


@Preview
@Composable
private fun MyScreenPreview() {
    DiveTheme {
        Myscreen(
            userId = "01yubin",
            userPw = "123456789",
            userNickname = "유콩이야이야",
            userHobby = "운동"
        )
    }
}
