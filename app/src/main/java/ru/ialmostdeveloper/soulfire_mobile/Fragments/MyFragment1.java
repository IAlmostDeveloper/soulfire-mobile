package ru.ialmostdeveloper.soulfire_mobile.Fragments;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.ialmostdeveloper.soulfire_mobile.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment1 extends Fragment {


    public MyFragment1() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MyFragment1 newInstance() {
        MyFragment1 fragment = new MyFragment1();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my1, container, false);
    }
}