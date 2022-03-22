package com.example.mymusic;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mymusic.fragment.DiscoryFragment;
import com.example.mymusic.fragment.FriendFragment;
import com.example.mymusic.fragment.MineFragment;

public class HomePageAdapter extends FragmentPagerAdapter {

    private CHANNEL[] channels;

    public HomePageAdapter(@NonNull FragmentManager fm, int behavior, CHANNEL[] channels) {
        super(fm, behavior);
        this.channels = channels;
    }

    public HomePageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        int fragmentID = channels[position].getValue();
        switch (fragmentID){
            case CHANNEL.MY_ID:
                return MineFragment.getInstance();
            case CHANNEL.DISCORY_ID:
                return DiscoryFragment.getInstance();
            case CHANNEL.FRIEND_ID:
                return FriendFragment.getInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return channels.length;
    }
}
