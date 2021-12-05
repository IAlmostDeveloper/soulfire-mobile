package ru.ialmostdeveloper.soulfire_mobile.network.models

import com.google.gson.annotations.SerializedName

data class UserAchievementsResponse (
    @SerializedName("status")
    var status : Int,

    @SerializedName("userAchievements")
    var userAchievements: List<UserAchievement>,
        )