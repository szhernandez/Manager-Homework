package com.example.windows.proyectopersonal;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


public class editarmateria extends ActionBarActivity {
    EditText txtidmateria, txtnombre, txtprofesor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editarmateria);
        txtidmateria = (EditText) findViewById(R.id.ed_idmateria);
        txtnombre = (EditText) findViewById(R.id.ed_nombremateria);
        txtprofesor = (EditText) findViewById(R.id.ed_profesor);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_editarmateria, menu);
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

    public void buscarmateria(View v) {
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "materias", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String buscar = txtidmateria.getText().toString();
            String resultado = "";


            // Cursor fila = bd.rawQuery("select * from materias where titulo='" + buscartitulo +"'", null);
            Cursor fila = bd.rawQuery("select * from materias where id_materia='"+buscar+"'", null);
            /*int iidtarea = fila.getColumnIndex("id_tarea");
            int ititulo = fila.getColumnIndex("titulo");
            int ifecha = fila.getColumnIndex("fecha");
            int idescripcion = fila.getColumnIndex("descripcion");
            */
            if (fila.moveToFirst()) {

               txtnombre.setText(fila.getString(1));
               txtprofesor.setText(fila.getString(2));


                //Desbloqueando EditTex
                txtnombre.setEnabled(true);
                txtprofesor.setEnabled(true);
                txtidmateria.setEnabled(false);



            } else {
                Toast.makeText(this, "No existen tareas con el ID selecionado", Toast.LENGTH_SHORT).show();
            }
            bd.close();
        }catch (Exception e){ Toast.makeText(this, "Error: " + e, Toast.LENGTH_SHORT).show();}

    }

    public void modificarmateria (View v) {
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "materias", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            String bdtitulo = txtnombre.getText().toString();
            String bdfecha = txtprofesor.getText().toString();


            ContentValues registro = new ContentValues();

            registro.put("nombre", bdtitulo);
            registro.put("profesor", bdfecha);


            String buscar = txtidmateria.getText().toString();
            int cant = bd.update("materias", registro, "id_materia='" + buscar+"'", null);
            bd.close();

            if (cant == 1) {
                Toast.makeText(this, "Se modificaron los datos",Toast.LENGTH_SHORT).show();

                txtidmateria.setEnabled(false);
                txtprofesor.setEnabled(false);

            } else {
                Toast.makeText(this, "No existe la materia",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){Toast.makeText(this, "Error: " + ex, Toast.LENGTH_SHORT).show();}

    }

}
