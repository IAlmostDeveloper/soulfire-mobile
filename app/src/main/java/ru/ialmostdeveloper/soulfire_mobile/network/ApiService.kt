package ru.ialmostdeveloper.soulfire_mobile.network


import retrofit2.Call
import retrofit2.http.*
import ru.ialmostdeveloper.soulfire_mobile.Constants
import ru.ialmostdeveloper.soulfire_mobile.network.models.*


interface APIService {
    @POST(Constants.LOGIN_URL)
//    @FormUrlEncoded
    fun login(@Body request: SignInRequest): Call<SignInResponse>

    @POST(Constants.REGISTER_URL)
    fun register(@Body request: SignUpRequest) : Call<SignUpResponse>

    @GET(Constants.ACHIEVEMENTS_URL)
    fun getAchievements(@Header("Authorization") token: String): Call<AchievementsResponse>

    @GET(Constants.USER_ACHIEVEMENTS_URL)
    fun getUserAchievements(@Header("Authorization") token: String, @Path("id") id: String): Call<UserAchievementsResponse>

    @GET(Constants.POSTS_URL)
    fun fetchPosts(@Header("Authorization") token: String): Call<PostsResponse>
}
