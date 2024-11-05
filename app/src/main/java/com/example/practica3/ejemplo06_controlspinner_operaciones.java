package com.example.practica3;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class ejemplo06_controlspinner_operaciones extends AppCompatActivity {

    private EditText et1, et2;
    private Spinner spinnerOperaciones, spinnerDigitos, spinnerDecimales;
    private TextView tvResultado;
    private Button buttonOperar;

    String[] opciones = {"sumar", "restar", "multiplicar", "dividir", "potencia", "raíz cuadrada"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo06_operaciones);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        spinnerOperaciones = findViewById(R.id.spinner);
        spinnerDigitos = findViewById(R.id.spinner_digitos);
        spinnerDecimales = findViewById(R.id.spinner_decimales);
        tvResultado = findViewById(R.id.tv1);
        buttonOperar = findViewById(R.id.boton_operar);
        ArrayAdapter<String> adapterOperaciones = new ArrayAdapter<>(this, R.layout.spinner_estilo1, opciones);
        adapterOperaciones.setDropDownViewResource(R.layout.spinner_estilo1);
        spinnerOperaciones.setAdapter(adapterOperaciones);

        ArrayAdapter<CharSequence> adapterDigitos = ArrayAdapter.createFromResource(this, R.array.digitos_array, R.layout.spinner_estilo1);
        adapterDigitos.setDropDownViewResource(R.layout.spinner_estilo1);
        spinnerDigitos.setAdapter(adapterDigitos);

        ArrayAdapter<CharSequence> adapterDecimales = ArrayAdapter.createFromResource(this, R.array.decimales_array, R.layout.spinner_estilo1);
        adapterDecimales.setDropDownViewResource(R.layout.spinner_estilo1);
        spinnerDecimales.setAdapter(adapterDecimales);
        buttonOperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operar();
            }
        });


    }



    private void operar() {
        try {
            String operacionSeleccionada = spinnerOperaciones.getSelectedItem().toString();
            String input1 = et1.getText().toString();
            String input2 = et2.getText().toString();

            if (input1.isEmpty() || (input2.isEmpty() && !operacionSeleccionada.equals("raíz cuadrada"))) {
                Toast.makeText(this, "Debe ingresar ambos números", Toast.LENGTH_SHORT).show();
                return;
            }

            double num1 = Double.parseDouble(input1);
            double num2 = operacionSeleccionada.equals("raíz cuadrada") ? 0 : Double.parseDouble(input2);

            int decimalesSeleccionados = Integer.parseInt(spinnerDecimales.getSelectedItem().toString());
            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.setMaximumFractionDigits(decimalesSeleccionados);

            double resultado = 0;

            if (operacionSeleccionada.equals("sumar")) {
                resultado = num1 + num2;
            } else if (operacionSeleccionada.equals("restar")) {
                resultado = num1 - num2;
            } else if (operacionSeleccionada.equals("multiplicar")) {
                resultado = num1 * num2;
            } else if (operacionSeleccionada.equals("dividir")) {
                if (num2 == 0) {
                    Toast.makeText(this, "No se puede dividir entre cero", Toast.LENGTH_SHORT).show();
                    return;
                }
                resultado = num1 / num2;
            } else if (operacionSeleccionada.equals("potencia")) {
                resultado = Math.pow(num1, num2);
            } else if (operacionSeleccionada.equals("raíz cuadrada")) {
                if (num1 < 0) {
                    Toast.makeText(this, "No se puede calcular la raíz de un número negativo", Toast.LENGTH_SHORT).show();
                    return;
                }
                resultado = Math.sqrt(num1);
            }

            tvResultado.setText("Resultado: " + decimalFormat.format(resultado));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Error en la entrada de datos", Toast.LENGTH_SHORT).show();
        }
    }
}
