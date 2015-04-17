package com.example.windows.proyectopersonal;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    com.getbase.floatingactionbutton.AddFloatingActionButton boton, botontareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton = (com.getbase.floatingactionbutton.AddFloatingActionButton) findViewById(R.id.btnmateria);
        botontareas = (com.getbase.floatingactionbutton.AddFloatingActionButton) findViewById(R.id.btntareas);
        //Mostrar barra para regresar de actividad
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(4);
        boton.setOnClickListener(this);
        botontareas.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //agrego para crear el action bar
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnmateria:
                Intent intent = new Intent(getBaseContext(), registromaterias.class);

                startActivity(intent);
                break;
            case R.id.btntareas:
                Intent intent2 = new Intent(getBaseContext(), registrotareas.class);

                startActivity(intent2);
                break;

            case R.id.ed_fecha:

                break;
        }
    }



}
