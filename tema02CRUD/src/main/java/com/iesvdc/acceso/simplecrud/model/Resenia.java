package com.iesvdc.acceso.simplecrud.model;

public class Resenia {

    Integer id;
    Integer id_prestamo;
    Boolean anonimo;
    String comentario;
    String fecha;
    Integer estrellas;

    public Resenia() {
    }

    public Resenia(Integer id, Integer id_prestamo, Boolean anonimo, String comentario, String fecha,
            Integer estrellas) {
        this.id = id;
        this.id_prestamo = id_prestamo;
        this.anonimo = anonimo;
        this.comentario = comentario;
        this.fecha = fecha;
        this.estrellas = estrellas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(Integer id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public Boolean getAnonimo() {
        return anonimo;
    }

    public void setAnonimo(Boolean anonimo) {
        this.anonimo = anonimo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }

}