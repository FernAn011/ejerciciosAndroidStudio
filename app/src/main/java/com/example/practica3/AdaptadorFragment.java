package com.example.practica3;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.fragment.app.Fragment;

public class AdaptadorFragment extends FragmentStateAdapter {
    public AdaptadorFragment(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Fragmentos1.Tab1Fragment();
            case 1:
                return new Fragmentos1.Tab2Fragment();
            case 2:
                return new Fragmentos1.Tab3Fragment();
            case 3:
                return new Fragmentos1.Tab4Fragment();
            default:
                return new Fragmentos1.Tab1Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}

