package app.clasesSecundarias;

import java.util.List;
import app.clasesPrincipales.Resenia;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "resenias")
public class Resenias{

    @XmlElement(name = "resenia")
    private List<Resenia> resenias = null;

    public List<Resenia> getResenias() {
        return resenias;
    }

    public void setResenias(List<Resenia> resenias) {
        this.resenias = resenias;
    }

    public void addResenia(Resenia resenia)
    {
        this.resenias.add(resenia);
    }

    public Resenias(List<Resenia> resenias) {
        this.resenias = resenias;
    }

    public Resenias(){}


}