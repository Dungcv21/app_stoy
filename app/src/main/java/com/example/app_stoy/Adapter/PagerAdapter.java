package com.example.app_stoy.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.app_stoy.Fragment.Chat.ChatFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    int tabcount;
    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount=behavior;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new ChatFragment();


            case 1:
                return new ChatFragment();

            case 2:

                return new ChatFragment();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return tabcount;
    }
}
