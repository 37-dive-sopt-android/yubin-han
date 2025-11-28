package com.sopt.dive.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.components.FeedItemCard
import com.sopt.dive.components.ProfileHeader
import com.sopt.dive.data.util.UiState
import com.sopt.dive.domain.model.FeedItem
import com.sopt.dive.domain.model.FeedItem.Companion.dummyFeeds
import com.sopt.dive.domain.model.ProfileSummary
import com.sopt.dive.ui.theme.DiveTheme


@Composable
fun HomeRoute(
    contentPadding: PaddingValues,
    viewModel: HomeViewModel = viewModel()
) {
    val feedState by viewModel.feedState.collectAsState()
    val profileState by viewModel.profileState.collectAsState() // 프로필 상태 관찰

    // 두 데이터가 모두 준비되었는지 확인
    if (feedState is UiState.Success && profileState is UiState.Success) {

        val feeds = (feedState as UiState.Success).data
        val userInfo = (profileState as UiState.Success).data

        val mappedProfile = ProfileSummary(
            username = userInfo.username,
            statusMessage = userInfo.status,
            profileImageResId = R.drawable.yangpa
        )

        HomeScreen(
            feedList = feeds,
            userProfile = mappedProfile, // 변환된 프로필 전달
            contentPadding = contentPadding
        )

    } else if (feedState is UiState.Loading || profileState is UiState.Loading) {
        // 둘 중 하나라도 로딩 중이면 로딩 표시
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        // 에러 처리 (간단하게)
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "데이터를 불러오지 못했습니다.")
        }
    }
}

@Composable
fun HomeScreen(
    feedList: List<FeedItem>,
    userProfile: ProfileSummary,
    contentPadding: PaddingValues,
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 20.dp),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        //사용자 프로필 헤더
        item {
            ProfileHeader(profile = userProfile)
            Spacer(modifier = Modifier.height(30.dp))
        }


        //피드목록 피드아이템 리스트를 기반으로 아이템 반복 생성하는 코드
        items(items = feedList, key = { it.userName }) { feed ->
            FeedItemCard(feed = feed)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    DiveTheme {
        HomeScreen(
            feedList = dummyFeeds,
            userProfile = ProfileSummary(
                "yukong", "하이", R.drawable.yangpa
            ),
            contentPadding = PaddingValues()
        )
    }
}
