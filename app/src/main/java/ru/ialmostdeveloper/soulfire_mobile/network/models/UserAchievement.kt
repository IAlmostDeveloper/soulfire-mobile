package ru.ialmostdeveloper.soulfire_mobile.network.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserAchievement (
    @SerializedName("id")
    var id: String,

    @SerializedName("userId")
    var userId: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("content")
    var content: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("updatedDate")
    var updatedDate: String,
)