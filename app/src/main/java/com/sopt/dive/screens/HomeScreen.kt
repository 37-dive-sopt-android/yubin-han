package com.sopt.dive.screens

import android.provider.ContactsContract
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
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.rememberCarouselState
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
import com.sopt.dive.components.ProfileHeaderComponent
import com.sopt.dive.data.FeedItem
import com.sopt.dive.data.ProfileSummary
import com.sopt.dive.data.dummyFeeds
import com.sopt.dive.ui.theme.DiveTheme




@Composable
fun HomeScreen(
    userNickname: String, contentPadding: PaddingValues
) {
    val userProfile = ProfileSummary(
        nickname = userNickname,
        statusMessage = "하이루방가",
        profileImageResId = R.drawable.yangpa
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        //사용자 프로필 헤더
        item {
            ProfileHeaderComponent(profile = userProfile)
            Spacer(modifier = Modifier.height(30.dp))
        }


        //피드목록 피드아이템 리스트를 기반으로 아이템 반복 생성하는 코드

        items(
            dummyFeeds.size,
            key = { index ->
                dummyFeeds[index].userNickname
            }

        ) { index ->
            val feed = dummyFeeds[index]
            FeedItemCard(feed = feed)
        }
    }
}


@Composable
fun FeedItemCard(feed: FeedItem,modifier: Modifier=Modifier) {
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
                Text(
                    text = feed.userNickname,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = feed.content,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    DiveTheme {
        HomeScreen(userNickname = "유콩이야이야", contentPadding())
    }
}
