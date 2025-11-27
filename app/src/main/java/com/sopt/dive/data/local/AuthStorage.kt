package com.sopt.dive.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object AuthStorage {
    private const val PREF_NAME = "DiveAuthPrefs"
    private const val KEY_USER_ID = "USER_ID"

    private lateinit var preferences: SharedPreferences

    // 앱 시작할 때 딱 한 번 초기화
    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    // 아이디 저장 (로그인/회원가입 성공 시 호출)
    fun setUserId(id: Long) {
        preferences.edit { putLong(KEY_USER_ID, id).apply() }
    }

    // 아이디 꺼내기 (홈/마이에서 호출)
    // 저장된 게 없으면 -1 반환
    fun getUserId(): Long {
        return preferences.getLong(KEY_USER_ID, -1L)
    }

    // 로그아웃 시 삭제
    fun clear() {
        preferences.edit {
            clear().apply()
        }
    }
}
