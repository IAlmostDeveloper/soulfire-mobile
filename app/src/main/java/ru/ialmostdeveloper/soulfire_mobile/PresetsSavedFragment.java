package ru.ialmostdeveloper.soulfire_mobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PresetsSavedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PresetsSavedFragment extends Fragment {

    public PresetsSavedFragment() {
        // Required empty public constructor
    }

    public static PresetsSavedFragment newInstance() {
        PresetsSavedFragment fragment = new PresetsSavedFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_presets_saved, container, false);
    }
}