package ru.ialmostdeveloper.soulfire_mobile.network.models

import com.google.gson.annotations.SerializedName

data class AchievementsResponse (
    @SerializedName("status_code")
    var status: Int,

    @SerializedName("achievements")
    var achievements: List<Achievement>
)