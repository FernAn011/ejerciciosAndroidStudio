package com.example.practica3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.CheckBox;

public class ejemplo3_frames_activity extends AppCompatActivity {
    private CheckBox cajita;
    private Button boton_oculto;
    private TextView texto_oculto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.ejemplo3_frames);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.FL_ejemplo3), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cajita = findViewById(R.id.cajita);
        boton_oculto = findViewById(R.id.boton_oculto);
        texto_oculto = findViewById(R.id.texto_oculto);
        boton_oculto.setVisibility(View.INVISIBLE);
        boton_oculto.setVisibility(View.INVISIBLE);
        cajita.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                boton_oculto.setVisibility(View.VISIBLE);
                texto_oculto.setVisibility(View.VISIBLE);
            } else {
                boton_oculto.setVisibility(View.INVISIBLE);
                texto_oculto.setVisibility(View.INVISIBLE);
            }
        });
        boton_oculto.setOnClickListener(v -> {
            texto_oculto.setText("El botón fue presionado");
        });

    }
}