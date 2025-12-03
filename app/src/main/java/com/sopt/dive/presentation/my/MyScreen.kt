package com.sopt.dive.presentation.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.data.dto.response.UserInfoDto
import com.sopt.dive.data.util.UiState
import com.sopt.dive.ui.theme.DiveTheme


@Composable
fun MyRoute(
    viewModel: MyViewModel = viewModel()
) {
    val uiState by viewModel.myInfoState.collectAsState()

    // 상태에 따라 다른 화면 보여주기
    when (uiState) {

        is UiState.Success -> {
            // 성공하면 데이터 보여주기
            val userInfo = (uiState as UiState.Success).data
            MyScreen(userInfo = userInfo)
        }

        is UiState.Error -> {
            // 에러 화면
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "에러 발생: ${(uiState as UiState.Error).message}")
            }
        }

        else -> {}
    }
}

@Composable
fun MyScreen(
    userInfo: UserInfoDto

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)

    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.yangpa),
                contentDescription = "프로필사진",
                modifier = Modifier.size(50.dp)
            )
            Text(
                text = userInfo.name,
                color = Color.Black,
                fontSize = 20.sp,
            )
        }

        Text(
            text = "안녕하세요! ${userInfo.username} 입니다.",
            color = Color.Black,
            fontSize = 20.sp,

            )
        Spacer(modifier = Modifier.height(50.dp))


        Text(
            text = "아이디: ${userInfo.username}",
            fontSize = 20.sp
        )
        Text(
            text = "이름: ${userInfo.name}",
            fontSize = 20.sp
        )

        Text(
            text = "이메일: ${userInfo.email}",
            fontSize = 20.sp
        )

        Text(
            text = "나이: ${userInfo.age}",
            fontSize = 20.sp
        )

    }
}


@Preview
@Composable
private fun MyScreenPreview() {
    DiveTheme {
        MyScreen(
            userInfo = UserInfoDto(1, "yukong", "한유빈", "01yubin@naver.com", 1, "")
        )
    }
}
