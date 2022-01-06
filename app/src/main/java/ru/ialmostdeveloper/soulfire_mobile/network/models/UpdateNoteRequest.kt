package ru.ialmostdeveloper.soulfire_mobile.network.models

import com.google.gson.annotations.SerializedName

data class UpdateNoteRequest(
    @SerializedName("content")
    val content: String
)
