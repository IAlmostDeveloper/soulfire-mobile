package ru.ialmostdeveloper.soulfire_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

public class RegisterActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        ViewPager viewPager = findViewById(R.id.viewpager);
        SlideAdapter slideAdapter = new SlideAdapter(this);
        viewPager.setAdapter(slideAdapter);

        Button btn_welcome = findViewById(R.id.welcome_btn);

        SharedPreferences sprefs = this.getSharedPreferences("ru.ialmostdeveloper.soulfire_mobile", Context.MODE_PRIVATE);



        btn_welcome.setOnClickListener(v -> {
            sprefs
                    .edit()
                    .putString("userCharacterType", slideAdapter.lst_title[viewPager.getCurrentItem()])
                    .apply();
            startActivity(new Intent(this, RegisterActivity2.class));
        });



    }
}