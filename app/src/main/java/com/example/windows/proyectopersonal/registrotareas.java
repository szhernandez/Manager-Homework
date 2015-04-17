package com.example.windows.proyectopersonal;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class registrotareas extends ActionBarActivity implements View.OnClickListener {
    EditText titulo, fecha,descripcion ;
    Button guardartarea;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrotareas);
        //agrego para crear el action bar
        //Mostrar barra para regresar de actividad
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(4);

        titulo = (EditText) findViewById(R.id.ed_nombretarea);
        fecha = (EditText) findViewById(R.id.ed_fecha);
        descripcion = (EditText) findViewById(R.id.ed_descripcion);
        guardartarea= (Button) findViewById(R.id.btnguardar);

        fecha.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registrotareas, menu);
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
            case R.id.ed_fecha:
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Lanzar Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Setear valor en editText
                                fecha.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                dpd.show();
            break;
        }
     }

    public void asignarfecha(View v){


    }

    public void alta (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "tareas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String bdtitulo = titulo.getText().toString();
        String bdfecha = fecha.getText().toString();
        String bddescripcion = descripcion.getText().toString();
        ContentValues registro = new ContentValues();

        registro.put("titulo", bdtitulo);
        registro.put("fecha", bdfecha);
        registro.put("descripcion", bddescripcion);

        bd.insert("tareas", null, registro);
        bd.close();

        titulo.setText("");
        fecha.setText("");
        descripcion.setText("");

        Toast.makeText(this, "Se agrego una nueva tarea", Toast.LENGTH_SHORT).show();

    }

    public void consulta(View v) {
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "tareas", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String buscartitulo = titulo.getText().toString();

            Cursor fila = bd.rawQuery("select titulo, fecha, descripcion from tareas where titulo='" + buscartitulo +"'", null);
            if (fila.moveToFirst()) {
                titulo.setText(fila.getString(0));
                fecha.setText(fila.getString(1));
                descripcion.setText(fila.getString(2));

            } else {
                Toast.makeText(this,"No existen tareas para el dia selecionado",Toast.LENGTH_SHORT).show();
            }
            bd.close();
        }catch (Exception e){ Toast.makeText(this, "Error: " + e, Toast.LENGTH_SHORT).show();}

    }

    public void baja(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "tareas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String bdtitulo = titulo.getText().toString();
        int cant = bd.delete("tareas","titulo='" + bdtitulo +"'", null);
        bd.close();


        titulo.setText("");
        fecha.setText("");
        descripcion.setText("");

        if (cant == 1) {
            Toast.makeText(this, "Se borró la tarea",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe la tarea",Toast.LENGTH_SHORT).show();
        }
    }

    public void modificacion (View v) {
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "tareas", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            String bdtitulo = titulo.getText().toString();
            String bdfecha = fecha.getText().toString();
            String bddescripcion = descripcion.getText().toString();

            ContentValues registro = new ContentValues();

            registro.put("titulo", bdtitulo);
            registro.put("fecha", bdfecha);
            registro.put("descripcion", bddescripcion);


            int cant = bd.update("tareas", registro, "titulo='" + bdtitulo+"'", null);
            bd.close();

            if (cant == 1) {
                Toast.makeText(this, "Se modificaron los datos",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No existe la tarea",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){Toast.makeText(this, "Error: " + ex, Toast.LENGTH_SHORT).show();}

    }

    public void limpia (View v){

        titulo.setText("");
        fecha.setText("");
        descripcion.setText("");
    }


}
