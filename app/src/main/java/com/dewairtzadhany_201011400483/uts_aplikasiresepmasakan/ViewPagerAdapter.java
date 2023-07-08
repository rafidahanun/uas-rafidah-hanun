package com.dewairtzadhany_201011400483.uts_aplikasiresepmasakan;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import android.os.Bundle;

import com.dewairtzadhany_201011400483.uts_aplikasiresepmasakan.fragment.ProfileFragment;
import com.dewairtzadhany_201011400483.uts_aplikasiresepmasakan.fragment.ResepFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private String username;
    public ViewPagerAdapter(FragmentActivity fragmentActivity, String username) {
        super(fragmentActivity);
        this.username = username;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position){
        switch (position){
            case 0:
                return new ResepFragment();
            case 1:
                ProfileFragment profileFragment = new ProfileFragment();
                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                profileFragment.setArguments(bundle);
                return profileFragment;
            default:
                return new ResepFragment();
        }
    }

    @Override
    public int getItemCount(){
        return 2;
    }
}
