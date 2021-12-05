package ru.ialmostdeveloper.soulfire_mobile.network

import android.content.Context
import android.content.SharedPreferences
import ru.ialmostdeveloper.soulfire_mobile.R

class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_NAME = "user_name"
        const val USER_ID = "user_id"
    }

    fun saveUserCredentials(credentials: UserCredentials) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, credentials.token)
        editor.putString(USER_NAME, credentials.username)
        editor.putString(USER_ID, credentials.userId)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun fetchUserId(): String? {
        return prefs.getString(USER_ID, null)
    }

    fun fetchUserName(): String? {
        return prefs.getString(USER_NAME, null)
    }
}