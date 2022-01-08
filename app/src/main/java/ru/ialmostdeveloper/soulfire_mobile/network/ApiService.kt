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
    fun register(@Body request: SignUpRequest): Call<SignUpResponse>

    @GET(Constants.GET_DIARY_NOTES_URL)
    fun getDiaryNotes(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Call<DiaryNotesResponse>

    @POST(Constants.ADD_DIARY_NOTE_URL)
    fun addDiaryNote(
        @Header("Authorization") token: String,
        @Body request: AddDiaryNoteRequest
    ): Call<DiaryNoteResponse>

    @PATCH(Constants.UPDATE_DIARY_NOTE_URL)
    fun updateDiaryNote(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body request: UpdateNoteRequest
    ): Call<DiaryNoteResponse>

    @DELETE(Constants.DELETE_DIARY_NOTE_URL)
    fun deleteDiaryNote(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Call<DiaryNoteResponse>

    @GET(Constants.GET_USER_ACHIEVEMENTS_URL)
    fun getUserAchievements(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Call<UserAchievementsResponse>

    @POST(Constants.ADD_USER_ACHIEVEMENT_URL)
    fun addUserAchievement(
        @Header("Authorization") token: String,
        @Body userAchievement: UserAchievement
    ): Call<UserAchievementResponse>

    @PATCH(Constants.UPDATE_USER_ACHIEVEMENT_URL)
    fun updateUserAchievement(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body userAchievement: UserAchievement
    ): Call<UserAchievementResponse>

    @DELETE(Constants.DELETE_USER_ACHIEVEMENT_URL)
    fun deleteUserAchievement(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Call<UserAchievementResponse>

    @GET(Constants.GET_USER_PRESETS_URL)
    fun getUserPresets()

    @GET(Constants.GET_ALL_PRESETS_URL)
    fun getAllPresets()

    @POST(Constants.ADD_USER_PRESET_URL)
    fun addUserPreset()

    @PATCH(Constants.UPDATE_USER_PRESET_URL)
    fun updateUserPreset()

    @DELETE(Constants.DELETE_USER_PRESET_URL)
    fun deleteUserPreset()
}
