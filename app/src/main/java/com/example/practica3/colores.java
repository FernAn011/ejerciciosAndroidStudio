package com.example.practica3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class colores extends AppCompatActivity {

    private TextView tvTitulo;
    private RadioGroup rgColores;
    private Button btnCambiarColor;

    private int[] colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colores);

        tvTitulo = findViewById(R.id.tv_titulo);
        rgColores = findViewById(R.id.rg_colores);

        rgColores.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbRojo) {
                    tvTitulo.setTextColor(ContextCompat.getColor(colores.this, R.color.holo_red_light));
                } else if (checkedId == R.id.rbVerde) {
                    tvTitulo.setTextColor(ContextCompat.getColor(colores.this, R.color.holo_green_light));
                } else if (checkedId == R.id.rbAzul) {
                    tvTitulo.setTextColor(ContextCompat.getColor(colores.this, R.color.holo_blue_light));
                } else if (checkedId == R.id.rbAmarillo) {
                    tvTitulo.setTextColor(Color.YELLOW);
                } else if (checkedId == R.id.rbNegro) {
                    tvTitulo.setTextColor(Color.BLACK);
                }
            }
        });

        btnCambiarColor = findViewById(R.id.btnCambiarColor);

        colors = new int[] {
                ContextCompat.getColor(this, R.color.holo_red_light),
                ContextCompat.getColor(this, R.color.holo_green_light),
                ContextCompat.getColor(this, R.color.holo_blue_light),
                Color.YELLOW,
                Color.BLACK
        };

        btnCambiarColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int randomColor = colors[random.nextInt(colors.length)];
                tvTitulo.setTextColor(randomColor);
            }
        });
    }
}
