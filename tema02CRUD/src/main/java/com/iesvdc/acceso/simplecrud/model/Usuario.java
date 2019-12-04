package com.iesvdc.acceso.simplecrud.model;

public class Usuario {
    
    String username;
    String email;
    String password;
    Integer id;
    String nombre;
    String apellido;
    String telefono;
    String dni;
    String tipo;
    
    Usuario() {

    }

    Usuario(String username, String password, String tipo){
        this.username = username;
        this.password = password;
        this.tipo = tipo;
    }

    Usuario(String username, String email, String password, Integer id, String nombre, String apellido,
            String tipo) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo = tipo;
    }

    Usuario(String username, String email, String password, Integer id, String nombre, String apellido,
            String telefono, String dni, String tipo) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.dni = dni;
        this.tipo = tipo;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return this.id;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}