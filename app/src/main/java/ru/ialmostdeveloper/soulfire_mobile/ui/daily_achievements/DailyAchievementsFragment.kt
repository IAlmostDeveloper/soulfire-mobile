package ru.ialmostdeveloper.soulfire_mobile.ui.daily_achievements

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.ialmostdeveloper.soulfire_mobile.R
import ru.ialmostdeveloper.soulfire_mobile.databinding.FragmentDashboardBinding
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient
import ru.ialmostdeveloper.soulfire_mobile.network.LoginRequest
import ru.ialmostdeveloper.soulfire_mobile.network.LoginResponse
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager

class DailyAchievementsFragment : Fragment() {

    private lateinit var dashboardViewModel: DailyAchievementsViewModel
    private var _binding: FragmentDashboardBinding? = null

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        apiClient = ApiClient()
        sessionManager = SessionManager(this.requireContext())
        dashboardViewModel =
            ViewModelProvider(this).get(DailyAchievementsViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}