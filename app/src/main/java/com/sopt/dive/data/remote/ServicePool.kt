package com.sopt.dive.data.remote

import com.sopt.dive.data.service.UserApiService

object ServicePool {
    val userService: UserApiService by lazy {
        ApiFactory.create<UserApiService>()
    }
}
