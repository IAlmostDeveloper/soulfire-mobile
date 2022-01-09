package ru.ialmostdeveloper.soulfire_mobile.network.models

import com.google.gson.annotations.SerializedName

data class SelfBelief(
    @SerializedName("id")
    val id: String,

    @SerializedName("userId")
    val userId: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("selfBeliefProofs")
    val selfBeliefProofs: Array<SelfBeliefProof>,

    @SerializedName("userAnswers")
    val userAnswers: Array<UserAnswer>

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SelfBelief

        if (id != other.id) return false
        if (userId != other.userId) return false
        if (title != other.title) return false
        if (content != other.content) return false
        if (!selfBeliefProofs.contentEquals(other.selfBeliefProofs)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + userId.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + selfBeliefProofs.contentHashCode()
        return result
    }
}
