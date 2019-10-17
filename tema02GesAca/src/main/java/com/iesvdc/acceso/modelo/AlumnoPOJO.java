/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.modelo;

/**
 *
 * @author juangu
 */
public class AlumnoPOJO {
    private Integer id;
    private String nombre;
    private String apellido;

    public AlumnoPOJO() {
        this.id = null;
        this.nombre = null;
        this.apellido = null;
    }
    
    public AlumnoPOJO(String nombre, String apellido) {
        this.id = null;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public AlumnoPOJO(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "AlumnoPOJO:{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + '}';
    }
    
    
    
}
