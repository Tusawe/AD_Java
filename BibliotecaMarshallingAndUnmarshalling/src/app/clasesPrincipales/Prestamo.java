package app.clasesPrincipales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "prestamo")
public class Prestamo{

    @XmlElement
    private String fecha_prestamo;
    @XmlElement
    private String fecha_devolucion;
    @XmlElement
    private String porroga;
    @XmlElement
    private String ejemplar;
    @XmlElement
    private String estado;
    @XmlElement
    private Usuario usuario;

    public String getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(String fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public String getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(String fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public String getPorroga() {
        return porroga;
    }

    public void setPorroga(String porroga) {
        this.porroga = porroga;
    }

    public String getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(String ejemplar) {
        this.ejemplar = ejemplar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Prestamo(String fecha_prestamo, String fecha_devolucion, String porroga, String ejemplar, String estado,
            Usuario usuario) {
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.porroga = porroga;
        this.ejemplar = ejemplar;
        this.estado = estado;
        this.usuario = usuario;
    }

    public Prestamo(){}
}