package com.example.windows.proyectopersonal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class registromaterias extends ActionBarActivity {
EditText Nmateria, Nprofesor;

    Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registromaterias);

        Nmateria = (EditText) findViewById(R.id.ed_nombremateria);
        Nprofesor = (EditText) findViewById(R.id.ed_maestro);
        boton = (Button) findViewById(R.id.btnguardar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registromaterias, menu);
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

    public void alta (View v) {
        try {


            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "materias", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            String bdnombre = Nmateria.getText().toString();
            String bdprofesor = Nprofesor.getText().toString();

            ContentValues registro = new ContentValues();

            registro.put("nombre", bdnombre);
            registro.put("profesor", bdprofesor);

            bd.insert("materias", null, registro);
            bd.close();

            Nmateria.setText("");
            Nprofesor.setText("");


            Toast.makeText(this, "Se agrego una nueva materia", Toast.LENGTH_SHORT).show();
        }catch (Exception e){  Toast.makeText(this, "Error: " + e, Toast.LENGTH_SHORT).show();}

    }

    public void consultamate(View v) {
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "materias", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String buscar = Nmateria.getText().toString();

            Cursor fila = bd.rawQuery("select nombre, profesor where nombre=" + buscar, null);
            if (fila.moveToFirst()) {
                Nmateria.setText(fila.getString(0));
                Nprofesor.setText(fila.getString(1));


            } else {
                Toast.makeText(this,"No existen materias con el nombre seleccionado",Toast.LENGTH_SHORT).show();
            }
            bd.close();
        }catch (Exception e){ Toast.makeText(this, "Error: " + e, Toast.LENGTH_SHORT).show();}

    }

    public void modificacion (View v) {
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "tareas", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            String bdnombre = Nmateria.getText().toString();
            String bdprofesor = Nprofesor.getText().toString();

            ContentValues registro = new ContentValues();

            registro.put("titulo", bdnombre);
            registro.put("fecha", bdprofesor);


            int cant = bd.update("materias", registro, "nombre=" + bdnombre, null);
            bd.close();

            if (cant == 1) {
                Toast.makeText(this, "Se modificaron los datos",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No existe la tarea",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){Toast.makeText(this, "Error: " + ex, Toast.LENGTH_SHORT).show();}

    }

}
