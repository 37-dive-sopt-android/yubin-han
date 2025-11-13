package com.sopt.dive.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.viewmodel.AuthViewModel


@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onNavigateToSignup: () -> Unit,
    onNavigateToMain: (id: String, pw: String, nickname: String, hobby: String) -> Unit
) {
    val context = LocalContext.current
    val savedState = viewModel.savedState

    var idText by remember { mutableStateOf("") }
    var pwText by remember { mutableStateOf("") }

    val onLoginAttempt: () -> Unit = {
        if (viewModel.attemptLogin(idText, pwText)) {
            Toast.makeText(context, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()

            // Main으로 이동하며 저장된 모든 사용자 정보를 Nav Arguments로 전달
            onNavigateToMain(
                savedState.savedId.value,
                savedState.savedPw.value, // PW도 Main으로 전달
                savedState.savedNickname.value,
                savedState.savedHobby.value
            )
        } else {
            Toast.makeText(context, "로그인에 실패했습니다. ID/PW를 확인하거나 회원가입을 하세요.", Toast.LENGTH_SHORT).show()
        }
    }


    LoginContainerUI(
        idText = idText,
        onIdChange = { idText = it },
        pwText = pwText,
        onPwChange = { pwText = it },
        onLoginClick = onLoginAttempt,
        onSignupClick = onNavigateToSignup
    )
}

@Composable
private fun LoginContainerUI(
    idText: String,
    onIdChange: (String) -> Unit,
    pwText: String,
    onPwChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onSignupClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Welcome To Sopt",
            color = Color.Black,
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "ID",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 38.sp,
            textAlign = TextAlign.Start
        )

        TextField(
            value = idText,
            onValueChange = onIdChange,
            placeholder = { Text(text = "아이디를 입력해주세요", color = Color.LightGray) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent, errorContainerColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "PW",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 38.sp,
            textAlign = TextAlign.Start
        )

        TextField(
            value = pwText,
            onValueChange = onPwChange,
            placeholder = { Text(text = "비밀번호를 입력해주세요", color = Color.LightGray) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent, errorContainerColor = Color.Transparent
            )
        )


        Spacer(modifier = Modifier.weight(1f))


        Button(
            onClick = onLoginClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue, contentColor = Color.White),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "Welcome To SOPT")
        }


        Text(
            text = "회원가입하기",
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onSignupClick),
            textAlign = TextAlign.Center
        )
    }
}
@Preview(showBackground = true)
@Composable
private fun Longinpreview() {
    DiveTheme {
        LoginContainerUI(
            idText = "",
            pwText = "",
            onIdChange ={},
            onPwChange ={},
            onSignupClick ={},
            onLoginClick = {}
        )
    }
}

