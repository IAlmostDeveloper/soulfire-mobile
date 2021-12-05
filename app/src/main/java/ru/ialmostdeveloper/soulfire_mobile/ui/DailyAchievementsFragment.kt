package ru.ialmostdeveloper.soulfire_mobile.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.ialmostdeveloper.soulfire_mobile.R
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager
import ru.ialmostdeveloper.soulfire_mobile.network.models.Achievement
import ru.ialmostdeveloper.soulfire_mobile.network.models.AchievementsResponse
import ru.ialmostdeveloper.soulfire_mobile.network.models.PostsResponse

class DailyAchievementsFragment : Fragment() {

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var listView: ListView
    private lateinit var listElements: MutableList<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        apiClient = ApiClient()
        sessionManager = SessionManager(this.requireContext())

        val view = inflater.inflate(R.layout.fragment_daily_achievements, container, false)
        val textView = view.findViewById<TextView>(R.id.text_daily_achievements)

        listElements = mutableListOf()
        listView = view.findViewById(R.id.listView)
        arrayAdapter = ArrayAdapter(this.requireContext(), android.R.layout.simple_list_item_1, listElements)
        listView.adapter = arrayAdapter

        apiClient.getApiService().getAchievements(token = "Bearer ${sessionManager.fetchAuthToken()}")
            .enqueue(object : Callback<AchievementsResponse> {
                override fun onFailure(call: Call<AchievementsResponse>, t: Throwable) {
                    // Error fetching posts
                   textView.text = t.message
                }

                override fun onResponse(call: Call<AchievementsResponse>, response: Response<AchievementsResponse>) {
                    textView.text = response.body().toString()
                    var achievements = response.body()?.achievements
                    achievements?.forEach {
                        listElements.add(it.name)
                        arrayAdapter.notifyDataSetChanged()
                    }
                }
            })

        return view
    }
}