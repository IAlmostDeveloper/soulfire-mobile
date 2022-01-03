package ru.ialmostdeveloper.soulfire_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class RegisterActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        SharedPreferences sprefs = this.getSharedPreferences("ru.ialmostdeveloper.soulfire_mobile", Context.MODE_PRIVATE);

        TextView welcome_label = findViewById(R.id.welcome_label2);

        welcome_label.setText(sprefs.getString("userCharacterType", "Undefined kek"));

        Button btn_welcome = findViewById(R.id.welcome_btn2);
        btn_welcome.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity3.class));
        });
    }
}