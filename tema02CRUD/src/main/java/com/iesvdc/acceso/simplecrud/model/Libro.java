package com.iesvdc.acceso.simplecrud.model;

public class Libro {

    Integer id;
    String isbn10;
    String isbn13;
    String titulo;
    String editorial;
    String fechaPublicacion;
    Integer nPaginas;

    public Libro() {
    }

    public Libro(Integer id, String isbn10, String isbn13, String titulo, String editorial, String fechaPublicacion,
            Integer nPaginas) {
        this.id = id;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.titulo = titulo;
        this.editorial = editorial;
        this.fechaPublicacion = fechaPublicacion;
        this.nPaginas = nPaginas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Integer getnPaginas() {
        return nPaginas;
    }

    public void setnPaginas(Integer nPaginas) {
        this.nPaginas = nPaginas;
    }
    
}