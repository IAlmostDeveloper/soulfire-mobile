package ru.ialmostdeveloper.soulfire_mobile.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import ru.ialmostdeveloper.soulfire_mobile.Activities.AddDiaryNoteActivity;
import ru.ialmostdeveloper.soulfire_mobile.Adapters.PresetsAdapter;
import ru.ialmostdeveloper.soulfire_mobile.R;
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient;
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager;
import ru.ialmostdeveloper.soulfire_mobile.network.models.DiaryNote;


public class SelfBelievesFragment extends Fragment {

    private SessionManager sessionManager;
    private ApiClient apiClient;
    private Context self;
    private View view;

    public SelfBelievesFragment() {
        // Required empty public constructor
    }

    public static SelfBelievesFragment newInstance(String param1, String param2) {
        SelfBelievesFragment fragment = new SelfBelievesFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_self_believes, container, false);
        Button btn_addnote = view.findViewById(R.id.btn_add_note);
        btn_addnote.setOnClickListener(v -> {
            startActivity(new Intent(this.requireActivity(), AddDiaryNoteActivity.class));
        });
        //DiaryNote[] diaryNotes = getDiaryNotes();
        return view;
    }


}