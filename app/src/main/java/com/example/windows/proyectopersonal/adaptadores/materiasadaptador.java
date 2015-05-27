package com.example.windows.proyectopersonal.adaptadores;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows.proyectopersonal.MainActivity;
import com.example.windows.proyectopersonal.R;
import com.example.windows.proyectopersonal.editartarea;
import com.example.windows.proyectopersonal.modelos.modelomaterias;
import com.example.windows.proyectopersonal.mostrartareas;
import  com.example.windows.proyectopersonal.metodos;

import java.util.List;

/**
 * Created by Windows on 22/04/2015.
 */
public class materiasadaptador extends RecyclerView.Adapter<materiasadaptador.materiasViewHolder> {

    private List<modelomaterias> items;
    public static class materiasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private static final String TAG = "CustomAdapter";
        public TextView id_materia;
        public TextView nombre;
        public TextView profesor;


        public materiasViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });
            id_materia = (TextView) v.findViewById(R.id.cm_idmateria);
            nombre = (TextView) v.findViewById(R.id.cm_nombre);
            profesor = (TextView) v.findViewById(R.id.cm_profesor);

        }

        @Override
        public void onClick(View view) {

        }
    }
        public materiasadaptador(List<modelomaterias> items) {
            this.items = items;
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public materiasViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.materias_card, viewGroup, false);
            return new materiasViewHolder(v);
        }

        @Override
        public void onBindViewHolder(materiasViewHolder viewHolder, int i) {
            viewHolder.id_materia.setText(String.valueOf(items.get(i).getId_materia()));
            viewHolder.nombre.setText(items.get(i).getNombre());
            viewHolder.profesor.setText("Profesor: "+items.get(i).getProfesor());
            //String.valueOF solo usado para convertir numeros a textos



        }


}
