package com.example.practica3;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class menu_simple extends AppCompatActivity {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private View shine, shine2, shine3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_simple);

        shine = findViewById(R.id.shine);

        shine2 = findViewById(R.id.shine2);
        shine3 = findViewById(R.id.shine3);

        shineAnimation(shine);
        shineAnimation(shine2);
        shineAnimation(shine3);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        
    }

    private void shineAnimation(final View view) {
        // Attach the animation layout using AnimationUtils.loadAnimation
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.boton_estilo_6_animacion_derecha);
        view.startAnimation(anim);

        // Set the animation listener
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                // This function starts the animation again after it ends
                view.startAnimation(anim);
            }

            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void colores(View view) {
        startActivity(new Intent(this, colores.class));
    }

    public void ejemplo0_activity(View view) {
        startActivity(new Intent(this, ejemplo0_activity.class));
    }

    public void ejemplo1_layouts_activity(View view) {
        startActivity(new Intent(this, ejemplo1_layouts_activity.class));

    }

    public void ejemplo2_tables_activity(View view) {
        startActivity(new Intent(this, ejemplo2_tables_activity.class));

    }

    public void ejemplo3_frames_activity(View view) {
        startActivity(new Intent(this, ejemplo3_frames_activity.class));

    }

    public void ejemplo4_radiobutton_activity(View view) {
        startActivity(new Intent(this, ejemplo4_radiobutton_activity.class));

    }

    public void encuesta_activity(View view) {
        startActivity(new Intent(this, encuesta_activity.class));

    }

    public void calculadora_tabla_activity(View view) {
        startActivity(new Intent(this, calculadora_tabla_activity.class));

    }


    public void checkbox(View view) {
        startActivity(new Intent(this, VistaDeslizante.class));

    }

    public void salir(View view) {
        finishAffinity();
        System.exit(0);       }

    public void mitabhost(View view) {
        startActivity(new Intent(this, MiTabhost.class));
    }

    public void dialogo(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Este es mi dialogo")
                .setTitle("Mi primer dialogo...")
                .setCancelable(false)
                .setNeutralButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void acercade(View view) {
        startActivity(new Intent(this, AcercaDe.class));
    }
    public void dialogo2(View vista) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Titulo: Mi Segundo Dialogo");
        builder.setMessage("Mensaje: ¿Quieres ver la App de Memorizar del número aleatorio?");
        builder.setCancelable(false);

        builder.setPositiveButton("Aceptar", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        aceptar();
                    }
                });
        builder.setNegativeButton("Cancelar", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cancelar();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void aceptar() {
        startActivity(new Intent(this, ejemplo8_memorizarNumero.class));
        Toast t= Toast.makeText(this,"Bienvenido a la App <Memoriza el número aleatorio!>",
                Toast.LENGTH_SHORT);
        t.show();
    }

    public void cancelar() {
        //finish();
        Toast t=Toast.makeText(this,"Tu te la pierdes de ver la App",
                Toast.LENGTH_SHORT);
        t.show();
    }
}