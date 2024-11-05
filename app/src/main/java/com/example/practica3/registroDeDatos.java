package com.example.practica3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class registroDeDatos extends AppCompatActivity {
    private TextInputEditText etApellidos, etNombres, etCorreo, etPassword, etPassword2;
    private RadioGroup rgGenero;
    private TextView tvDatos;
    private Button btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_de_datos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etApellidos = findViewById(R.id.et_apellidos);
        etNombres = findViewById(R.id.et_nombres);
        etCorreo = findViewById(R.id.et_correo);
        etPassword = findViewById(R.id.etPassword);
        etPassword2 = findViewById(R.id.etPassword2);
        rgGenero = findViewById(R.id.rg_colores);
        tvDatos = findViewById(R.id.tv_datos);
        btnRegistrar = findViewById(R.id.btn_registrar);

        mostrarDialogoConfirmacion();
    }
    private void mostrarDialogoConfirmacion() {
        new AlertDialog.Builder(this)
                .setTitle("Autorización")
                .setMessage("¿Autorizas para registrar tus datos?")
                .setPositiveButton("Aceptar", (dialog, which) -> dialog.dismiss())
                .setNegativeButton("Cancelar", (dialog, which) -> finish())
                .setCancelable(false)
                .show();
    }

    public void registrarDatos(View view) {
        if (validarCampos()) {
            String apellidos = etApellidos.getText().toString().trim();
            String nombres = etNombres.getText().toString().trim();
            String correo = etCorreo.getText().toString().trim();
            String genero = (rgGenero.getCheckedRadioButtonId() == R.id.rb_masculino) ? "Masculino" : "Femenino";

            String datos = "Apellidos: " + apellidos + "\n" +
                    "Nombres: " + nombres + "\n" +
                    "Género: " + genero + "\n" +
                    "Correo: " + correo;

            tvDatos.setText(datos);
        }
    }
    private boolean validarCampos() {
        if (TextUtils.isEmpty(etApellidos.getText())) {
            etApellidos.setError("Ingrese sus apellidos");
            return false;
        }
        if (TextUtils.isEmpty(etNombres.getText())) {
            etNombres.setError("Ingrese su nombre");
            return false;
        }
        if (TextUtils.isEmpty(etCorreo.getText())) {
            etCorreo.setError("Ingrese su correo electrónico");
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etCorreo.getText()).matches()) {
            etCorreo.setError("Ingrese un correo electrónico válido");
            return false;
        }
        if (TextUtils.isEmpty(etPassword.getText())) {
            etPassword.setError("Ingrese su contraseña");
            return false;
        }
        if (!etPassword.getText().toString().equals(etPassword2.getText().toString())) {
            etPassword2.setError("Las contraseñas no coinciden");
            return false;
        }
        if (rgGenero.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Seleccione su género", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}