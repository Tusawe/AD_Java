package app;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import app.clasesPrincipales.Admin;
import app.clasesPrincipales.Alumno;
import app.clasesPrincipales.Biblioteca;
import app.clasesPrincipales.Libro;
import app.clasesPrincipales.Prestamo;
import app.clasesPrincipales.Profesor;
import app.clasesPrincipales.Usuario;
import app.clasesSecundarias.Libros;
import app.clasesSecundarias.Prestamos;
import app.clasesSecundarias.Usuarios;

public class Marshalling {
    public static void main(String[] args) throws Exception 
    {

        try {
			
            Biblioteca biblioteca = new Biblioteca();

           

            Alumno alum1 = new Alumno("1652", "FJBVIO", "password1", "Marta", "Jimenez", "mjimenez@hotmail.com", "M");
            Alumno alum2 = new Alumno("2929", "fb32fbf", "password2", "Juan", "Lopez", "jlopez@gmail.com", "H");
            Alumno alum3 = new Alumno("33542", "passss", "password2", "jose Luis", "Gomez Lopez", "jlgomezlopez@yahoo.es", "H");

            Profesor profe1 = new Profesor("23188989P", "prof", "pass1", "02342342f", "Juan Gualberto", "Gutierrez Marin", "jggm@iesvirgendelcarmen.com", "33451298324");

            Admin admin1 = new Admin("Admin1", "Admin1", "****1****");
            Admin admin2 = new Admin("Admin2", "Admin2", "****2****");

            Usuarios usuarios = new Usuarios();
            usuarios.addAlumno(alum1);
            usuarios.addAlumno(alum2);
            usuarios.addAlumno(alum3);
            usuarios.addProfesor(profe1);
            usuarios.addAdmin(admin1);
            usuarios.addAdmin(admin2);

            biblioteca.setUsuarios(usuarios);

            Libros libros = new Libros();

            Libro book1 = new Libro(134123, "isbn10", "23", "anaya", "Lo que el viento se llevó", "1999");
            Libro book2 = new Libro(34348, "isbn12", "32", "planet", "Yo, julia", "2007");
            Libro book3 = new Libro(98394, "isbn10", "55", "libroRR", "Un mar violeta oscuro", "2003");
            Libro book4 = new Libro(77373, "isbn13", "100", "Coral", "La casa alemana", "1989");

            libros.addLibro(book1);
            libros.addLibro(book2);
            libros.addLibro(book3);
            libros.addLibro(book4);

            biblioteca.setLibros(libros);

            Prestamos prestamos = new Prestamos();

            Usuario usu1 = new Usuario("Tusawe", "Paco", "FFog15");
            Prestamo presta1 = new Prestamo("12/03/2002", "10/04/2002", "prorroga1", "ejemp1", "nuevo", usu1);
            Prestamo presta2 = new Prestamo("03/07/2003", "10/07/2003", "prorroga2", "ejemp1powijef", "deteriorado", usu1);

            prestamos.addPrestamo(presta1);
            prestamos.addPrestamo(presta2);

            biblioteca.setPrestamos(prestamos);
            
            JAXBContext contextObj = JAXBContext.newInstance(Biblioteca.class, Usuarios.class, Libros.class, Prestamos.class);
            Marshaller  marshallerObj = contextObj.createMarshaller();
            
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
			
			marshallerObj.marshal(biblioteca, new File("src/app/xmls/biblioteca.xml"));
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
        
    }
}