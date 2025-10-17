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
import androidx.compose.runtime.LaunchedEffect
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

    private val signup = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            //회원가입 성공
            val data: Intent? = result.data // SignUpActivity에서 보낸 Intent


            val userID = data?.getStringExtra("ID")
            val userPW = data?.getStringExtra("PW")
            val userNICKNAME = data?.getStringExtra("NICKNAME")
            val userHOBBY = data?.getStringExtra("HOBBY")

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
        context.startActivity(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Week1assignment(
                        paddingValues = innerPadding,
                        activity = this
                    )

                }
            }
        }
    }
}



@Composable
fun Week1assignment(
    paddingValues: PaddingValues,
    activity: LoginActivity?=null
) {

    //로그인 창 입력 상태
    var idText by remember { mutableStateOf("") }
    var pwText by remember { mutableStateOf("") }

    //회원가입에서 받아온 상태
    var savedId by remember { mutableStateOf<String>("") }
    var savedPw by remember { mutableStateOf<String>("") }
    var savedNickname by remember { mutableStateOf<String>("") }
    var savedHobby by remember { mutableStateOf<String>("") }

    // 상태를 업데이트하는 함수 (콜백으로 전달될 함수)
    val updateSavedCredentials: (id: String, pw: String) -> Unit = { id, pw ->
        savedId = id
        savedPw = pw}


    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current



    Column (
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
            onValueChange = { value ->
                idText = value
            },
            placeholder = {
                Text(
                    text = "아이디를 입력해주세요",color=Color.LightGray
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
                errorContainerColor = Color.Transparent)
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
            onValueChange = { value -> pwText = value},
            placeholder = {
                Text(
                    text = "비밀번호를 입력해주세요",color=Color.LightGray
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
            onClick = {  //성공 조건 추가
                if(idText==savedId&&pwText==savedPw){

                    Toast.makeText(context,
                        "로그인에 성공했습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    activity?.navigateToMain(
                        context,
                        savedId,
                        savedPw,
                        savedNickname,
                        savedHobby
                    )
                }else{
                    Toast.makeText(context,
                        "회원가입 정보가 없습니다.",
                        Toast.LENGTH_SHORT,

                    ).show()
                }
                      },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ){
            Text(
                text = "Welcome To SOPT"
            )
        }



        Text(
            text = "회원가입하기",
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.fillMaxWidth()
                .clickable{
                    val intent = Intent(context, SignUpActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }

                    context.startActivity(intent)
                },
            textAlign = TextAlign.Center

        )
    }
}
@Preview(showBackground = true)
@Composable
fun Week1assignmentPreview() {
    DiveTheme {
        Week1assignment(
            paddingValues = PaddingValues()
        )
    }
}

