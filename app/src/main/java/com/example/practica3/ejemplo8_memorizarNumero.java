package com.example.practica3;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ejemplo8_memorizarNumero extends AppCompatActivity {
    private EditText et_mn;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejemplo8_memorizar_numero);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        et_mn=(EditText)findViewById(R.id.et_mn);
        num=(int)(Math.random()*100001);
        String cadena=String.valueOf(num);
        Toast notificacion=
                Toast.makeText(this,cadena,Toast.LENGTH_LONG);
        notificacion.show();

    }

    public void controlar(View view) {
        String
                valorIngresado=et_mn.getText().toString();
        int
                valor=Integer.parseInt(valorIngresado);

        if (valor==num) {
            Toast notificacion=Toast.makeText(this,"Muy bien recordaste el número mostrado.",Toast.LENGTH_LONG);
                    notificacion.show();
        }
        else {
            Toast notificacion=Toast.makeText(this,"Lo siento pero no es el número que mostré.",Toast.LENGTH_LONG);
                    notificacion.show();
        }
    }
}
