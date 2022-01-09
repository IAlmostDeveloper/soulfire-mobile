package ru.ialmostdeveloper.soulfire_mobile.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;

import ru.ialmostdeveloper.soulfire_mobile.Fragments.MyFragment1;
import ru.ialmostdeveloper.soulfire_mobile.Fragments.MyFragment2;
import ru.ialmostdeveloper.soulfire_mobile.Fragments.MyFragment3;
import ru.ialmostdeveloper.soulfire_mobile.R;

public class EditSelfBeliefActivity extends AppCompatActivity {


    FrameLayout container;
    FragmentManager myFragmentManager;
    MyFragment1 myFragment1;
    MyFragment2 myFragment2;
    MyFragment3 myFragment3;
    final static String TAG_1 = "FRAGMENT_1";
    final static String TAG_2 = "FRAGMENT_2";
    final static String TAG_3 = "FRAGMENT_3";
    final static String KEY_MSG_1 = "FRAGMENT1_MSG";
    final static String KEY_MSG_2 = "FRAGMENT2_MSG";
    final static String KEY_MSG_3 = "FRAGMENT3_MSG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_self_belief);

        SwitchMaterial switchMaterial = findViewById(R.id.beliefswitch);
        switchMaterial.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                FragmentTransaction fragmentTransaction = myFragmentManager
                        .beginTransaction();
                // добавляем в контейнер при помощи метода add()
                fragmentTransaction.hide(myFragment1);
                fragmentTransaction.show(myFragment2);
                fragmentTransaction.commit();
            } else {
                FragmentTransaction fragmentTransaction = myFragmentManager
                        .beginTransaction();
                // добавляем в контейнер при помощи метода add()
                fragmentTransaction.hide(myFragment2);
                fragmentTransaction.show(myFragment1);
                fragmentTransaction.commit();
            }
        });


        container = (FrameLayout) findViewById(R.id.container);

        myFragmentManager = getFragmentManager();
        myFragment1 = new MyFragment1();
        myFragment2 = new MyFragment2();
        myFragment3 = new MyFragment3();

        if (savedInstanceState == null) {
            // при первом запуске программы
            FragmentTransaction fragmentTransaction = myFragmentManager
                    .beginTransaction();
            // добавляем в контейнер при помощи метода add()
            fragmentTransaction.add(R.id.container, myFragment1);
            fragmentTransaction.add(R.id.container, myFragment2);
            fragmentTransaction.hide(myFragment2);
            fragmentTransaction.commit();
        }
    }
}