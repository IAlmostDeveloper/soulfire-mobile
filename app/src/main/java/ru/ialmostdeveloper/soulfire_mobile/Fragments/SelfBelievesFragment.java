package ru.ialmostdeveloper.soulfire_mobile.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.ialmostdeveloper.soulfire_mobile.R;


public class SelfBelievesFragment extends Fragment {


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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_self_believes, container, false);
    }
}