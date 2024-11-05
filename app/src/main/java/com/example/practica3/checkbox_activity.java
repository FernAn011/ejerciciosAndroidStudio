package com.example.practica3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class checkbox_activity extends AppCompatActivity {
    private EditText txt_nro1, txt_nro2;
    private CheckBox chk_1, chk_2;
    private TextView txt_resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_checkbox);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt_nro1=findViewById(R.id.txt_nro1);
        txt_nro2=findViewById(R.id.txt_nro2);
        chk_1=findViewById(R.id.chk_1);
        chk_2=findViewById(R.id.chk_2);
        txt_resultado=findViewById(R.id.txt_respuesta);
    }
    public void calcular(View view) {
        String valor1 = txt_nro1.getText().toString();
        String valor2 = txt_nro2.getText().toString();
        Integer valor_1 = Integer.parseInt(valor1);
        Integer valor_2 = Integer.parseInt(valor2);
        String rpta = "";
        if (chk_1.isChecked()) {
            Integer suma = valor_1 + valor_2;
            rpta = rpta + "La suma es " + suma + " \n";
        }
        if (chk_2.isChecked()) {
            Integer resta = valor_1 - valor_2;
            rpta = rpta + "La resta es " + resta + " \n";
        }
        txt_resultado.setText(rpta);
    }
}