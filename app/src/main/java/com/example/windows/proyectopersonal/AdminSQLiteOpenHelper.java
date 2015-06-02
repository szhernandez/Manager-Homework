package com.example.windows.proyectopersonal;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    // Creamos el constructor
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Se crea la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table materias (id_materia INTEGER PRIMARY KEY AUTOINCREMENT, nombre text, profesor text) ");
        //db.execSQL("insert into materias ('Nombre de la materia', 'Nombre del profesor') ");
        db.execSQL("create table tareas (id_tarea INTEGER PRIMARY KEY AUTOINCREMENT, titulo text, fecha text, descripcion text, id_materia INTEGER, FOREIGN KEY(id_materia) REFERENCES materias(id_materia)) ");
        // db.execSQL("insert into tareas ('Aqui va el titulo', '01-1-2015', 'Breve descripcion de la tarea') ");
        // db.execSQL("create table tareas (titulo text PRIMARY KEY , fecha text, descripcion text) ");
        //
    }

    // borrar la tabla y crear la nueva tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists tareas");

        db.execSQL("create table tareas (id_tarea INTEGER PRIMARY KEY AUTOINCREMENT, titulo text, fecha text, descripcion text) ");

    }
}
