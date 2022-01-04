package ru.ialmostdeveloper.soulfire_mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {


    private SessionManager sessionManager;
    public ProfileFragment() {
        // Required empty public constructor
    }
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sessionManager = new SessionManager(this.requireActivity());
        SharedPreferences sprefs = this.requireActivity().getSharedPreferences("ru.ialmostdeveloper.soulfire_mobile", Context.MODE_PRIVATE);

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView profile_label = view.findViewById(R.id.greetings_label);
        profile_label.setText(sessionManager.fetchUserName());
        Button logout_btn = view.findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(v->  {
            Toast.makeText(this.requireActivity(), "разлогиниваюсь", Toast.LENGTH_SHORT).show();
            sprefs.edit().putBoolean("isUserLoggedIn", false).apply();
            startActivity(new Intent(this.requireActivity(), LoginActivity.class));
        });
        return view;
    }
}