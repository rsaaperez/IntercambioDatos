package com.rsaaperez.ral.intercambiodatos;

import java.io.Serializable;

/**
 * Created by Raúl on 07/11/2014.
 */
public class Agenda implements Serializable {
    private String nombre;
    private int telefono;

    public Agenda() {
    }

    public Agenda(String nombre, int telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Nombre: "+nombre+"\n  "+"Teléfono: "+telefono;
    }
}
