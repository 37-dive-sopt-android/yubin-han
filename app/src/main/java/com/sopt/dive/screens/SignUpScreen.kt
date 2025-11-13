package com.sopt.dive.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.loginprocess.Signup
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.viewmodel.AuthViewModel

@Composable
fun SignupScreen(
    viewModel: AuthViewModel,
    onNavigateToLogin: () -> Unit
) {
    var idText by remember { mutableStateOf("") }
    var pwText by remember { mutableStateOf("") }
    var nicknameText by remember { mutableStateOf("") }
    var hobbyText by remember { mutableStateOf("") }


    val context = LocalContext.current

    // 검증 + 제출
    fun validateAndSubmit() {
        if (idText.isBlank() || pwText.isBlank() || nicknameText.isBlank() || hobbyText.isBlank()) {
            Toast.makeText(context, "모든 정보를 입력해야 회원가입 가능합니다.", Toast.LENGTH_SHORT).show()
            return
        }
        if (idText.length !in 6..10) {
            Toast.makeText(context, "아이디는 6~10글자로 설정해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        if (pwText.length !in 8..12) {
            Toast.makeText(context, "비밀번호는 8~12글자로 설정해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        if (nicknameText.isBlank()) {
            Toast.makeText(context, "닉네임은 한 글자 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
            return
        }

        // ViewModel에 저장 (로그인에 사용)
        viewModel.saveCredentials(idText, pwText, nicknameText, hobbyText)
        Toast.makeText(context, "회원가입 완료되었습니다. 로그인 화면으로 돌아갑니다.", Toast.LENGTH_SHORT).show()

        // 로그인 화면으로 돌아가기
        onNavigateToLogin()
    }


    SignupUI(
        idText = idText,
        pwText = pwText,
        nicknameText = nicknameText,
        hobbyText = hobbyText,
        onIdChange = { idText = it },
        onPwChange = { pwText = it },
        onNicknameChange = { nicknameText = it },
        onHobbyChange = { hobbyText = it },
        onSignUpClick = { validateAndSubmit() }
    )
}

@Composable
private fun SignupUI(
    idText: String,
    pwText: String,
    nicknameText: String,
    hobbyText: String,
    onIdChange: (String) -> Unit,
    onPwChange: (String) -> Unit,
    onNicknameChange: (String) -> Unit,
    onHobbyChange: (String) -> Unit,
    onSignUpClick: () -> Unit
) {

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "SIGN UP",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(25.dp))

        // ID
        Text(
            text = "ID",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 38.sp,
            textAlign = TextAlign.Start
        )

        TextField(
            value = idText,
            onValueChange = onIdChange,
            placeholder = { Text(text = "아이디를 입력해주세요 (6~10자)", color = Color.LightGray) },
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

        // PW
        Text(
            text = "PW",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 38.sp,
            textAlign = TextAlign.Start
        )

        TextField(
            value = pwText,
            onValueChange = onPwChange,
            placeholder = { Text(text = "비밀번호를 입력해주세요 (8~12자)", color = Color.LightGray) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent, errorContainerColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // NICKNAME
        Text(
            text = "NICKNAME",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            fontSize = 38.sp
        )
        TextField(
            value = nicknameText,
            onValueChange = onNicknameChange,
            placeholder = { Text(text = "닉네임을 입력해주세요", color = Color.LightGray) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // HOBBY
        Text(
            text = "HOBBY",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            fontSize = 38.sp

        )
        TextField(
            value = hobbyText,
            onValueChange = onHobbyChange,
            placeholder = { Text(text = "취미를 입력해주세요", color = Color.LightGray) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent, errorContainerColor = Color.Transparent
            )
        )


        Spacer(modifier = Modifier.weight(1f))


        Button(
            onClick = onSignUpClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue, contentColor = Color.White),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "회원가입하기")
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun SignupPreview() {
    DiveTheme {
        SignupUI(
            idText = "",
            pwText = "",
            nicknameText ="" ,
            hobbyText = "",
            onIdChange ={},
            onPwChange ={},
            onHobbyChange = {},
            onNicknameChange ={},
            onSignUpClick ={}
        )
    }
}
