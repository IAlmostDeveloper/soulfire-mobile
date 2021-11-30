package ru.ialmostdeveloper.soulfire_mobile.network


import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import ru.ialmostdeveloper.soulfire_mobile.Constants


interface APIService {
    @POST(Constants.LOGIN_URL)
    @FormUrlEncoded
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET(Constants.POSTS_URL)
    fun fetchPosts(@Header("Authorization") token: String): Call<PostsResponse>
}
