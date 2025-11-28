package com.sopt.dive.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.domain.model.ProfileSummary

@Composable
fun ProfileHeader(
    profile: ProfileSummary,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = profile.profileImageResId),
            contentDescription = "프로필 사진",
            Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Gray) // 이미지 없으면 회색 배경
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = profile.username, fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text(
                text = profile.statusMessage, fontSize = 16.sp, color = Color.Black
            )
        }
    }
}
