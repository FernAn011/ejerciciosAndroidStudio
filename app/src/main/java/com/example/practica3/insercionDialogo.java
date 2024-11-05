package com.example.practica3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class insercionDialogo extends AppCompatActivity {
    private TextView datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insercion_dialogo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        datos = findViewById(R.id.tv_resultado);

    }



    public void i_dialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Envie su nombre");

        View vista_inflada1 = LayoutInflater.from(this).inflate(R.layout.insercion_datos_input_dialog, null);
        final EditText input = vista_inflada1.findViewById(R.id.editt_1);
        builder.setView(vista_inflada1);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = input.getText().toString();
                datos.setText("Nombre: " + text);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void cb_dialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona las opciones");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.insercion_datos_checkbox_dialog, null);
        final CheckBox checkboxOption1 = viewInflated.findViewById(R.id.checkb_1);
        final CheckBox checkboxOption2 = viewInflated.findViewById(R.id.checkb_2);
        final CheckBox checkboxOption3 = viewInflated.findViewById(R.id.checkb_3);
        builder.setView(viewInflated);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder selectedOptions = new StringBuilder();

                if (checkboxOption1.isChecked()) {
                    selectedOptions.append("Perro, ");
                }
                if (checkboxOption2.isChecked()) {
                    selectedOptions.append("Gato, ");
                }
                if (checkboxOption3.isChecked()) {
                    selectedOptions.append("Caballo, ");
                }

                if (selectedOptions.length() > 0) {
                    selectedOptions.setLength(selectedOptions.length() - 2);
                    datos.setText("Animales seleccionados: " + selectedOptions.toString());
                } else {
                    datos.setText("Ningún animal seleccionado");
                }

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }
}