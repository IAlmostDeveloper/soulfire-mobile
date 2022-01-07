package ru.ialmostdeveloper.soulfire_mobile.network.models

import com.google.gson.annotations.SerializedName

data class UserAchievementsResponse (
    @SerializedName("status")
    var status : Int,

    @SerializedName("userAchievements")
    var userAchievements: Array<UserAchievement>,
        ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserAchievementsResponse

        if (status != other.status) return false
        if (!userAchievements.contentEquals(other.userAchievements)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = status
        result = 31 * result + userAchievements.contentHashCode()
        return result
    }
}