package com.example.windows.proyectopersonal.adaptadores;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.Application;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows.proyectopersonal.MainActivity;
import com.example.windows.proyectopersonal.R;
import com.example.windows.proyectopersonal.TwoActionButtonsDialog;
import com.example.windows.proyectopersonal.editartarea;
import com.example.windows.proyectopersonal.eliminarmateria;
import com.example.windows.proyectopersonal.modelos.modelomaterias;
import com.example.windows.proyectopersonal.mostrartareas;
import com.example.windows.proyectopersonal.metodos;

import java.util.List;

/**
 * Created by Windows on 22/04/2015.
 */
public class materiasadaptador extends RecyclerView.Adapter<materiasadaptador.materiasViewHolder>  {

    private List<modelomaterias> items;

    public static class materiasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private static final String TAG = "CustomAdapter";
        public TextView id_materia;
        public TextView nombre;
        public TextView profesor, Layout_materias;

        private ClickListener clickListener;

        public materiasViewHolder(View v) {
            super(v);


            id_materia = (TextView) v.findViewById(R.id.cm_idmateria);
            nombre = (TextView) v.findViewById(R.id.cm_nombre);
            profesor = (TextView) v.findViewById(R.id.cm_profesor);
            v.setOnClickListener(this);


        }

        /* Interface for handling clicks - both normal and long ones. */
        public interface ClickListener {

            /**
             * Called when the view is clicked.
             *
             * @param v           view that is clicked
             * @param position    of the clicked item
             * @param isLongClick true if long click, false otherwise
             */
            public void onClick(View v, int position, boolean isLongClick);

        }

        /* Setter for listener. */
        public void setClickListener(ClickListener clickListener) {
            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View v) {

            // If not long clicked, pass last variable as false.
            clickListener.onClick(v, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {

            // If long clicked, passed last variable as true.
            clickListener.onClick(v, getPosition(), true);
            return true;
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
    public void onBindViewHolder(final materiasViewHolder viewHolder, int i) {
        viewHolder.id_materia.setText(String.valueOf(items.get(i).getId_materia()));
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.profesor.setText("Profesor: " + items.get(i).getProfesor());
        //String.valueOF solo usado para convertir numeros a textos

        viewHolder.setClickListener(new materiasViewHolder.ClickListener() {

            @Override
            public void onClick(View v, int pos, boolean isLongClick) {
                if (isLongClick) {
                    // View v at position pos is long-clicked.
                    Log.d("CustomAdapter", "id long" + viewHolder.nombre.getText());
                } else {
                    // View v at position pos is clicked.
                    Log.d("CustomAdapter", "id " + viewHolder.nombre.getText() + "Get context: " + v.getContext());
                    try {
                        Intent intent = new Intent(v.getContext(), mostrartareas.class);
                        //you can pass on the Pojo with PARCELABLE
                        intent.putExtra("id_materia", viewHolder.id_materia.getText());
                        v.getContext().startActivity(intent);

                      // FragmentTransaction ft = getFragmentManager().beginTransaction();
                      // DialogFragment dialog = new TwoActionButtonsDialog();
                      // dialog.show(,"dialog");


                    } catch (Exception e) {
                        Log.d("CustomAdapter", "error " + e);
                    }


                }
            }
        });


    }


}
