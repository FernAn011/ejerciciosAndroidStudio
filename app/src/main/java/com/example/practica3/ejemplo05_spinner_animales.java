package com.example.practica3;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class ejemplo05_spinner_animales extends AppCompatActivity {

    private Spinner spinnerAnimales, spinnerRazas;
    private TextView tvAnimalSeleccionado, tvRazaSeleccionada;
    private Button botonSonido;
    private ImageView imgAnimal, imgRaza;

    private Map<String, String[]> razasAnimales = new HashMap<>();
    private Map<String, Integer> imagenesAnimales = new HashMap<>();
    private Map<String, Integer> imagenesRazas = new HashMap<>();
    private Map<String, Integer> sonidosAnimales = new HashMap<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo05_spinner_animales);

        spinnerAnimales = findViewById(R.id.spinnerCategoria);
        spinnerRazas = findViewById(R.id.spinnerRaza);
        tvAnimalSeleccionado = findViewById(R.id.tv_animal_seleccionado);
        tvRazaSeleccionada = findViewById(R.id.tv_raza_seleccionada);
        botonSonido = findViewById(R.id.boton_sonido_animal);
        imgAnimal = findViewById(R.id.img_animal);
        imgRaza = findViewById(R.id.img_raza);

        razasAnimales.put("Perros", new String[]{"","Poodle", "Bulldog", "Chihuahua", "Dálmata", "Beagle"});
        razasAnimales.put("Gatos", new String[]{"","Siames", "Persa", "Maine Coon", "British Shorthair", "Sphynx"});
        razasAnimales.put("Caballos", new String[]{"","Árabe", "Quarter Horse", "Pura Sangre", "Criollo", "Appaloosa"});
        razasAnimales.put("Cerdos", new String[]{"","Duroc", "Hampshire", "Yorkshire", "Pietrain", "Landrace"});
        razasAnimales.put("Ovejas", new String[]{"","Merino", "Rambouillet", "Suffolk", "Hampshire Down", "Dorset"});
        razasAnimales.put("Aves", new String[]{"","Gallina Leghorn", "Gallina Plymouth Rock", "Pavo Real", "Conejero", "Pollo Silkie"});


        imagenesAnimales.put("Perros", R.drawable.img_perro);
        imagenesAnimales.put("Gatos", R.drawable.img_gato);
        imagenesAnimales.put("Caballos", R.drawable.img_caballo);
        imagenesAnimales.put("Cerdos", R.drawable.img_cerdo);
        imagenesAnimales.put("Ovejas", R.drawable.img_oveja);
        imagenesAnimales.put("Aves", R.drawable.img_ave);

        sonidosAnimales.put("Perros", R.raw.sonido_perro);
        sonidosAnimales.put("Gatos", R.raw.sonido_gato);
        sonidosAnimales.put("Caballos", R.raw.sonido_caballo);
        sonidosAnimales.put("Cerdos", R.raw.sonido_cerdo);
        sonidosAnimales.put("Ovejas", R.raw.sonido_oveja);
        sonidosAnimales.put("Aves", R.raw.sonido_ave);


        imagenesRazas.put("Poodle", R.drawable.poodle);
        imagenesRazas.put("Bulldog", R.drawable.bulldog);
        imagenesRazas.put("Chihuahua", R.drawable.chihuahua);
        imagenesRazas.put("Dálmata", R.drawable.dalmata);
        imagenesRazas.put("Beagle", R.drawable.beagle);

        imagenesRazas.put("Siames", R.drawable.siames);
        imagenesRazas.put("Persa", R.drawable.persa);
        imagenesRazas.put("Maine Coon", R.drawable.mainecoon);
        imagenesRazas.put("British Shorthair", R.drawable.britishshorthair);
        imagenesRazas.put("Sphynx", R.drawable.sphynx);

        imagenesRazas.put("Árabe", R.drawable.arabe);
        imagenesRazas.put("Quarter Horse", R.drawable.quarterhorse);
        imagenesRazas.put("Pura Sangre", R.drawable.purasangre);
        imagenesRazas.put("Criollo", R.drawable.criollo);
        imagenesRazas.put("Appaloosa", R.drawable.appaloosa);

        imagenesRazas.put("Duroc", R.drawable.duroc);
        imagenesRazas.put("Hampshire", R.drawable.hampshire);
        imagenesRazas.put("Yorkshire", R.drawable.yorkshire);
        imagenesRazas.put("Pietrain", R.drawable.pietrain);
        imagenesRazas.put("Landrace", R.drawable.landrace);

        imagenesRazas.put("Merino", R.drawable.merino);
        imagenesRazas.put("Rambouillet", R.drawable.rambouillet);
        imagenesRazas.put("Suffolk", R.drawable.suffolk);
        imagenesRazas.put("Hampshire Down", R.drawable.hampshiredown);
        imagenesRazas.put("Dorset", R.drawable.dorset);

        imagenesRazas.put("Gallina Leghorn", R.drawable.leghorn);
        imagenesRazas.put("Gallina Plymouth Rock", R.drawable.plymouthrock);
        imagenesRazas.put("Pavo Real", R.drawable.pavoreal);
        imagenesRazas.put("Conejero", R.drawable.conejero);
        imagenesRazas.put("Pollo Silkie", R.drawable.silkie);

        ArrayAdapter<String> adapterAnimales = new ArrayAdapter<>(this, R.layout.spinner_estilo1, razasAnimales.keySet().toArray(new String[0]));
        adapterAnimales.setDropDownViewResource(R.layout.spinner_estilo1);
        spinnerAnimales.setAdapter(adapterAnimales);
        spinnerAnimales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String animalSeleccionado = parent.getItemAtPosition(position).toString();
                tvAnimalSeleccionado.setText("Animal seleccionado: " + animalSeleccionado);

                actualizarSpinnerRazas(animalSeleccionado);
                imgAnimal.setImageResource(imagenesAnimales.get(animalSeleccionado));
                spinnerRazas.setSelection(0);
                imgRaza.setImageDrawable(null);
                tvRazaSeleccionada.setText("");
                botonSonido.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                botonSonido.setEnabled(false);
                spinnerRazas.setAdapter(null);
            }
        });

        spinnerRazas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String razaSeleccionada = spinnerRazas.getSelectedItem().toString();

                tvRazaSeleccionada.setText("Raza seleccionada: " + razaSeleccionada);
                if (imagenesRazas.containsKey(razaSeleccionada)) {
                    int idImagen = imagenesRazas.get(razaSeleccionada);
                    imgRaza.setImageResource(idImagen);
                } else {
                    imgRaza.setImageDrawable(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        botonSonido.setOnClickListener(v -> {
            String animal = spinnerAnimales.getSelectedItem().toString();
            if (sonidosAnimales.containsKey(animal)) {
                MediaPlayer mediaPlayer = MediaPlayer.create(ejemplo05_spinner_animales.this, sonidosAnimales.get(animal));
                mediaPlayer.start();
            } else {
                Toast.makeText(ejemplo05_spinner_animales.this, "Sonido no disponible", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void actualizarSpinnerRazas(String animalSeleccionado) {
        String[] razas = razasAnimales.get(animalSeleccionado);
        ArrayAdapter<String> adapterRazas = new ArrayAdapter<>(this, R.layout.spinner_estilo1, razas);
        adapterRazas.setDropDownViewResource(R.layout.spinner_estilo1);
        spinnerRazas.setAdapter(adapterRazas);
    }
}
