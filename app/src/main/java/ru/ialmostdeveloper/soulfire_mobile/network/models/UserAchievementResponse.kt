package ru.ialmostdeveloper.soulfire_mobile.network.models

import com.google.gson.annotations.SerializedName

data class UserAchievementResponse (
    @SerializedName("status")
    var status : Int,

    @SerializedName("result")
    var result: UserAchievement,
        )