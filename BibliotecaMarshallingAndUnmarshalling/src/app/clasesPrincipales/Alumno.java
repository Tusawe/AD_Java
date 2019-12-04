package app.clasesPrincipales;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlAccessorType(XmlAccessType.FIELD)


@XmlRootElement(name = "alumno")
public class Alumno extends Usuario{
    @XmlElement
    private String nombre;
    @XmlElement
    private String apellidos;
    @XmlElement
    private String email;
    @XmlElement
    private String sexo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Alumno(String id, String username, String password, String nombre, String apellidos, String email,
            String sexo) {
        super(id, username, password);
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.sexo = sexo;
    }

    public Alumno(String nombre, String apellidos, String email, String sexo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.sexo = sexo;
    }

    public Alumno(){}



}