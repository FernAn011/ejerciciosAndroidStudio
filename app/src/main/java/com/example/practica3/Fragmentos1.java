package com.example.practica3;

import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragmentos1 {
    public static class Tab1Fragment extends Fragment {

        private EditText et1, et2;
        private Spinner spinnerOperaciones, spinnerDigitos, spinnerDecimales;
        private TextView tvResultado;
        private Button buttonOperar;

        String[] opciones = {"sumar", "restar", "multiplicar", "dividir", "potencia", "raíz cuadrada"};

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_ejemplo06_operaciones, container, false);

            et1 = view.findViewById(R.id.et1);
            et2 = view.findViewById(R.id.et2);
            spinnerOperaciones = view.findViewById(R.id.spinner);
            spinnerDigitos = view.findViewById(R.id.spinner_digitos);
            spinnerDecimales = view.findViewById(R.id.spinner_decimales);
            tvResultado = view.findViewById(R.id.tv1);
            buttonOperar = view.findViewById(R.id.boton_operar);

            ArrayAdapter<String> adapterOperaciones = new ArrayAdapter<>(getContext(), R.layout.spinner_estilo1, opciones);
            adapterOperaciones.setDropDownViewResource(R.layout.spinner_estilo1);
            spinnerOperaciones.setAdapter(adapterOperaciones);

            ArrayAdapter<CharSequence> adapterDigitos = ArrayAdapter.createFromResource(getContext(), R.array.digitos_array, R.layout.spinner_estilo1);
            adapterDigitos.setDropDownViewResource(R.layout.spinner_estilo1);
            spinnerDigitos.setAdapter(adapterDigitos);

            ArrayAdapter<CharSequence> adapterDecimales = ArrayAdapter.createFromResource(getContext(), R.array.decimales_array, R.layout.spinner_estilo1);
            adapterDecimales.setDropDownViewResource(R.layout.spinner_estilo1);
            spinnerDecimales.setAdapter(adapterDecimales);

            buttonOperar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operar();
                }
            });
            return view;
        }

        private void operar() {
            try {
                String operacionSeleccionada = spinnerOperaciones.getSelectedItem().toString();
                String input1 = et1.getText().toString();
                String input2 = et2.getText().toString();

                if (input1.isEmpty() || (input2.isEmpty() && !operacionSeleccionada.equals("raíz cuadrada"))) {
                    Toast.makeText(getContext(), "Debe ingresar ambos números", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getContext(), "No se puede dividir entre cero", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        resultado = num1 / num2;
                        break;
                    case "potencia":
                        resultado = Math.pow(num1, num2);
                        break;
                    case "raíz cuadrada":
                        if (num1 < 0) {
                            Toast.makeText(getContext(), "No se puede calcular la raíz de un número negativo", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        resultado = Math.sqrt(num1);
                        break;
                }

                tvResultado.setText("Resultado: " + decimalFormat.format(resultado));

            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Error en la entrada de datos", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public static class Tab2Fragment extends Fragment {

        private Spinner spinnerRegion;
        private ListView listViewPaises;
        private TextView textViewDetalles;

        private HashMap<String, String[]> paisesContinentes;
        private HashMap<String, Integer> imagenesPaises;
        private HashMap<String, String[]> detallesPaises;
        String[] regiones = {"Sudamérica", "Norteamérica", "Centroamérica"};

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_ejemplo07_paises, container, false);
            spinnerRegion = view.findViewById(R.id.spinner_region);
            listViewPaises = view.findViewById(R.id.listview_paises);
            textViewDetalles = view.findViewById(R.id.textview_detalles);

            inicializarDatos();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_estilo1, regiones);
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

            listViewPaises.setOnItemClickListener((parent, view1, position, id) -> {
                String paisSeleccionado = (String) parent.getItemAtPosition(position);
                mostrarDetallesPais(paisSeleccionado);
            });

            return view;
        }

        private void inicializarDatos() {
            paisesContinentes = new HashMap<>();
            imagenesPaises = new HashMap<>();
            detallesPaises = new HashMap<>();
            paisesContinentes.put("Sudamérica", new String[]{"Argentina", "Bolivia", "Brasil", "Chile", "Colombia", "Ecuador", "Guyana", "Paraguay", "Perú", "Surinam", "Uruguay", "Venezuela"});
            paisesContinentes.put("Centroamérica", new String[]{"Belice", "Costa Rica", "El Salvador", "Guatemala", "Honduras", "Nicaragua", "Panamá"});
            paisesContinentes.put("Norteamérica", new String[]{"Canadá", "Estados Unidos", "México"});


            imagenesPaises.put("Argentina", R.drawable.argentina); // Añadir las demás imágenes
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
            adaptador_pais paisAdapter = new adaptador_pais(requireActivity(), listaPaises, imagenesPaises, codigosPaises);
            listViewPaises.setAdapter(paisAdapter);
        }
        private void mostrarDetallesPais(String pais) {
            String[] detalles = detallesPaises.get(pais);
            String capital = detalles[0];
            String poblacion = detalles[1];
            textViewDetalles.setText("El país de " + pais + " cuenta con " + poblacion + " de pobladores y su capital es la ciudad de " + capital);
        }
    }


    public static class Tab3Fragment extends Fragment {
        private Spinner spinnerAnimales, spinnerRazas;
        private TextView tvAnimalSeleccionado, tvRazaSeleccionada;
        private Button botonSonido;
        private ImageView imgAnimal, imgRaza;

        private Map<String, String[]> razasAnimales = new HashMap<>();
        private Map<String, Integer> imagenesAnimales = new HashMap<>();
        private Map<String, Integer> imagenesRazas = new HashMap<>();
        private Map<String, Integer> sonidosAnimales = new HashMap<>();

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_ejemplo05_spinner_animales, container, false);

            spinnerAnimales = view.findViewById(R.id.spinnerCategoria);
            spinnerRazas = view.findViewById(R.id.spinnerRaza);
            tvAnimalSeleccionado = view.findViewById(R.id.tv_animal_seleccionado);
            tvRazaSeleccionada = view.findViewById(R.id.tv_raza_seleccionada);
            botonSonido = view.findViewById(R.id.boton_sonido_animal);
            imgAnimal = view.findViewById(R.id.img_animal);
            imgRaza = view.findViewById(R.id.img_raza);

            inicializarDatos();

            ArrayAdapter<String> adapterAnimales = new ArrayAdapter<>(getContext(), R.layout.spinner_estilo1, razasAnimales.keySet().toArray(new String[0]));
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
                public void onNothingSelected(AdapterView<?> parent) {}
            });

            botonSonido.setOnClickListener(v -> {
                String animal = spinnerAnimales.getSelectedItem().toString();
                if (sonidosAnimales.containsKey(animal)) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), sonidosAnimales.get(animal));
                    mediaPlayer.start();
                } else {
                    Toast.makeText(getActivity(), "Sonido no disponible", Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }

        private void inicializarDatos() {
            razasAnimales.put("Perros", new String[]{"", "Poodle", "Bulldog", "Chihuahua", "Dálmata", "Beagle"});
            razasAnimales.put("Gatos", new String[]{"", "Siames", "Persa", "Maine Coon", "British Shorthair", "Sphynx"});
            razasAnimales.put("Caballos", new String[]{"", "Árabe", "Quarter Horse", "Pura Sangre", "Criollo", "Appaloosa"});
            razasAnimales.put("Cerdos", new String[]{"", "Duroc", "Hampshire", "Yorkshire", "Pietrain", "Landrace"});
            razasAnimales.put("Ovejas", new String[]{"", "Merino", "Rambouillet", "Suffolk", "Hampshire Down", "Dorset"});
            razasAnimales.put("Aves", new String[]{"", "Gallina Leghorn", "Gallina Plymouth Rock", "Pavo Real", "Conejero", "Pollo Silkie"});

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
        }

        private void actualizarSpinnerRazas(String animalSeleccionado) {
            String[] razas = razasAnimales.get(animalSeleccionado);
            ArrayAdapter<String> adapterRazas = new ArrayAdapter<>(getContext(), R.layout.spinner_estilo1, razas);
            adapterRazas.setDropDownViewResource(R.layout.spinner_estilo1);
            spinnerRazas.setAdapter(adapterRazas);
        }
    }
    public static class Tab4Fragment extends Fragment {

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_tab_4, container, false);

            ImageButton imageButtonUniversidad = view.findViewById(R.id.imageButtonUniversidad);
            ImageButton imageButtonIngenieria = view.findViewById(R.id.imageButtonIngenieria);

            imageButtonUniversidad.setOnClickListener(v ->
                    Toast.makeText(getActivity(), "UNIVERSIDAD NACIONAL DE MOQUEGUA", Toast.LENGTH_SHORT).show()
            );

            imageButtonIngenieria.setOnClickListener(v ->
                    Toast.makeText(getActivity(), "INGENIERIA DE SISTEMAS E INFORMATICA", Toast.LENGTH_SHORT).show()
            );

            return view;
        }
    }
}
