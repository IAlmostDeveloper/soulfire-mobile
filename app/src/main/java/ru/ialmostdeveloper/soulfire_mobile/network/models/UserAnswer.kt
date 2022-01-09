package ru.ialmostdeveloper.soulfire_mobile.network.models

import com.google.gson.annotations.SerializedName

data class UserAnswer(
    @SerializedName("id")
    val id: String,

    @SerializedName("selfBeliefId")
    val selfBeliefId: String,

    @SerializedName("question")
    val question: String,

    @SerializedName("answer")
    val answer: String,

    @SerializedName("date")
    val date: String
)
