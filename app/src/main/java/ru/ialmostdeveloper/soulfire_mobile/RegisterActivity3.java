package ru.ialmostdeveloper.soulfire_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class RegisterActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);
        SharedPreferences sprefs = this.getSharedPreferences("ru.ialmostdeveloper.soulfire_mobile", Context.MODE_PRIVATE);

        Set<String> optionsSet = sprefs.getStringSet("userAutoThoughts", new HashSet<>());
        LinearLayout card_layout = findViewById(R.id.card_layout2);
        for (int i=0;i<optionsSet.size();i++){
            TextView textView = new TextView(this);
            textView.setText(optionsSet.toArray()[i].toString());
            card_layout.addView(textView);
        }

        Button btn_welcome = findViewById(R.id.welcome_btn3);
        btn_welcome.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });
    }
}