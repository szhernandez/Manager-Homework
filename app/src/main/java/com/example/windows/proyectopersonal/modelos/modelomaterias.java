package com.example.windows.proyectopersonal.modelos;

/**
 * Created by Windows on 21/04/2015.
 */
public class modelomaterias {
    private String id_materia;
    private String nombre;
    private String profesor;


    public modelomaterias(String id_materia, String nombre, String profesor){
        this.id_materia = id_materia;
        this.nombre = nombre;
        this.profesor = profesor;

    }

    public String getId_materia() {return id_materia;}

    public String getNombre() {return nombre; }

    public String getProfesor() { return profesor;  }

}
