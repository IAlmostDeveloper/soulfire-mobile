package ru.ialmostdeveloper.soulfire_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import ru.ialmostdeveloper.soulfire_mobile.ui.RegisterFragment1;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
Fragment fragment = new RegisterFragment1();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.contentFragment, fragment);
        transaction.commit();


//        ViewPager viewPager = findViewById(R.id.viewpager);
//        SlideAdapter slideAdapter = new SlideAdapter(this);
//        viewPager.setAdapter(slideAdapter);



    }
}