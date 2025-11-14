package com.sopt.dive.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.navigation.AppBottomBar
import com.sopt.dive.data.FeedItem
import com.sopt.dive.data.ProfileSummary


@Composable

fun UserInfoComponent(
    title: String,
    value: String,
    placeholder: String
) {
    Text(
        text = title,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 38.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start
    )
    TextField(
        value = value,
        onValueChange = { },
        readOnly = true,
        placeholder = {
            Text(
                text = placeholder,
                color = Color.LightGray
            )
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent
        )
    )
    Spacer(modifier = Modifier.height(50.dp))
}
