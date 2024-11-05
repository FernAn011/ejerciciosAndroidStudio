package com.example.practica3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class adaptador_pais extends BaseAdapter {

    private Context context;
    private List<String> paises;
    private HashMap<String, Integer> imagenesPaises;
    private HashMap<String, String> codigosPaises;

    public adaptador_pais(Context context, List<String> paises, HashMap<String, Integer> imagenesPaises, HashMap<String, String> codigosPaises) {
        this.context = context;
        this.paises = paises;
        this.imagenesPaises = imagenesPaises;
        this.codigosPaises = codigosPaises;
    }

    @Override
    public int getCount() {
        return paises.size();
    }

    @Override
    public Object getItem(int position) {
        return paises.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_estilo_1, parent, false);
        }

        String pais = paises.get(position);
        ImageView imageViewBandera = convertView.findViewById(R.id.imageView_bandera);
        TextView textViewNombrePais = convertView.findViewById(R.id.textView_nombrePais);
        TextView textViewCodigoPais = convertView.findViewById(R.id.textView_codigoPais);

        imageViewBandera.setImageResource(imagenesPaises.get(pais));
        textViewNombrePais.setText(pais);
        textViewCodigoPais.setText(codigosPaises.get(pais));

        return convertView;
    }
}