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
    fun getUserPresets(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Call<PresetsResponse>

    @GET(Constants.GET_ALL_PRESETS_URL)
    fun getAllPresets(@Header("Authorization") token: String): Call<PresetsResponse>

    @POST(Constants.ADD_USER_PRESET_URL)
    fun addUserPreset(
        @Header("Authorization") token: String,
        @Body preset: Preset
    ): Call<PresetResponse>

    @PATCH(Constants.UPDATE_USER_PRESET_URL)
    fun updateUserPreset(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body preset: Preset
    ): Call<PresetResponse>

    @DELETE(Constants.DELETE_USER_PRESET_URL)
    fun deleteUserPreset(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Call<PresetResponse>

    @GET(Constants.GET_SELF_BELIEFS_URL)
    fun getSelfBeliefs(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Call<SelfBeliefsResponse>

    @POST(Constants.ADD_SELF_BELIEF_URL)
    fun addSelfBelief(
        @Header("Authorization") token: String,
        @Body selfBelief: SelfBelief
    ): Call<SelfBeliefResponse>

    @PATCH(Constants.UPDATE_SELF_BELIEF_URL)
    fun updateSelfBelief(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body selfBelief: SelfBelief
    ): Call<SelfBeliefResponse>

    @DELETE(Constants.DELETE_SELF_BELIEF_URL)
    fun deleteSelfBelief(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Call<SelfBeliefResponse>
}
