package ru.ialmostdeveloper.soulfire_mobile.network.models

import com.google.gson.annotations.SerializedName

data class SignUpResponse (
    @SerializedName("username")
    var username: String,

    @SerializedName("password")
    var password: String,
)