package app.clasesSecundarias;

import java.util.ArrayList;
import java.util.List;
import app.clasesPrincipales.Libro;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "libros")
public class Libros{

    @XmlElement(name = "libro")
    private List<Libro> libros = null;

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public void addLibro(Libro libro)
    {
        this.libros.add(libro);
    }

    public Libros() {
        this.libros = new ArrayList<Libro>();
    }

}