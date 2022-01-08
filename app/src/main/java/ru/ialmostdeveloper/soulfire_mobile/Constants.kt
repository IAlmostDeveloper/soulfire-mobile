package ru.ialmostdeveloper.soulfire_mobile

object Constants {

    // Endpoints
    const val BASE_URL = "https://8c65-188-17-163-69.ngrok.io/api/"
    const val LOGIN_URL = "auth/login"
    const val REGISTER_URL = "auth/register"

    const val GET_DIARY_NOTES_URL = "diary/{id}"
    const val ADD_DIARY_NOTE_URL = "diary"
    const val UPDATE_DIARY_NOTE_URL = "diary/{id}"
    const val DELETE_DIARY_NOTE_URL = "diary/{id}"

    const val GET_USER_ACHIEVEMENTS_URL = "user/achievements/{id}"
    const val ADD_USER_ACHIEVEMENT_URL = "user/achievements"
    const val UPDATE_USER_ACHIEVEMENT_URL = "user/achievements/{id}"
    const val DELETE_USER_ACHIEVEMENT_URL = "user/achievements/{id}"

    const val GET_USER_PRESETS_URL = "presets/{id}"
    const val GET_ALL_PRESETS_URL = "presets"
    const val ADD_USER_PRESET_URL = "presets"
    const val UPDATE_USER_PRESET_URL = "presets/{id}"
    const val DELETE_USER_PRESET_URL = "presets/{id}"

}