package ru.ialmostdeveloper.soulfire_mobile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.ialmostdeveloper.soulfire_mobile.R
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager

class DailyAchievementsFragment : Fragment() {

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        apiClient = ApiClient()
        sessionManager = SessionManager(this.requireContext())
        var view = inflater.inflate(R.layout.fragment_daily_achievements, container, false)



        return view
    }
}