package ru.ialmostdeveloper.soulfire_mobile

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sprefs = getSharedPreferences("ru.ialmostdeveloper.soulfire_mobile", MODE_PRIVATE)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        val isLoggedIn = sprefs.getBoolean("isUserLoggedIn", false);
        if (!isLoggedIn)
            startActivity(Intent(this, LoginActivity::class.java))

        val profile_label = findViewById<TextView>(R.id.greetings_label);
        profile_label.text = sessionManager.fetchUserName()
        val logout_btn = findViewById<Button>(R.id.logout_btn);
        logout_btn.setOnClickListener {
            Toast.makeText(this, "разлогиниваюсь", Toast.LENGTH_SHORT).show()
            sprefs.edit().putBoolean("isUserLoggedIn", false).apply();
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}