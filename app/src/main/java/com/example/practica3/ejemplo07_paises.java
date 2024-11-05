package com.example.practica3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ejemplo07_paises extends AppCompatActivity {

    private Spinner spinnerRegion;
    private ListView listViewPaises;
    private TextView textViewDetalles;

    private HashMap<String, String[]> paisesContinentes;
    private HashMap<String, Integer> imagenesPaises;
    private HashMap<String, String[]> detallesPaises;
    String[] regiones = {"Sudamérica", "Norteamérica", "Centroamérica"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo07_paises);

        spinnerRegion = findViewById(R.id.spinner_region);
        listViewPaises = findViewById(R.id.listview_paises);
        textViewDetalles = findViewById(R.id.textview_detalles);
        inicializarDatos();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_estilo1, regiones);
        adapter.setDropDownViewResource(R.layout.spinner_estilo1);

        spinnerRegion.setAdapter(adapter);

        spinnerRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String regionSeleccionada = (String) parent.getItemAtPosition(position);
                actualizarListaPaises(regionSeleccionada);
                textViewDetalles.setText("");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        listViewPaises.setOnItemClickListener((parent, view, position, id) -> {
            String paisSeleccionado = (String) parent.getItemAtPosition(position);
            mostrarDetallesPais(paisSeleccionado);
        });
    }

    private void inicializarDatos() {
        paisesContinentes = new HashMap<>();
        imagenesPaises = new HashMap<>();
        detallesPaises = new HashMap<>();
        paisesContinentes.put("Sudamérica", new String[]{"Argentina", "Bolivia", "Brasil", "Chile", "Colombia", "Ecuador", "Guyana", "Paraguay", "Perú", "Surinam", "Uruguay", "Venezuela"});
        paisesContinentes.put("Centroamérica", new String[]{"Belice", "Costa Rica", "El Salvador", "Guatemala", "Honduras", "Nicaragua", "Panamá"});
        paisesContinentes.put("Norteamérica", new String[]{"Canadá", "Estados Unidos", "México"});


        imagenesPaises.put("Argentina", R.drawable.argentina);
        imagenesPaises.put("Bolivia", R.drawable.bolivia);
        imagenesPaises.put("Brasil", R.drawable.brasil);
        imagenesPaises.put("Chile", R.drawable.chile);
        imagenesPaises.put("Colombia", R.drawable.colombia);
        imagenesPaises.put("Ecuador", R.drawable.ecuador);
        imagenesPaises.put("Guyana", R.drawable.guyana);
        imagenesPaises.put("Paraguay", R.drawable.paraguay);
        imagenesPaises.put("Perú", R.drawable.peru);
        imagenesPaises.put("Surinam", R.drawable.surinam);
        imagenesPaises.put("Uruguay", R.drawable.uruguay);
        imagenesPaises.put("Venezuela", R.drawable.venezuela);
        imagenesPaises.put("Belice", R.drawable.belice);
        imagenesPaises.put("Costa Rica", R.drawable.costarica);
        imagenesPaises.put("El Salvador", R.drawable.salvador);
        imagenesPaises.put("Guatemala", R.drawable.guatemala);
        imagenesPaises.put("Honduras", R.drawable.honduras);
        imagenesPaises.put("Nicaragua", R.drawable.nicaragua);
        imagenesPaises.put("Panamá", R.drawable.panama);
        imagenesPaises.put("Canadá", R.drawable.canada);
        imagenesPaises.put("Estados Unidos", R.drawable.estadosunidos);
        imagenesPaises.put("México", R.drawable.mexico);


        detallesPaises.put("Argentina", new String[]{"Buenos Aires", "45,6 millones"});
        detallesPaises.put("Bolivia", new String[]{"La Paz", "12,1 millones"});
        detallesPaises.put("Brasil", new String[]{"Brasilia", "215,3 millones"});
        detallesPaises.put("Chile", new String[]{"Santiago", "19,4 millones"});
        detallesPaises.put("Colombia", new String[]{"Bogotá", "54,3 millones"});
        detallesPaises.put("Ecuador", new String[]{"Quito", "19,1 millones"});
        detallesPaises.put("Guyana", new String[]{"Georgetown", "794.000"});
        detallesPaises.put("Paraguay", new String[]{"Asunción", "7,4 millones"});
        detallesPaises.put("Perú", new String[]{"Lima", "34,7 millones"});
        detallesPaises.put("Surinam", new String[]{"Paramaribo", "586.000"});
        detallesPaises.put("Uruguay", new String[]{"Montevideo", "3,6 millones"});
        detallesPaises.put("Venezuela", new String[]{"Caracas", "32,5 millones"});

        detallesPaises.put("Belice", new String[]{"Belmopán", "397.000"});
        detallesPaises.put("Costa Rica", new String[]{"San José", "5,2 millones"});
        detallesPaises.put("El Salvador", new String[]{"San Salvador", "6,6 millones"});
        detallesPaises.put("Guatemala", new String[]{"Ciudad de Guatemala", "18,3 millones"});
        detallesPaises.put("Honduras", new String[]{"Tegucigalpa", "11,2 millones"});
        detallesPaises.put("Nicaragua", new String[]{"Managua", "6,7 millones"});
        detallesPaises.put("Panamá", new String[]{"Panamá", "4,4 millones"});
        detallesPaises.put("Canadá", new String[]{"Ottawa", "38,4 millones"});
        detallesPaises.put("Estados Unidos", new String[]{"Washington D.C.", "337,2 millones"});
        detallesPaises.put("México", new String[]{"Ciudad de México", "133,4 millones"});
    }

    private void actualizarListaPaises(String regionSeleccionada) {
        String[] paises = paisesContinentes.get(regionSeleccionada);
        List<String> listaPaises = Arrays.asList(paises);
        HashMap<String, String> codigosPaises = new HashMap<>();
        codigosPaises.put("Argentina", "AR");
        codigosPaises.put("Bolivia", "BO");
        codigosPaises.put("Brasil", "BR");
        codigosPaises.put("Chile", "CL");
        codigosPaises.put("Colombia", "CO");
        codigosPaises.put("Ecuador", "EC");
        codigosPaises.put("Guyana", "GY");
        codigosPaises.put("Paraguay", "PY");
        codigosPaises.put("Perú", "PE");
        codigosPaises.put("Surinam", "SR");
        codigosPaises.put("Uruguay", "UY");
        codigosPaises.put("Venezuela", "VE");
        codigosPaises.put("Canadá", "CA");
        codigosPaises.put("Estados Unidos", "US");
        codigosPaises.put("México", "MX");
        codigosPaises.put("Belice", "BZ");
        codigosPaises.put("Costa Rica", "CR");
        codigosPaises.put("El Salvador", "SV");
        codigosPaises.put("Guatemala", "GT");
        codigosPaises.put("Honduras", "HN");
        codigosPaises.put("Nicaragua", "NI");
        codigosPaises.put("Panamá", "PA");
        adaptador_pais paisAdapter = new adaptador_pais(this, listaPaises, imagenesPaises, codigosPaises);
        listViewPaises.setAdapter(paisAdapter);
    }

    private void mostrarDetallesPais(String pais) {
        String[] detalles = detallesPaises.get(pais);
        String capital = detalles[0];
        String poblacion = detalles[1];
        textViewDetalles.setText("El país de " + pais + " cuenta con " + poblacion + " de pobladores y su capital es la ciudad de " + capital);
    }
}
