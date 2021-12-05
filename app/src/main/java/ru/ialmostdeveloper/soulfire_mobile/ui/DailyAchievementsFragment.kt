package ru.ialmostdeveloper.soulfire_mobile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.ialmostdeveloper.soulfire_mobile.R
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager
import ru.ialmostdeveloper.soulfire_mobile.network.models.AchievementsResponse
import ru.ialmostdeveloper.soulfire_mobile.ui.models.Achievement
import ru.ialmostdeveloper.soulfire_mobile.ui.models.AchievementsAdapter

class DailyAchievementsFragment : Fragment() {

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    private lateinit var arrayAdapter: AchievementsAdapter
    private lateinit var listView: ListView
    private lateinit var listElements: MutableList<ru.ialmostdeveloper.soulfire_mobile.ui.models.Achievement>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        apiClient = ApiClient()
        sessionManager = SessionManager(this.requireContext())

        val view = inflater.inflate(R.layout.fragment_daily_achievements, container, false)

        listElements = mutableListOf()
        listView = view.findViewById(R.id.listView)
        arrayAdapter = AchievementsAdapter(this.requireContext(), listElements)
        listView.adapter = arrayAdapter

        apiClient.getApiService().getAchievements(token = "Bearer ${sessionManager.fetchAuthToken()}")
            .enqueue(object : Callback<AchievementsResponse> {
                override fun onFailure(call: Call<AchievementsResponse>, t: Throwable) {
                    // Error fetching posts
                }

                override fun onResponse(call: Call<AchievementsResponse>, response: Response<AchievementsResponse>) {
                    var achievements = response.body()?.achievements
                    achievements?.forEach {
                        listElements.add(Achievement(R.drawable.ic_home_black_24dp, it.name, it.description))
                        arrayAdapter.notifyDataSetChanged()
                    }
                }
            })

        return view
    }
}