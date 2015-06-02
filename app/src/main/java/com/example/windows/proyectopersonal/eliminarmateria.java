package com.example.windows.proyectopersonal;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class eliminarmateria extends ActionBarActivity {
    TextView txtidmateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminarmateria);
        txtidmateria = (TextView) findViewById(R.id.ed_idmateriadl);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_eliminarmateria, menu);
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
    public void eliminarmateria(View v) {

        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "materias", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            //Obteniendo datos del EditTex
            String buscar = txtidmateria.getText().toString();
            //Eliminado registro segun el ID obtenido del EditText
            int cant = bd.delete("materias","id_materia='" + buscar +"'", null);
            bd.close();
            //Limpiando EditText
            txtidmateria.setText("");
            //Verificando
            if (cant == 1) {
                Toast.makeText(this, "Se borró la materia", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No existe la materia",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){Toast.makeText(this,"Error" + e,Toast.LENGTH_SHORT).show();}
    }
}
