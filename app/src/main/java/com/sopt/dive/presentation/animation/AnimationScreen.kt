package com.sopt.dive.presentation.animation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.ui.theme.Purple40


@Composable
fun AnimationScreen(viewModel: AnimationViewModel = viewModel()) {

    val cardState by viewModel.cardState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // 카드 뒤집기 애니메이션 Composable
        CardFlipAnimation(
            isFlipped = cardState.isFront,
            frontResourceId = viewModel.cardFrontResId,
            backResourceId = viewModel.cardBacktResId,
            onCardClick = viewModel::flipCard // 카드 클릭 시 ViewModel 함수 호출
        )

        Spacer(modifier = Modifier.height(64.dp))

        // 뒤집기 버튼
        Button(
            onClick = viewModel::flipCard // 버튼 클릭 시 ViewModel 함수 호출
            , colors = ButtonDefaults.buttonColors(
                containerColor = Purple40,
                contentColor = Color.White
            )
        ) {
            Text(text = if (cardState.isFront) "앞면 보기" else "뒷면 보기")
        }
    }
}

@Composable
fun CardFlipAnimation(
    isFlipped: Boolean,
    frontResourceId: Int,
    backResourceId: Int,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // 0f (앞면) 또는 180f (뒷면)로 회전 각도 애니메이션
    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(
            durationMillis = 800,
            easing = FastOutSlowInEasing
        ), label = "CardRotation"
    )

    // 회전 각도가 90도를 넘어가면 반대 면 보여주기
    val isFrontVisible = rotation < 90f

    val cardSize = Modifier.size(200.dp, 300.dp)

    Box(
        modifier = modifier
            .then(cardSize)
            .clickable(onClick = onCardClick), // 카드 클릭 시 뒤집기
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = cardSize
                .graphicsLayer {
                    rotationY = rotation // Y축 회전
                    cameraDistance = 8 * density // 3D 원근감 설정
                }
        ) {
            CardContent(
                resourceId = if (isFrontVisible) frontResourceId else backResourceId,
                modifier = Modifier.graphicsLayer {
                    rotationY = if (isFrontVisible) 0f else 180f
                }
            )
        }
    }
}

@Composable
private fun CardContent(resourceId: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = resourceId),
        contentDescription = "Card Image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp))
    )
}


@Preview(showBackground = true)
@Composable
private fun AnimationScreenPreview() {
    DiveTheme {
        AnimationScreen()
    }
}
