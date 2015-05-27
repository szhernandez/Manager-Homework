package com.example.windows.proyectopersonal;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;


/**
 * Created by Windows on 26/05/2015.
 */
public class metodos extends ActionBarActivity {

    public void metodos(){}

    public void abrir(){
        Intent intent3 = new Intent(getBaseContext(), mostrartareas.class);

        startActivity(intent3);
    }


}
