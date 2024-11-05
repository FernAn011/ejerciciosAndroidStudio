package com.example.practica3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class encuesta_activity extends AppCompatActivity {

    private EditText etNombres;
    private RadioGroup rgEmpanadas, rgBebidas;
    private Button btnEnviar, btnVerPreferencias;
    private TextView tvResultadoEncuesta1, tvResultadoEncuesta2;

    private int contadorQueso = 0;
    private int contadorCarne = 0;
    private int contadorPepsi = 0;
    private int contadorCocaCola = 0;
    private int contadorIncaKola = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encuesta);

        etNombres = findViewById(R.id.et_nombres);
        rgEmpanadas = findViewById(R.id.rg_empanadas);
        rgBebidas = findViewById(R.id.rgBebidas);
        btnEnviar = findViewById(R.id.btn_enviar);
        btnVerPreferencias = findViewById(R.id.btn_empanadas);
        tvResultadoEncuesta1 = findViewById(R.id.tv_resultado_encuesta1);
        tvResultadoEncuesta2 = findViewById(R.id.tv_resultado_encuesta2);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos();
                reiniciarCampos();
            }
        });

        btnVerPreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarPreferencias();
            }
        });
    }

    private void guardarDatos() {
        String nombre = etNombres.getText().toString();
        int empanadaSeleccionada = rgEmpanadas.getCheckedRadioButtonId();
        if (empanadaSeleccionada == R.id.rb_queso) {
            contadorQueso++;
        } else if (empanadaSeleccionada == R.id.rb_carne) {
            contadorCarne++;
        }
        int bebidaSeleccionada = rgBebidas.getCheckedRadioButtonId();
        if (bebidaSeleccionada == R.id.rbPepsi) {
            contadorPepsi++;
        } else if (bebidaSeleccionada == R.id.rbCocaCola) {
            contadorCocaCola++;
        } else if (bebidaSeleccionada == R.id.rbIncaKola) {
            contadorIncaKola++;
        }
    }

    private void reiniciarCampos() {
        etNombres.setText("");
        rgEmpanadas.clearCheck();
        rgBebidas.clearCheck();
    }

    private void mostrarPreferencias() {
        String resultadoEmpanadas = "Preferencia empanadas: " +
                "Queso (" + contadorQueso + "), Carne (" + contadorCarne + ")";
        String resultadoBebidas = "Preferencia bebidas: " +
                "Pepsi (" + contadorPepsi + "), Coca Cola (" + contadorCocaCola + "), Inca Kola (" + contadorIncaKola + ")";
        tvResultadoEncuesta1.setText(resultadoEmpanadas);
        tvResultadoEncuesta2.setText(resultadoBebidas);
    }
}
