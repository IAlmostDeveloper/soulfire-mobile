package ru.ialmostdeveloper.soulfire_mobile.ui.daily_achievements

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DailyAchievementsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is daily achievements Fragment"
    }
    val text: LiveData<String> = _text
}