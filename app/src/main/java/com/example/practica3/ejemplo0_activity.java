package com.example.practica3;

import android.os.Bundle;



import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.TextView;


public class ejemplo0_activity extends AppCompatActivity {
    private TextView txt_hola;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.ejemplo0);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.CL_ejemplo0), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt_hola = findViewById(R.id.txt_id);


    }
    public void hola(View vista){
        txt_hola.setText("Hola desde java....");
    }
}