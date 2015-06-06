package com.example.windows.proyectopersonal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class TwoActionButtonsDialog extends DialogFragment {
 
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
 /*
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
 
        // Set the message to display.
        builder.setMessage("Are you sure?");
 
        // Set a listener to be invoked when the positive button of the dialog
        // is pressed.
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getActivity(), "Yes", Toast.LENGTH_SHORT).show();
            }
        });
 
        // Set a listener to be invoked when the negative button of the dialog
        // is pressed.
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getActivity(), "No", Toast.LENGTH_SHORT).show();
            }
        });
 
        // Create the AlertDialog object and return it
        return builder.create();
*/
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.titledialog)
                .setItems(R.array.colors_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        //Primero opcion seleccionada
                        if (which ==0){
                            Intent intent = new Intent(getActivity(), mostrartareas.class);
                            startActivity(intent);
                            Toast.makeText(getActivity(), "Seleccionaste el elemento "+which, Toast.LENGTH_SHORT).show();
                        }
                        //Segunda opcion
                        if (which ==1){
                            Toast.makeText(getActivity(), "Seleccionaste el elemento "+which, Toast.LENGTH_SHORT).show();
                        }
                        //Tercera opcion
                        if (which ==2){
                            Toast.makeText(getActivity(), "Seleccionaste el elemento "+which, Toast.LENGTH_SHORT).show();
                        }
                    }

                });



        return builder.create();
    }
}