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


public class editartarea extends ActionBarActivity {
    EditText titulo, fecha,descripcion, txtodo, txtid ;
    private int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editartarea);
        txtodo= (EditText) findViewById(R.id.todo);

        titulo = (EditText) findViewById(R.id.ed_nombretarea);
        fecha = (EditText) findViewById(R.id.ed_fecha);
        descripcion = (EditText) findViewById(R.id.ed_descripcion);
        txtodo= (EditText) findViewById(R.id.todo);
        txtid= (EditText) findViewById(R.id.ed_id);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_editartarea, menu);
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
    public void consulta(View v) {
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "tareas", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String buscar = txtid.getText().toString();
            String resultado = "";


            // Cursor fila = bd.rawQuery("select titulo, fecha, descripcion from tareas where titulo='" + buscartitulo +"'", null);
            Cursor fila = bd.rawQuery("select * from tareas where id_tarea='"+buscar+"'", null);
            /*int iidtarea = fila.getColumnIndex("id_tarea");
            int ititulo = fila.getColumnIndex("titulo");
            int ifecha = fila.getColumnIndex("fecha");
            int idescripcion = fila.getColumnIndex("descripcion");
            */
            if (fila.moveToFirst()) {

                titulo.setText(fila.getString(1));
                fecha.setText(fila.getString(2));
                descripcion.setText(fila.getString(3));

           //Desbloqueando EditTex
                titulo.setEnabled(true);
                fecha.setEnabled(true);
                descripcion.setEnabled(true);


             } else {
                Toast.makeText(this,"No existen tareas con el ID selecionado",Toast.LENGTH_SHORT).show();
            }
            bd.close();
        }catch (Exception e){ Toast.makeText(this, "Error: " + e, Toast.LENGTH_SHORT).show();}

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

            String buscar = txtid.getText().toString();
            int cant = bd.update("tareas", registro, "id_tarea='" + buscar+"'", null);
            bd.close();

            if (cant == 1) {
                Toast.makeText(this, "Se modificaron los datos",Toast.LENGTH_SHORT).show();

                titulo.setEnabled(false);
                fecha.setEnabled(false);
                descripcion.setEnabled(false);
            } else {
                Toast.makeText(this, "No existe la tarea",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){Toast.makeText(this, "Error: " + ex, Toast.LENGTH_SHORT).show();}

    }
    public void asignarfecha(View v){
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

    }
}
