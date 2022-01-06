package ru.ialmostdeveloper.soulfire_mobile.network.models

import com.google.gson.annotations.SerializedName

data class DiaryNotesResponse(
    @SerializedName("status")
    val status: Int,

    @SerializedName("content")
    val content: Array<DiaryNote>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DiaryNotesResponse

        if (status != other.status) return false
        if (!content.contentEquals(other.content)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = status
        result = 31 * result + content.contentHashCode()
        return result
    }
}
