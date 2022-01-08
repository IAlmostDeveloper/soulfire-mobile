package ru.ialmostdeveloper.soulfire_mobile.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import ru.ialmostdeveloper.soulfire_mobile.Adapters.PresetsAdapter;
import ru.ialmostdeveloper.soulfire_mobile.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PresetsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PresetsFragment extends Fragment {
    public PresetsFragment() {
        // Required empty public constructor
    }

    public static PresetsFragment newInstance() {
        PresetsFragment fragment = new PresetsFragment();
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
        View view = inflater.inflate(R.layout.fragment_presets, container, false);
        ViewPager pager = view.findViewById(R.id.viewpager);
        pager.setAdapter(new PresetsAdapter(requireActivity().getSupportFragmentManager()));
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager);
        return view;
    }
}