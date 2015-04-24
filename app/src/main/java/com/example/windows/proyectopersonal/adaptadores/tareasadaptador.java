package com.example.windows.proyectopersonal.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.windows.proyectopersonal.R;
import com.example.windows.proyectopersonal.modelos.modelotareas;

import java.util.List;

/**
 * Created by Windows on 22/04/2015.
 */
public class tareasadaptador extends RecyclerView.Adapter<tareasadaptador.tareasViewHolder> {

    private List<modelotareas> items;
    public static class tareasViewHolder extends RecyclerView.ViewHolder {
        public TextView id_tarea;
        public TextView titulo;
        public TextView descripcion;
        public TextView fecha;

        public tareasViewHolder(View v) {
            super(v);
            id_tarea = (TextView) v.findViewById(R.id.c_id);
            titulo = (TextView) v.findViewById(R.id.c_titulo);
            descripcion = (TextView) v.findViewById(R.id.c_descripcion);
            fecha = (TextView) v.findViewById(R.id.c_fecha);
        }
    }
        public tareasadaptador(List<modelotareas> items) {
            this.items = items;
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public tareasViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.tareas_card, viewGroup, false);
            return new tareasViewHolder(v);
        }

        @Override
        public void onBindViewHolder(tareasViewHolder viewHolder, int i) {
            viewHolder.id_tarea.setText(String.valueOf(items.get(i).getId_tarea()));
            viewHolder.titulo.setText(items.get(i).getTitulo());
            viewHolder.descripcion.setText(items.get(i).getDescripcion());
            //String.valueOF solo usado para convertir numeros a textos
            viewHolder.fecha.setText("Fecha de entrega :"+String.valueOf(items.get(i).getFecha()));
        }

}
