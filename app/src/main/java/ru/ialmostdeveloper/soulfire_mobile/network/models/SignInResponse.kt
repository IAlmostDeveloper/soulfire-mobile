package ru.ialmostdeveloper.soulfire_mobile.network.models

import com.google.gson.annotations.SerializedName

data class SignInResponse (

    @SerializedName("token")
    var token: String,

    @SerializedName("message")
    var message: String
)