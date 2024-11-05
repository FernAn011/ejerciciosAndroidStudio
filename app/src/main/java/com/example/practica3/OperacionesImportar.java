package com.example.practica3;

import android.content.Context;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;



public class OperacionesImportar {

    public static void operar(Context context, EditText et1, EditText et2, Spinner spinnerOperaciones, Spinner spinnerDecimales, TextView tvResultado) {
        try {
            String operacionSeleccionada = spinnerOperaciones.getSelectedItem().toString();
            String input1 = et1.getText().toString();
            String input2 = et2.getText().toString();

            if (input1.isEmpty() || (input2.isEmpty() && !operacionSeleccionada.equals("raíz cuadrada"))) {
                Toast.makeText(context, "Debe ingresar ambos números", Toast.LENGTH_SHORT).show();
                return;
            }

            double num1 = Double.parseDouble(input1);
            double num2 = operacionSeleccionada.equals("raíz cuadrada") ? 0 : Double.parseDouble(input2);
            int decimalesSeleccionados = Integer.parseInt(spinnerDecimales.getSelectedItem().toString());
            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.setMaximumFractionDigits(decimalesSeleccionados);

            double resultado = 0;

            switch (operacionSeleccionada) {
                case "sumar":
                    resultado = num1 + num2;
                    break;
                case "restar":
                    resultado = num1 - num2;
                    break;
                case "multiplicar":
                    resultado = num1 * num2;
                    break;
                case "dividir":
                    if (num2 == 0) {
                        Toast.makeText(context, "No se puede dividir entre cero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    resultado = num1 / num2;
                    break;
                case "potencia":
                    resultado = Math.pow(num1, num2);
                    break;
                case "raíz cuadrada":
                    if (num1 < 0) {
                        Toast.makeText(context, "No se puede calcular la raíz de un número negativo", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    resultado = Math.sqrt(num1);
                    break;
            }

            tvResultado.setText("Resultado: " + decimalFormat.format(resultado));

        } catch (NumberFormatException e) {
            Toast.makeText(context, "Error en la entrada de datos", Toast.LENGTH_SHORT).show();
        }
    }
}
