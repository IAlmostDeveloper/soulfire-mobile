package ru.ialmostdeveloper.soulfire_mobile.network


import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query


interface APIService {
    @Headers("Content-Type: application/json; charset=utf-8")
    @POST("/auth")
    fun auth(@Body body: RequestBody?): Call<ResponseBody?>?

    @Headers("Content-Type: application/json; charset=utf-8")
    @POST("/register")
    fun register(@Body body: RequestBody?): Call<ResponseBody?>?

    @Headers("Content-Type: application/json; charset=utf-8")
    @POST("/send")
    fun send(@Body body: RequestBody?): Call<ResponseBody?>?

    @POST("/receive")
    fun receive(@Body body: RequestBody?): Call<ResponseBody?>?

    @GET("/receivedcode")
    fun receivedCode(
        @Query("token") token: String?,
        @Query("key") key: String?
    ): Call<ResponseBody?>?

    @GET("/controllers")
    fun controllers(
        @Query("user") user: String?,
        @Query("token") token: String?
    ): Call<ResponseBody?>?

    @POST("/add/controller")
    fun addController(@Body body: RequestBody?): Call<ResponseBody?>?

    @POST("/update/controller")
    fun updateController(@Body body: RequestBody?): Call<ResponseBody?>?

    @POST("/delete/controller")
    fun deleteController(@Body body: RequestBody?): Call<ResponseBody?>?

    @GET("/userscripts")
    fun userScripts(
        @Query("user") user: String?,
        @Query("token") token: String?
    ): Call<ResponseBody?>?

    @POST("/script")
    fun addScript(@Body body: RequestBody?): Call<ResponseBody?>?

    @POST("/execute")
    fun executeScript(@Body body: RequestBody?): Call<ResponseBody?>?

    @POST("/delete/script")
    fun deleteScript(@Body body: RequestBody?): Call<ResponseBody?>?
}
