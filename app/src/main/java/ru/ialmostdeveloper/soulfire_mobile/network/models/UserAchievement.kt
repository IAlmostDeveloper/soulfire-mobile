package ru.ialmostdeveloper.soulfire_mobile.network.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserAchievement (
    @SerializedName("id")
    var id: Int,

    @SerializedName("userId")
    var userId: String,

    @SerializedName("achievementId")
    var achievementId: String,

    @SerializedName("date")
    var date: Date,

    @SerializedName("achievement")
    var achievement: Achievement
)