package ru.ialmostdeveloper.soulfire_mobile.network.models

import com.google.gson.annotations.SerializedName

data class SignUpRequest (
    @SerializedName("Username")
    var username: String,

    @SerializedName("password")
    var password: String,

    @SerializedName("characterType")
    var characterType: String,

    @SerializedName("autoThoughts")
    var autoThoughts: Array<String>,

    @SerializedName("middleThoughts")
    var middleThoughts: Array<String>,

    @SerializedName("deepThoughts")
    var deepThoughts: Array<String>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SignUpRequest

        if (username != other.username) return false
        if (password != other.password) return false
        if (!autoThoughts.contentEquals(other.autoThoughts)) return false
        if (!middleThoughts.contentEquals(other.middleThoughts)) return false
        if (!deepThoughts.contentEquals(other.deepThoughts)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + autoThoughts.contentHashCode()
        result = 31 * result + middleThoughts.contentHashCode()
        result = 31 * result + deepThoughts.contentHashCode()
        return result
    }
}