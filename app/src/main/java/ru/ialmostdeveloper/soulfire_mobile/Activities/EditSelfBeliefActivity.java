package ru.ialmostdeveloper.soulfire_mobile.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;

import ru.ialmostdeveloper.soulfire_mobile.Fragments.BeliefGoodProofsFragment;
import ru.ialmostdeveloper.soulfire_mobile.Fragments.BeliefBadProofsFragment;
import ru.ialmostdeveloper.soulfire_mobile.R;

public class EditSelfBeliefActivity extends AppCompatActivity {


    FrameLayout container;
    FragmentManager myFragmentManager;
    BeliefGoodProofsFragment goodProofsFragment;
    BeliefBadProofsFragment badProofsFragment;


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
                fragmentTransaction.hide(goodProofsFragment);
                fragmentTransaction.show(badProofsFragment);
                fragmentTransaction.commit();
            } else {
                FragmentTransaction fragmentTransaction = myFragmentManager
                        .beginTransaction();
                // добавляем в контейнер при помощи метода add()
                fragmentTransaction.hide(badProofsFragment);
                fragmentTransaction.show(goodProofsFragment);
                fragmentTransaction.commit();
            }
        });


        container = (FrameLayout) findViewById(R.id.container);

        myFragmentManager = getFragmentManager();
        goodProofsFragment = BeliefGoodProofsFragment.newInstance(
                getIntent().getStringExtra("id"),
                getIntent().getStringExtra("title"),
                getIntent().getStringExtra("content"),
                getIntent().getStringArrayListExtra("goodProofs"));
        badProofsFragment = BeliefBadProofsFragment.newInstance(
                getIntent().getStringExtra("id"),
                getIntent().getStringExtra("title"),
                getIntent().getStringExtra("content"),
                getIntent().getStringArrayListExtra("badProofs")
        );

        if (savedInstanceState == null) {
            // при первом запуске программы
            FragmentTransaction fragmentTransaction = myFragmentManager
                    .beginTransaction();
            // добавляем в контейнер при помощи метода add()
            fragmentTransaction.add(R.id.container, goodProofsFragment);
            fragmentTransaction.add(R.id.container, badProofsFragment);
            fragmentTransaction.hide(badProofsFragment);
            fragmentTransaction.commit();
        }
    }
}