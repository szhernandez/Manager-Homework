package com.example.windows.proyectopersonal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows.proyectopersonal.adaptadores.materiasadaptador;
import com.example.windows.proyectopersonal.adaptadores.tareasadaptador;
import com.example.windows.proyectopersonal.modelos.modelotareas;

import org.xml.sax.Parser;

import java.util.ArrayList;
import java.util.List;


public class mostrartareas extends ActionBarActivity {
    TextView titulo, fecha,descripcion, txtodo ;
    String Allconsulta;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    public String  bolsita;
    public String bolsitaobtenida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrartareas);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();



     //  txtodo= (TextView) findViewById(R.id.todo);
try {

    obtenerbolsita();
   // Toast.makeText(this, "ID_MATERIA: " + bolsitaobtenida, Toast.LENGTH_SHORT).show();
    //Creando el arreglo
    List<modelotareas> items = new ArrayList<>();
    //Conexion
    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "tareas", null, 1);
    SQLiteDatabase bd = admin.getWritableDatabase();
    Cursor fila = bd.rawQuery("select id_tarea, titulo, fecha, descripcion from tareas where id_materia='"+bolsitaobtenida+"'", null);
    //validando que existan datos
    if (fila.moveToFirst()) {

        for (fila.moveToFirst(); !fila.isAfterLast(); fila.moveToNext()) {
            items.add(new modelotareas(fila.getString(0), fila.getString(1), fila.getString(2), fila.getString(3)));
        }//EndFor


    }else{  Toast.makeText(this,"Aun no existen tareas, registre una",Toast.LENGTH_SHORT).show();}

    // Obtener el Recycler
    recycler = (RecyclerView) findViewById(R.id.r_tareas);
    recycler.setHasFixedSize(true);

    // Usar un administrador para LinearLayout
    lManager = new LinearLayoutManager(this);
    recycler.setLayoutManager(lManager);

    // Crear un nuevo adaptador
    adapter = new tareasadaptador(items);
    recycler.setAdapter(adapter);

}catch (Exception e){}

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mostrartareas, menu);

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
            //Opcion del menu Editar---Creando actividad
            case R.id.edit:
                Intent intent = new Intent(getBaseContext(), editartarea.class);
                startActivity(intent);
                return true;
        //Opcion del menu Eliminar---Creando actividad
            case R.id.delete:
                Intent intent2 = new Intent(getBaseContext(), eliminartarea.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Metodo-Su funcion es realizar Consulta
  /*  public void consulta(View v) {
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "tareas", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            String resultado = "";
            Bundle bundle = getIntent().getExtras();
            String  bolsita = bundle.getString("id_materia");

            Toast.makeText(this, "ID_MATERIA: " + bolsita, Toast.LENGTH_SHORT).show();
        //Seleccionando todos los datos cuando cumplan con la condicion
            Cursor fila = bd.rawQuery("select * from  where id_materia='"+bolsita+"'", null);
            int iidtarea = fila.getColumnIndex("id_tarea");
            int ititulo = fila.getColumnIndex("titulo");
            int ifecha = fila.getColumnIndex("fecha");
            int idescripcion = fila.getColumnIndex("descripcion");

            resultado = "   |+| Titulo |+| Fecha "+ "|+|"+ " Descripcion"+"\n";
            for (fila.moveToFirst(); !fila.isAfterLast(); fila.moveToNext()) {
                resultado = resultado + fila.getString(iidtarea) + "   |+|   " +
                        fila.getString(ititulo) + "  |+|  " + fila.getString(ifecha) + "  |+|  " +
                        fila.getString(idescripcion) + "\n";
            }
            txtodo.setText(resultado);


            bd.close();
        }catch (Exception e){ Toast.makeText(this, "Error: " + e, Toast.LENGTH_SHORT).show();}

    }
    */
    //Metodo que lanza la actividad para registrar una nueva tarea
    public void nuevatareita(View v){
        Bundle bundle = getIntent().getExtras();
        //Se incluye el "id_materia" el cual se incluira como clave foranea en la tabla tareas
        String  bolsita = bundle.getString("id_materia");
        Intent intent = new Intent(this, registrotareas.class);
        //Agregando extra
        intent.putExtra("id_materia", bolsita);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        if (bolsitaobtenida != null) {
            String bol =  bolsitaobtenida;
            savedInstanceState.putString("mibolsa", bol);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        if (bolsitaobtenida == null) {
            String bol = savedInstanceState.getString("mibolsa");
            asignar(bol);

        }
    }

    //Metodo para obtener los datos enviados por la actividad "Mostrar materias"
    public String obtenerbolsita(){
        //Validando los datos de la Bolsa
        if (bolsitaobtenida==null){
            try {
                //obteniendo bolsita de envio
                Bundle bundle = getIntent().getExtras();
                final String  bolsita = bundle.getString("id_materia");
                bolsitaobtenida = bolsita;
                return bolsitaobtenida;
            }catch (Exception e){}

        }
        //En caso de que no exitan datos en la bositaobtenida se asigna por defecto el numero "1"
        if (bolsitaobtenida==null){
            bolsitaobtenida="1";
            return bolsitaobtenida;
        }

       return bolsitaobtenida;
    }

   public String asignar(String asig){
       bolsitaobtenida=asig;
       return bolsitaobtenida;
   }
    @Override
    protected void onResume() {
      super.onResume();
    }
}
