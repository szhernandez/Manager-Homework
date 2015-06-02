package com.example.windows.proyectopersonal;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class eliminartarea extends ActionBarActivity {
    EditText titulo, fecha,descripcion, txtid ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminartarea);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        txtid= (EditText) findViewById(R.id.ed_id);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_eliminartarea, menu);
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
    public void baja(View v) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "tareas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String buscar = txtid.getText().toString();
        //Eliminando registro de base de datos
        int cant = bd.delete("tareas","id_tarea='" + buscar +"'", null);
        bd.close();

        //Limpiando EditText
        txtid.setText("");
        //Comprobando si se borro la tarea
        if (cant == 1) {
            Toast.makeText(this, "Se borró la tarea", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe la tarea",Toast.LENGTH_SHORT).show();
        }
    }
}