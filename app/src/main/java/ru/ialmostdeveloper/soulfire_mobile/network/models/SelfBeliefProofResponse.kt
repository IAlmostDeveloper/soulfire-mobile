package ru.ialmostdeveloper.soulfire_mobile.network.models

import com.google.gson.annotations.SerializedName

data class SelfBeliefProofResponse(
    @SerializedName("status")
    val status: Int,

    @SerializedName("content")
    val content: SelfBeliefProof
)
