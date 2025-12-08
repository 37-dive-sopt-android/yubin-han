package com.sopt.dive.presentation.signup

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.data.util.UiState
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.ui.theme.Purple40

@Composable
fun SignUpRoute(
    viewModel: SignUpViewModel = viewModel(),
    onNavigateToLogin: () -> Unit //todo어떤인자를 넘겨주어야하는지 작성해야함.
) {
    // 뷰모델의 상태(StateFlow)를 Compose 상태(State)로 변환
    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()
    val name by viewModel.name.collectAsState()
    val email by viewModel.email.collectAsState()
    val age by viewModel.age.collectAsState()
    val signUpState by viewModel.signUpState.collectAsState()

    val context = LocalContext.current

    // 서버 통신 결과(Success/Error) 처리
    LaunchedEffect(signUpState) {
        when (signUpState) {
            is UiState.Success -> {
                Toast.makeText(context, "회원가입 성공! 로그인 페이지로 이동합니다.", Toast.LENGTH_SHORT).show()
                onNavigateToLogin()
            }

            is UiState.Error -> {
                val message = (signUpState as UiState.Error).message
                Toast.makeText(context, "실패: $message", Toast.LENGTH_SHORT).show()
            }

            else -> {} // Idle, Loading 상태는 대기
        }
    }

    // 회원가입 유효성 검사 로직
    val passwordRegex =
        Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,64}$")

    val onSignUpClick: () -> Unit = {
        if (username.isBlank()) {
            Toast.makeText(context, "사용자명을 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else if (username.length > 50) {
            Toast.makeText(context, "사용자명은 50자 이내여야 합니다.", Toast.LENGTH_SHORT).show()
        } else if (password.contains(" ")) {
            Toast.makeText(context, "비밀번호에 공백을 포함할 수 없습니다.", Toast.LENGTH_SHORT).show()
        } else if (!password.matches(passwordRegex)) {
            Toast.makeText(
                context,
                "비밀번호는 8~64자이며, 대/소문자, 숫자, 특수문자를 모두 포함해야 합니다.",
                Toast.LENGTH_LONG
            ).show()
        } else if (name.isBlank()) {
            Toast.makeText(context, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else if (email.isBlank()) {
            Toast.makeText(context, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(context, "올바른 이메일 형식이 아닙니다.", Toast.LENGTH_SHORT).show()
        } else if ((age.toIntOrNull() ?: (-1)) < 0) { // age가 null이거나 0보다 작으면
            Toast.makeText(context, "나이는 0세 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.signUp()
        }
    }
    SignUpScreen(
        username = username,
        password = password,
        name = name,
        email = email,
        age = age,
        onUsernameChange = { viewModel.username.value = it },
        onPasswordChange = { viewModel.password.value = it },
        onNameChange = { viewModel.name.value = it },
        onEmailChange = { viewModel.email.value = it },
        onAgeChange = { viewModel.age.value = it },
        onSignUpClick = onSignUpClick
    )

}

@Composable
fun SignUpScreen(
    username: String,
    password: String,
    name: String,
    email: String,
    age: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onSignUpClick: () -> Unit
) {

    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
            .imePadding(),
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

        // UserName
        Text(
            text = "USERNAME",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 38.sp,
            textAlign = TextAlign.Start
        )

        TextField(
            value = username,
            onValueChange = onUsernameChange,
            placeholder = { Text(text = "아이디를 입력해주세요", color = Color.LightGray) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent
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
            value = password,
            onValueChange = onPasswordChange,
            placeholder = { Text(text = "비밀번호를 입력해주세요", color = Color.LightGray) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password
            ),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // NICKNAME
        Text(
            text = "NAME",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            fontSize = 38.sp
        )
        TextField(
            value = name,
            onValueChange = onNameChange,
            placeholder = { Text(text = "이름을 입력해주세요", color = Color.LightGray) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        //Email
        Text(
            text = "EMAIL",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            fontSize = 38.sp

        )
        TextField(
            value = email,
            onValueChange = onEmailChange,
            placeholder = { Text(text = "이메일 입력해주세요", color = Color.LightGray) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        //나이
        Text(
            text = "AGE",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            fontSize = 38.sp

        )
        TextField(
            value = age,
            onValueChange = onAgeChange,
            placeholder = { Text(text = "나이를 입력해주세요", color = Color.LightGray) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { KeyboardActions(onDone = { focusManager.clearFocus() }) }),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent
            )
        )


        Spacer(modifier = Modifier.height(50.dp))

        androidx.compose.material3.Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clickable(
                    interactionSource = androidx.compose.runtime.remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onSignUpClick
                ),
            shape = RoundedCornerShape(16.dp),
            color = Purple40,
            contentColor = Color.White
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "회원가입하기"
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun SignupPreview() {
    DiveTheme {
        SignUpScreen(
            username = "",
            password = "",
            name = "",
            email = "",
            age = "",
            onUsernameChange = {},
            onPasswordChange = {},
            onNameChange = {},
            onEmailChange = {},
            onAgeChange = {},
            onSignUpClick = {}

        )
    }
}


