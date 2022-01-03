package ru.ialmostdeveloper.soulfire_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;

public class RegisterActivity5 extends AppCompatActivity {

    private Button btn_register;
    private EditText username_input;
    private EditText password_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register5);
        SharedPreferences sprefs = this.getSharedPreferences("ru.ialmostdeveloper.soulfire_mobile", Context.MODE_PRIVATE);

        btn_register = findViewById(R.id.signUpButton);
        username_input = findViewById(R.id.usernameInput);
        password_input = findViewById(R.id.passwordInput);

        Set autoThoughts = sprefs.getStringSet("userAutoThoughts", new HashSet<String>());
        Set middleThoughts = sprefs.getStringSet("userMiddleThoughts", new HashSet<String>());
        Set deepThoughts = sprefs.getStringSet("userDeepThoughts", new HashSet<String>());



        btn_register.setOnClickListener(v -> {

        });
    }
}