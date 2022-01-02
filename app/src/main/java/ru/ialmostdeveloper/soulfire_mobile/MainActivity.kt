package ru.ialmostdeveloper.soulfire_mobile

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.ialmostdeveloper.soulfire_mobile.databinding.ActivityMainBinding
import ru.ialmostdeveloper.soulfire_mobile.network.*
import ru.ialmostdeveloper.soulfire_mobile.network.models.PostsResponse

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_sign_in,
            R.id.navigation_sign_up,
            R.id.navigation_daily_achievements,
            R.id.navigation_user_achievements,
            R.id.navigation_quiz,
        ))
        navView.setupWithNavController(navController)


        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        startActivity(Intent(this, LoginActivity::class.java))
    }
}