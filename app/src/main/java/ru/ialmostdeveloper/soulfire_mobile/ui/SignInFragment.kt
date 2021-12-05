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

class SignInFragment : Fragment() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signInButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        apiClient = ApiClient()
        sessionManager = SessionManager(this.requireContext())
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
        usernameEditText = view.findViewById(R.id.usernameInput)
        passwordEditText = view.findViewById(R.id.passwordInput)
        signInButton = view.findViewById(R.id.signInButton)
        signInButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            login(username, password)
        }

        return view
    }

    private fun login(username: String, password: String) {
        apiClient.getApiService().login(SignInRequest(username = username, password = password))
            .enqueue(object : Callback<SignInResponse> {
                override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                    var textView = view?.findViewById<TextView>(R.id.text_sign_in)
                    if (textView != null) {
                        textView.text = t.message
                    }
                }

                override fun onResponse(
                    call: Call<SignInResponse>,
                    response: Response<SignInResponse>
                ) {
                    val loginResponse = response.body()

                    if (loginResponse?.message == "Success") {
                        sessionManager.saveAuthToken(loginResponse.token)
                        var textView = view?.findViewById<TextView>(R.id.text_sign_in)
                        if (textView != null) {
                            textView.text = loginResponse.token
                        }
                    }
                }
            })
    }
}