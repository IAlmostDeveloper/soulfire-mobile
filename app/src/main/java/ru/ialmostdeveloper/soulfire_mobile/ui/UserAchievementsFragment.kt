package ru.ialmostdeveloper.soulfire_mobile.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.ialmostdeveloper.soulfire_mobile.R
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager
import ru.ialmostdeveloper.soulfire_mobile.network.models.UserAchievementsResponse
import ru.ialmostdeveloper.soulfire_mobile.ui.models.Achievement
import ru.ialmostdeveloper.soulfire_mobile.ui.models.AchievementsAdapter

class UserAchievementsFragment : Fragment() {

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    private lateinit var arrayAdapter: AchievementsAdapter
    private lateinit var listView: ListView
    private lateinit var listElements: MutableList<Achievement>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        apiClient = ApiClient()
        sessionManager = SessionManager(this.requireContext())

        val view = inflater.inflate(R.layout.fragment_user_achievements, container, false)

        listElements = mutableListOf()
        listView = view.findViewById(R.id.userAchievementsListView)
        arrayAdapter = AchievementsAdapter(this.requireContext(), listElements)
        listView.adapter = arrayAdapter

        apiClient.getApiService().getUserAchievements(
            token = "Bearer ${sessionManager.fetchAuthToken()}",
            id = sessionManager.fetchUserId()!!
        )
            .enqueue(object : Callback<UserAchievementsResponse> {
                override fun onFailure(call: Call<UserAchievementsResponse>, t: Throwable) {
                    Log.d("123123123", t.message.toString())
                }

                override fun onResponse(
                    call: Call<UserAchievementsResponse>,
                    response: Response<UserAchievementsResponse>
                ) {
                    var userAchievements = response.body()?.userAchievements
                    userAchievements?.forEach {
                        listElements.add(
                            Achievement(
                                R.drawable.ic_home_black_24dp,
                                it.achievement.name,
                                it.achievement.description
                            )
                        )
                        arrayAdapter.notifyDataSetChanged()
                    }
                }
            })

        return view
    }
}