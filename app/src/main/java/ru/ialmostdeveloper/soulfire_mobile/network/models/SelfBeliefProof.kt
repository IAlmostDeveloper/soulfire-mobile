package ru.ialmostdeveloper.soulfire_mobile.network.models

import com.google.gson.annotations.SerializedName

data class SelfBeliefProof(
    @SerializedName("id")
    val id: String,

    @SerializedName("selfBeliefId")
    val selfBeliefId: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("content")
    val content: String
)
