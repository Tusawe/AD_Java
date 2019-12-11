package com.iesvdc.acceso.simplecrud.model;

import java.util.Date;

public class Resenia {

    Integer id;
    Integer id_prestamo;
    String comentario;
    Date fecha;
    Integer estrellas;

    public Resenia() {
    }

    public Resenia(Integer id, Integer id_prestamo, String comentario, Date fecha,
            Integer estrellas) {
        this.id = id;
        this.id_prestamo = id_prestamo;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }

}