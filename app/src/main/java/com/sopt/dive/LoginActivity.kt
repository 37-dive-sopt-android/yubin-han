package com.sopt.dive

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Scaffold
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


class LoginActivity : ComponentActivity() {

    //회원가입에서 받아온 상태
    var savedId by mutableStateOf<String>("")
    var savedPw by mutableStateOf<String>("")
    var savedNickname by mutableStateOf<String>("")
    var savedHobby by mutableStateOf<String>("")

    // 상태를 업데이트하는 함수 (콜백으로 전달될 함수)
    val updateSavedCredentials: (id: String, pw: String, nickname: String, hobby: String) -> Unit =
        { id, pw, nickname, hobby ->
            savedId = id
            savedPw = pw
            savedNickname = nickname
            savedHobby = hobby
        }


    val signup = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            //회원가입 성공
            val data: Intent? = result.data // SignUpActivity에서 보낸 Intent


            val userID = data?.getStringExtra("ID").orEmpty()
            val userPW = data?.getStringExtra("PW").orEmpty()
            val userNICKNAME = data?.getStringExtra("NICKNAME").orEmpty()
            val userHOBBY = data?.getStringExtra("HOBBY").orEmpty()

            updateSavedCredentials(userID, userPW, userNICKNAME, userHOBBY)


        }

    }

    fun navigateToMain(
        context: Context,
        id: String, pw: String, nickname: String, hobby: String
    ) {
        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra("ID", id)
            putExtra("PW", pw)
            putExtra("NICKNAME", nickname)
            putExtra("HOBBY", hobby)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginContainer(
                        paddingValues = innerPadding,
                        savedId = savedId,
                        savedPw = savedPw,
                        savedNickname = savedNickname,
                        savedHobby = savedHobby,

                        onLoginAttempt = { id, pw ->
                            if (id == savedId && id.isNotBlank() && pw == savedPw && pw.isNotBlank()) {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "로그인에 성공했습니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navigateToMain(
                                    this@LoginActivity,
                                    id,
                                    pw,
                                    savedNickname,
                                    savedHobby
                                )
                            } else {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "로그인에 실패했습니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        onSignupClick = {
                            val intent = Intent(this, SignUpActivity::class.java)
                            signup.launch(intent)
                        }


                    )

                }
            }
        }
    }
}

@Composable
fun LoginContainer(
    paddingValues: PaddingValues,
    savedId: String,
    savedPw: String,
    savedNickname: String,
    savedHobby: String,
    onLoginAttempt: (id: String, pw: String) -> Unit,
    onSignupClick: () -> Unit

) {
    //로그인 창 입력 상태
    var idText by remember { mutableStateOf("") }
    var pwText by remember { mutableStateOf("") }

    Login(
        paddingValues = paddingValues,
        idText = idText,
        onIdChange = { idText = it },
        pwText = pwText,
        onPwChange = { pwText = it },
        onLoginClick = { onLoginAttempt(idText, pwText) },
        onSignupClick = onSignupClick
    )

}

@Composable
fun Login(
    paddingValues: PaddingValues,
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
            .padding(30.dp)
            .padding(paddingValues),
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
            onValueChange = {
                onIdChange(it)
            },
            placeholder = {
                Text(
                    text = "아이디를 입력해주세요", color = Color.LightGray
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) } // PW로 포커스 이동
            ),
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,    // 배경 투명
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent
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
            placeholder = {
                Text(
                    text = "비밀번호를 입력해주세요", color = Color.LightGray
                )
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,    // 배경 투명
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent
            )
        )



        Spacer(modifier = Modifier.weight(1f))



        Button(
            onClick = onLoginClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Welcome To SOPT"
            )
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
private fun LoginPreview() {
    DiveTheme {
        Login(
            paddingValues = PaddingValues(),
            idText = "test_id",
            onIdChange = {},
            pwText = "test_pw",
            onPwChange = {},
            onLoginClick = {},
            onSignupClick = {}
        )

    }
}