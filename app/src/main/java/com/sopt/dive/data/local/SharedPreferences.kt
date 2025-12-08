package com.sopt.dive.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object DiveSharedPreferences {
    private const val PREFS_NAME = "dive_prefs"
    private const val IS_LOGIN = "is_login"

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    // 로그인 상태 저장/가져오기
    var isLogin: Boolean
        get() = prefs.getBoolean(IS_LOGIN, false) // 기본값은 false (비로그인)
        set(value) = prefs.edit { putBoolean(IS_LOGIN, value).apply() }

}
