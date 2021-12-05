package ru.ialmostdeveloper.soulfire_mobile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.ialmostdeveloper.soulfire_mobile.R
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient
import ru.ialmostdeveloper.soulfire_mobile.network.models.SignInRequest
import ru.ialmostdeveloper.soulfire_mobile.network.models.SignInResponse
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager
import ru.ialmostdeveloper.soulfire_mobile.network.UserCredentials

class QuizFragment : Fragment() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        apiClient = ApiClient()
        sessionManager = SessionManager(this.requireContext())
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)

        return view
    }
}