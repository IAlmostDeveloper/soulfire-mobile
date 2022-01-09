package ru.ialmostdeveloper.soulfire_mobile.Fragments;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.ialmostdeveloper.soulfire_mobile.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment3 extends Fragment {

    public MyFragment3() {
        // Required empty public constructor
    }

    public static MyFragment3 newInstance() {
        MyFragment3 fragment = new MyFragment3();
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
        return inflater.inflate(R.layout.fragment_my3, container, false);
    }
}