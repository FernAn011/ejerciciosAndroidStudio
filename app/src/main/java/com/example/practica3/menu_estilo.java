package com.example.practica3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class menu_estilo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_estilo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_menu_estilo), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void act_operaciones(View view) {
        startActivity(new Intent(this, ejemplo06_controlspinner_operaciones.class));
    }

    public void act_acerca(View view) {
        startActivity(new Intent(this, menu_simple.class));

    }

    public void act_animales(View view) {
        startActivity(new Intent(this, ejemplo05_spinner_animales.class));

    }

    public void act_paises(View view) {
        startActivity(new Intent(this, ejemplo07_paises.class));

    }

    public void act_formulario(View view) {
        startActivity(new Intent(this, registroDeDatos.class));

    }

    public void act_insercion(View view) {
        startActivity(new Intent(this, insercionDialogo.class));

    }
}