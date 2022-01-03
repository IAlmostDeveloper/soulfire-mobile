package ru.ialmostdeveloper.soulfire_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

public class RegisterActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);
        SharedPreferences sprefs = this.getSharedPreferences("ru.ialmostdeveloper.soulfire_mobile", Context.MODE_PRIVATE);

        Button btn_welcome = findViewById(R.id.welcome_btn3);
        btn_welcome.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });
    }
}