package ru.ialmostdeveloper.soulfire_mobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.ialmostdeveloper.soulfire_mobile.databinding.ActivityMainBinding
import ru.ialmostdeveloper.soulfire_mobile.network.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sprefs = getSharedPreferences("ru.ialmostdeveloper.soulfire_mobile", MODE_PRIVATE)
        //setContentView(R.layout.activity_main)
        val isLoggedIn = sprefs.getBoolean("isUserLoggedIn", false);
        if (!isLoggedIn){
            startActivity(Intent(this, LoginActivity::class.java))
            return
        }

        var inflater = layoutInflater
        binding = ActivityMainBinding.inflate(inflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_diary, R.id.navigation_presets, R.id.navigation_achievements, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)



        apiClient = ApiClient()
        sessionManager = SessionManager(this)


    }
}