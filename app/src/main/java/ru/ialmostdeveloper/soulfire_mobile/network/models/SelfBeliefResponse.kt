package ru.ialmostdeveloper.soulfire_mobile.network.models

import com.google.gson.annotations.SerializedName

data class SelfBeliefResponse(
    @SerializedName("status")
    val status: Int,

    @SerializedName("content")
    val content: SelfBelief
)
