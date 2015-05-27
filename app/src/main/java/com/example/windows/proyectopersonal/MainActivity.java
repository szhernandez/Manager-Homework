package com.example.windows.proyectopersonal;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows.proyectopersonal.adaptadores.materiasadaptador;
import com.example.windows.proyectopersonal.modelos.modelomaterias;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    com.getbase.floatingactionbutton.AddFloatingActionButton boton, botontareas;
    TextView txttodo;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton = (com.getbase.floatingactionbutton.AddFloatingActionButton) findViewById(R.id.btnmateria);
     //  botontareas = (com.getbase.floatingactionbutton.AddFloatingActionButton) findViewById(R.id.btntareas);
        txttodo = (TextView) findViewById(R.id.todomat);
        //Mostrar barra para regresar de actividad
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(4);
        boton.setOnClickListener(this);

        //Creando metodo para llenar arreglo de las materias
        try {
            //Creando el arreglo
            List<modelomaterias> items = new ArrayList<>();
            //Conexion
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "materias", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor fila = bd.rawQuery("select * from materias", null);
            //validando que existan datos
            if (fila.moveToFirst()) {

                for (fila.moveToFirst(); !fila.isAfterLast(); fila.moveToNext()) {
                    items.add(new modelomaterias(fila.getString(0), fila.getString(1), fila.getString(2)));
                }//EndFor


            }else{  Toast.makeText(this,"Aun no existen materias, registre una",Toast.LENGTH_SHORT).show();}

            // Obtener el Recycler
            recycler = (RecyclerView) findViewById(R.id.r_materias);
            recycler.setHasFixedSize(true);

            // Usar un administrador para LinearLayout
            lManager = new LinearLayoutManager(this);
            recycler.setLayoutManager(lManager);

            // Crear un nuevo adaptador
            adapter = new materiasadaptador(items);
            recycler.setAdapter(adapter);

        }catch (Exception e){}

        //Probando el codigo para onClicListener



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //agrego para crear el action bar
        // MenuInflater menuInflater = getMenuInflater();
        // menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.editmateria:
                Intent intent = new Intent(getBaseContext(), editarmateria.class);

                startActivity(intent);
                return true;

            case R.id.deletemateria:
                Intent intent2 = new Intent(getBaseContext(), eliminarmateria.class);

                startActivity(intent2);
                return true;
            case R.id.showtareas:
                Intent intent3 = new Intent(getBaseContext(), mostrartareas.class);

                startActivity(intent3);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnregistrar:

                break;


        }
    }


    public void nuevamateria(View v){
        Intent intent = new Intent(this, registromaterias.class);
        startActivity(intent);
    }
    public void consultamaterias(View v) {
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "materias", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            String resultado = "";

            Cursor fila = bd.rawQuery("select * from materias", null);
            int iidtarea = fila.getColumnIndex("id_materia");
            int ititulo = fila.getColumnIndex("nombre");
            int ifecha = fila.getColumnIndex("profesor");


            resultado = "   |+| Nombre |+| Profesor "+ "\n";
            for (fila.moveToFirst(); !fila.isAfterLast(); fila.moveToNext()) {
                resultado = resultado + fila.getString(iidtarea) + "   |+|   " +
                        fila.getString(ititulo) + "  |+|  " + fila.getString(ifecha) + "  |+|  " +
                         "\n";
            }
            txttodo.setText(resultado);


            bd.close();
        }catch (Exception e){ Toast.makeText(this, "Error: " + e, Toast.LENGTH_SHORT).show();}

    }
}
