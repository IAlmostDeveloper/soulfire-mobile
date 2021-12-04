package ru.ialmostdeveloper.soulfire_mobile.ui.home

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
import ru.ialmostdeveloper.soulfire_mobile.databinding.FragmentHomeBinding
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient
import ru.ialmostdeveloper.soulfire_mobile.network.LoginRequest
import ru.ialmostdeveloper.soulfire_mobile.network.LoginResponse
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
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
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        var usernameInput: EditText = view.findViewById(R.id.usernameInput)
        var passwordInput: EditText = view.findViewById(R.id.passwordInput)
        var loginButton: Button = view.findViewById(R.id.loginRequestButton)
        loginButton.setOnClickListener(View.OnClickListener {
            var username = usernameInput.text.toString()
            var password = passwordInput.text.toString()
            login(username, password)
            Log.d("1233234122", "nutton pressed")
        })


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun login(username: String, password : String) {
        apiClient.getApiService().login(LoginRequest(username = username, password = password))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d("1233234122", "error");
                    var textView = view?.findViewById<TextView>(R.id.text_home)
                    if (textView != null) {
                        textView.text = t.message
                    }
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    Log.d("1233234122", "response");

                    val loginResponse = response.body()

                    if (loginResponse?.message == "Success") {
                        sessionManager.saveAuthToken(loginResponse.authToken)
                        var textView = view?.findViewById<TextView>(R.id.text_home)
                        if (textView != null) {
                            textView.text = loginResponse.authToken
                        }
                    }
                }
            })
    }
}