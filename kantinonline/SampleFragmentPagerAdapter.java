package com.example.mmift.kantinonline;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mmift.kantinonline.Fragment.AyamFragment;
import com.example.mmift.kantinonline.Fragment.IkanFragment;
import com.example.mmift.kantinonline.Fragment.NasiFragment;
import com.example.mmift.kantinonline.Fragment.SayurFragment;

/**
 * Created by mmift on 4/14/2017.
 */

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "Nasi", "Ayam", "Ikan", "Sayur" };
    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new NasiFragment();
        } else if (position == 1){
            return new AyamFragment();
        } else if (position == 2){
            return new IkanFragment();
        } else {
            return new SayurFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}