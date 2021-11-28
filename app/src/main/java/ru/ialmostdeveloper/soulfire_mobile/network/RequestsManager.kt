package ru.ialmostdeveloper.soulfire_mobile.network

import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import java.lang.StringBuilder
import java.util.*


class RequestsManager(private val service: APIService) {

    fun auth(login: String?, password: String?): String {
        val requestBody = JSONObject()
        try {
            requestBody.put("login", login)
            requestBody.put("password", password)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val bodyRequest: RequestBody =
            RequestBody.create(MediaType.parse("application/json"), requestBody.toString())
        val call: Call<ResponseBody?>? = service.auth(bodyRequest)
        try {
            val response: Response<ResponseBody?> = call!!.execute()
            if (response.code() === 200) {
                val bodyraw: String = response.body()!!.string()
                val responseBody = JSONObject(bodyraw)
                val token = responseBody["token"].toString()
                val error = responseBody["error"].toString()
                return if (error == "") token else ""
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return ""
    }

    fun register(login: String?, password: String?): Boolean {
        val requestBody = JSONObject()
        try {
            requestBody.put("login", login)
            requestBody.put("password", password)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val bodyRequest: RequestBody =
            RequestBody.create(MediaType.parse("application/json"), requestBody.toString())
        val call: Call<ResponseBody?>? = service.register(bodyRequest)
        try {
            val response: Response<ResponseBody?> = call!!.execute()
            if (response.code() === 200) {
                val bodyraw: String = response.body()!!.string()
                val responseBody = JSONObject(bodyraw)
                val error = responseBody["error"].toString()
                return error == ""
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return false
    }

    fun send(id: Int, code: String?, encoding: String?, token: String?): Boolean {
        val requestBody = JSONObject()
        try {
            requestBody.put("id", id)
            requestBody.put("code", code)
            requestBody.put("encoding", encoding)
            requestBody.put("token", token)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val bodyRequest: RequestBody =
            RequestBody.create(MediaType.parse("application/json"), requestBody.toString())
        val call: Call<ResponseBody?>? = service.send(bodyRequest)
        try {
            val response: Response<ResponseBody?> = call!!.execute()
            if (response.code() === 200) {
                return true
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return false
    }

    fun deleteController(controllerName: String?, login: String?, token: String?): Boolean {
        val requestBody = JSONObject()
        try {
            requestBody.put("token", token)
            requestBody.put("name", controllerName)
            requestBody.put("user", login)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val bodyRequest: RequestBody =
            RequestBody.create(MediaType.parse("application/json"), requestBody.toString())
        val call: Call<ResponseBody?>? = service.deleteController(bodyRequest)
        try {
            val response: Response<ResponseBody?> = call!!.execute()
            if (response.code() === 200) {
                val bodyraw: String = response.body()!!.string()
                val responseBody = JSONObject(bodyraw)
                println(responseBody.toString())
                return true
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return false
    }

    fun executeScript(id: Int, token: String?): Boolean {
        val requestBody = JSONObject()
        val buttons = StringBuilder()
        try {
            requestBody.put("token", token)
            requestBody.put("id", id)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val bodyRequest: RequestBody =
            RequestBody.create(MediaType.parse("application/json"), requestBody.toString())
        val call: Call<ResponseBody?>? = service.executeScript(bodyRequest)
        try {
            val response: Response<ResponseBody?> = call!!.execute()
            if (response.code() === 200) {
                return true
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return false
    }

    fun deleteScript(token: String?, user: String?, name: String?): Boolean {
        val requestBody = JSONObject()
        val buttons = StringBuilder()
        try {
            requestBody.put("token", token)
            requestBody.put("user", user)
            requestBody.put("name", name)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val bodyRequest: RequestBody =
            RequestBody.create(MediaType.parse("application/json"), requestBody.toString())
        val call: Call<ResponseBody?>? = service.deleteScript(bodyRequest)
        try {
            val response: Response<ResponseBody?> = call!!.execute()
            if (response.code() === 200) {
                return true
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return false
    }
}
