package com.sopt.dive.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.components.ProfileInfoItemComponent
import com.sopt.dive.data.FeedItem
import com.sopt.dive.data.ProfileSummary
import com.sopt.dive.ui.theme.DiveTheme


//더미
val dummyProfile = ProfileSummary("yukong", "하이방가방가", R.drawable.yangpa) // R.drawable.yangpa는 가정한 리소스 ID
val dummyFeeds = listOf(
    FeedItem("한유빈", "집가고 싶다", R.drawable.yangpa),
    FeedItem("유콩", "하루종일 놀고싶다", R.drawable.yangpa),
    FeedItem("쿵야", "붕어빵 먹고싶다", R.drawable.yangpa),
    // ... 실제 데이터는 더 많을 것
)
val dummyStats = listOf("게시물 12", "팔로워 300", "팔로잉 400", "좋아요 100")

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding=PaddingValues(top = 16.dp, bottom = 80.dp) // BottomBar 공간 확보
    ) {

        //사용자 프로필 헤더
        item {
            ProfileInfoItemComponent(dummyProfile.nickname,dummyProfile.statusMessage,"자기소개")
            Spacer(modifier = Modifier.height(20.dp))
        }

        //프로필 관련 컴포넌트
        item {
            // LazyVerticalGrid: 유저 프로필 통계 정보를 2열 그리드로 표시
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // 2열 그리드
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp) // 높이를 명시적으로 지정하거나, 내부 컨텐츠에 따라 조절해야 함
            ) {
                items(dummyStats) { stat ->
                    StatCard(text=stat)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        //피드목록 피드아이템 리스트를 기반으로 아이템 반복 생성하는 코드

        items(dummyFeeds.size) { index ->
            // 더미 데이터가 부족하여 반복적으로 표시합니다.
            val feed = dummyFeeds[index % dummyFeeds.size]
            FeedItemCard(feed=feed)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}


// --- 재사용 가능한 Compose Component ---

// Home 화면 상단 프로필 요약
@Composable
fun ProfileHeaderComponent(profile: ProfileSummary) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = profile.profileImageResId),
            contentDescription = "프로필 사진",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Gray) // 이미지 없으면 회색 배경
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = profile.nickname, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = profile.statusMessage, fontSize = 16.sp, color = Color.Gray)
        }
    }
}

// LazyVerticalGrid 내부에 들어갈 통계 카드
@Composable
fun StatCard(text: String) {
    Card(
        modifier = Modifier.padding(4.dp).fillMaxHeight(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(8.dp), contentAlignment = Alignment.Center) {
            Text(text = text, fontSize = 14.sp)
        }
    }
}

// Feed 목록 아이템 (image_7b62a0.jpg의 하단 목록 스타일)
@Composable
fun FeedItemCard(feed: FeedItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)) // 배경색 변경
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = feed.profileImageResId),
                contentDescription = "유저 프로필",
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = feed.userNickname, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = feed.content, fontSize = 14.sp, color = Color.DarkGray)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Week1assignmentPreview() {
    DiveTheme {
        HomeScreen()
    }
}
