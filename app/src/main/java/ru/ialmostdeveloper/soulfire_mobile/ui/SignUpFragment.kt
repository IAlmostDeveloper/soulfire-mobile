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
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager
import ru.ialmostdeveloper.soulfire_mobile.network.models.SignInRequest
import ru.ialmostdeveloper.soulfire_mobile.network.models.SignInResponse
import ru.ialmostdeveloper.soulfire_mobile.network.models.SignUpRequest
import ru.ialmostdeveloper.soulfire_mobile.network.models.SignUpResponse

class SignUpFragment : Fragment() {

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signUpButton: Button

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        apiClient = ApiClient()
        sessionManager = SessionManager(this.requireContext())

        val view : View = inflater.inflate(R.layout.fragment_sign_up, container, false)

        usernameEditText = view.findViewById(R.id.usernameInput)
        passwordEditText = view.findViewById(R.id.passwordInput)
        signUpButton = view.findViewById(R.id.signInButton)
        signUpButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            register(username, password)
        }

        return view
    }

    private fun register(username: String, password: String) {
        apiClient.getApiService().register(SignUpRequest(username = username, password = password))
            .enqueue(object : Callback<SignUpResponse> {
                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    var textView = view?.findViewById<TextView>(R.id.text_sign_up)
                    if (textView != null) {
                        textView.text = t.message
                    }
                }

                override fun onResponse(
                    call: Call<SignUpResponse>,
                    response: Response<SignUpResponse>
                ) {
                    val loginResponse = response.body()
                    var textView = view?.findViewById<TextView>(R.id.text_sign_up)
                    if (textView != null) {
                        if (loginResponse != null) {
                            textView.text = loginResponse.username
                        }
                    }
                }
            })
    }
}