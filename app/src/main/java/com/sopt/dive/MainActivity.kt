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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Week1assignment3(
                        paddingValues = innerPadding,
                        userId = userId,
                        userPw=userPw,
                        userNickname=userNickname,
                        userHobby=userHobby
                    )


                }
            }
        }
    }
}


@Composable
fun Week1assignment3(
    paddingValues: PaddingValues,
    userId: String, // 데이터를 받기 위한 파라미터
    userPw:String,
    userNickname: String,
    userHobby:String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(paddingValues),
        horizontalAlignment = BiasAlignment.Horizontal(-1f)
    ) {
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically

            )
        {
        Image(painter = painterResource(id=R.drawable.yangpa),
            contentDescription = "프로필사진",
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)

        )


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


        Text(
            text = "ID",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
        @Composable
        fun IDInputWithHint(
            // 1. 현재 ID 텍스트 값
            idText: String,
            // 2. 텍스트가 변경될 때 호출할 콜백 함수
            onIdTextChange: (String) -> Unit
        ){
            TextField(
                value = idText,
                onValueChange =  onIdTextChange,
                //ID 힌트
                placeholder = {
                    Text(
                        text = "유콩이라고하오",
                        color=Color.LightGray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,    // 배경 투명
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent)
            )


        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "PW",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
        @Composable
        fun PWInputWithHint(
            // 1. 현재 pw 텍스트 값
            userPw: String,
            // 2. 텍스트가 변경될 때 호출할 콜백 함수
            onpwTextChange: (String) -> Unit
        ){
            TextField(
                value = userPw,
                onValueChange =  onpwTextChange,
                //ID 힌트
                placeholder = {
                    Text(
                        text = "123456789",
                        color=Color.LightGray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,    // 배경 투명
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent)
            )


        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "NICKNAME",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
        @Composable
        fun NICKNAMEInputWithHint(
            // 1. 현재 nickname 텍스트 값
            nicknameText: String,
            // 2. 텍스트가 변경될 때 호출할 콜백 함수
            onnicknameTextChange: (String) -> Unit
        ){
            TextField(
                value = userNickname,
                onValueChange =  onnicknameTextChange,
                //닉네임힌트
                placeholder = {
                    Text(
                        text = "나는야유콩이지",
                        color=Color.LightGray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,    // 배경 투명
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent)
            )


        }
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "HOBBY",
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 38.sp,
            textAlign = TextAlign.Start
        )
        @Composable
        fun HOBBYInputWithHint(
            // 1. 현재 HOBBY 텍스트 값
            ageText: String,
            // 2. 텍스트가 변경될 때 호출할 콜백 함수
            onhobbyTextChange: (String) -> Unit
        ){
            TextField(
                value = userPw,
                onValueChange =  onhobbyTextChange,
                //취미힌트
                placeholder = {
                    Text(
                        text = "운동",
                        color=Color.LightGray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,    // 배경 투명
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent)
            )


        }


    }
}
@Preview(showBackground = true)
@Composable
fun Week1assignmentPreview3() {
    DiveTheme {
        Week1assignment3(
            paddingValues = PaddingValues(),userId = "01yubin",
            userPw = "123456789",
            userNickname ="유콩이야이야" ,
            userHobby = "운동",

        )
    }
}