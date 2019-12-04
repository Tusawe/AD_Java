package app.clasesPrincipales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import app.clasesSecundarias.Libros;
import app.clasesSecundarias.Prestamos;
import app.clasesSecundarias.Resenias;
import app.clasesSecundarias.Usuarios;

@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "biblioteca")
public class Biblioteca{
    @XmlElement
    private Usuarios usuarios;
    @XmlElement
    private Libros libros;
    @XmlElement
    private Prestamos prestamos;
    @XmlElement
    private Resenias resenias;


    
    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Libros getLibros() {
        return libros;
    }

    public void setLibros(Libros libros) {
        this.libros = libros;
    }

    public Prestamos getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Prestamos prestamos) {
        this.prestamos = prestamos;
    }

    public Resenias getResenias() {
        return resenias;
    }

    public void setResenias(Resenias resenias) {
        this.resenias = resenias;
    }


}