package com.iesvdc.acceso.simplecrud.model;

public class Usuario {
    
    String username;
    String email;
    String password;
    Integer id;
    
    Usuario() {

    }

    Usuario(String username, String email, String password){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    Usuario(Integer id, String username, String email, String password){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
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


}