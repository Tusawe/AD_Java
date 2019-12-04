package app.clasesSecundarias;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import app.clasesPrincipales.*;

@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "usuarios")
public class Usuarios{

    @XmlElement(name = "alumnos")
    private List<Alumno> alumnos = null;
    @XmlElement(name = "profesores")
    private List<Profesor> profesores = null;
    @XmlElement(name = "admins")
    private List<Admin> admins = null;
    
    
    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public void addAlumno(Alumno alumno)
    {
        this.alumnos.add(alumno);
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }

    public void addProfesor(Profesor profesor)
    {
        this.profesores.add(profesor);
    }

    public List<Admin> getAdmin() {
        return admins;
    }

    public void setAdmin(List<Admin> admin) {
        this.admins = admin;
    }

    public void addAdmin(Admin admin)
    {
        this.admins.add(admin);
    }

    public Usuarios() {
        this.alumnos = new ArrayList<Alumno>() ;
        this.profesores = new ArrayList<Profesor>();
        this.admins = new ArrayList<Admin>();
    }

    

}