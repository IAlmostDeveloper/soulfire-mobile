package ru.ialmostdeveloper.soulfire_mobile.network

import com.google.gson.annotations.SerializedName

data class LoginResponse (

    @SerializedName("token")
    var authToken: String,

    @SerializedName("message")
    var message: String
)