package com.example.windows.proyectopersonal.modelos;

/**
 * Created by Windows on 21/04/2015.
 */
public class modelotareas {
    private String id_tarea;
    private String titulo;
    private String fecha;
    private String descripcion;

    public modelotareas(String id_tarea, String titulo, String fecha, String descripcion){
        this.id_tarea = id_tarea;
        this.titulo = titulo;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public String getId_tarea() {return id_tarea;}

    public String getTitulo() {return titulo; }

    public String getFecha() { return fecha;  }

    public String getDescripcion() {return descripcion;}
}
