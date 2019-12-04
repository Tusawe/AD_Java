package app.clasesSecundarias;

import java.util.ArrayList;
import java.util.List;
import app.clasesPrincipales.Prestamo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "prestamos")
public class Prestamos{

    @XmlElement(name = "prestamo")
    private List<Prestamo> prestamos=null;

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public void addPrestamo(Prestamo prestamo)
    {
        this.prestamos.add(prestamo);
    }


    public Prestamos(){
        this.prestamos = new ArrayList<Prestamo>();
    }
}