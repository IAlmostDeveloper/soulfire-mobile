package ru.ialmostdeveloper.soulfire_mobile.Adapters;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import ru.ialmostdeveloper.soulfire_mobile.Fragments.PresetsAvailableFragment;
import ru.ialmostdeveloper.soulfire_mobile.Fragments.PresetsSavedFragment;

public class PresetsAdapter extends FragmentPagerAdapter {

    public PresetsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "Все";
            case 1:
                return "Избранное";
        }
        return null;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PresetsAvailableFragment.newInstance();
            case 1:
                return PresetsSavedFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
