package app.clasesPrincipales;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "resenia")
public class Resenia{

    @XmlAttribute
    private String libro;
    @XmlElement
    private String estrellas;
    @XmlElement
    private String comentario;
    @XmlElement
    private Usuario usuario; 
    @XmlElement
    private boolean anonimo;
    @XmlElement
    private Date fecha;

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(String estrellas) {
        this.estrellas = estrellas;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isAnonimo() {
        return anonimo;
    }

    public void setAnonimo(boolean anonimo) {
        this.anonimo = anonimo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Resenia(String libro, String estrellas, String comentario, Usuario usuario, boolean anonimo, Date fecha) {
        this.libro = libro;
        this.estrellas = estrellas;
        this.comentario = comentario;
        this.usuario = usuario;
        this.anonimo = anonimo;
        this.fecha = fecha;
    }

    public Resenia(){}


}