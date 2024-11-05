package com.example.practica3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class VistaDeslizante extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_deslizante);

        ViewPager2 viewPager = findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        AdaptadorFragment adapter = new AdaptadorFragment(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Operaciones");
                    tab.setIcon(R.drawable.iconsuma);
                    break;
                case 1:
                    tab.setText("Paises");
                    tab.setIcon(R.drawable.iconmundo);
                    break;
                case 2:
                    tab.setText("Animales");
                    tab.setIcon(R.drawable.img_perro);
                    break;
                case 3:
                    tab.setText("Mensajes");
                    tab.setIcon(R.drawable.iconmsg);

                    break;
            }
        }).attach();
    }
}
