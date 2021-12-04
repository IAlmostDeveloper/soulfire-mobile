package ru.ialmostdeveloper.soulfire_mobile.network

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("Username")
    var username: String,

    @SerializedName("password")
    var password: String
)