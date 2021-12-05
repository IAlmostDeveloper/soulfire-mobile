package ru.ialmostdeveloper.soulfire_mobile.network


import retrofit2.Call
import retrofit2.http.*
import ru.ialmostdeveloper.soulfire_mobile.Constants
import ru.ialmostdeveloper.soulfire_mobile.network.models.SignInRequest
import ru.ialmostdeveloper.soulfire_mobile.network.models.SignInResponse
import ru.ialmostdeveloper.soulfire_mobile.network.models.PostsResponse


interface APIService {
    @POST(Constants.LOGIN_URL)
//    @FormUrlEncoded
    fun login(@Body request: SignInRequest): Call<SignInResponse>

    @GET(Constants.POSTS_URL)
    fun fetchPosts(@Header("Authorization") token: String): Call<PostsResponse>
}
