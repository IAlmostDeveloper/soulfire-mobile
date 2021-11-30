package ru.ialmostdeveloper.soulfire_mobile.network

class RequestsManager(private val service: APIService) {

    fun auth(login: String?, password: String?): String {
//        val requestBody = JSONObject()
//        try {
//            requestBody.put("login", login)
//            requestBody.put("password", password)
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        val bodyRequest: RequestBody =
//            RequestBody.create(MediaType.parse("application/json"), requestBody.toString())
//        val call: Call<ResponseBody?>? = service.login(bodyRequest)
//        try {
//            val response: Response<ResponseBody?> = call!!.execute()
//            if (response.code() === 200) {
//                val bodyraw: String = response.body()!!.string()
//                val responseBody = JSONObject(bodyraw)
//                val token = responseBody["token"].toString()
//                val error = responseBody["error"].toString()
//                return if (error == "") token else ""
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        return ""
        return "null"
    }

    fun register(login: String?, password: String?): Boolean {
//        val requestBody = JSONObject()
//        try {
//            requestBody.put("login", login)
//            requestBody.put("password", password)
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        val bodyRequest: RequestBody =
//            RequestBody.create(MediaType.parse("application/json"), requestBody.toString())
//        val call: Call<ResponseBody?>? = service.register(bodyRequest)
//        try {
//            val response: Response<ResponseBody?> = call!!.execute()
//            if (response.code() === 200) {
//                val bodyraw: String = response.body()!!.string()
//                val responseBody = JSONObject(bodyraw)
//                val error = responseBody["error"].toString()
//                return error == ""
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        return false
        return false;
    }
}
