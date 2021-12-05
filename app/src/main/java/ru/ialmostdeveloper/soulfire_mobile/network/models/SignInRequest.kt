package ru.ialmostdeveloper.soulfire_mobile.network.models

import com.google.gson.annotations.SerializedName

data class SignInRequest (
    @SerializedName("Username")
    var username: String,

    @SerializedName("password")
    var password: String
)