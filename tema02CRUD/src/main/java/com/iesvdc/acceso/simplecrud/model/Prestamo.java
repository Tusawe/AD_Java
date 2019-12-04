package com.iesvdc.acceso.simplecrud.model;

public class Prestamo {

    Integer id;
    Integer id_libro;
    Integer id_usuario;
    String fechaInicio;
    String fechaEntrega;
    Integer prorroga;
    String estado;

    public Prestamo() {
    }

    public Prestamo(Integer id, Integer id_libro, Integer id_usuario, String fechaInicio, String fechaEntrega,
            Integer prorroga, String estado) {
        this.id = id;
        this.id_libro = id_libro;
        this.id_usuario = id_usuario;
        this.fechaInicio = fechaInicio;
        this.fechaEntrega = fechaEntrega;
        this.prorroga = prorroga;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_libro() {
        return id_libro;
    }

    public void setId_libro(Integer id_libro) {
        this.id_libro = id_libro;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getProrroga() {
        return prorroga;
    }

    public void setProrroga(Integer prorroga) {
        this.prorroga = prorroga;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}