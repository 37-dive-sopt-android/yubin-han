package com.sopt.dive.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable

fun ProfileInfoItemComponent(
    title: String,
    value: String,
    modifier: Modifier = Modifier,

    ) {
    Column(modifier = modifier) {
        Text(
            text = title,
            Modifier.fillMaxWidth(),
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
        Text(
            text = value,
            Modifier.fillMaxWidth(),
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(50.dp))
    }


}
