package app;

import java.io.File;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import app.clasesPrincipales.Biblioteca;
import app.clasesPrincipales.Libro;

import app.clasesSecundarias.Libros;
import app.clasesSecundarias.Prestamos;
import app.clasesSecundarias.Usuarios;

public class UnMarshalling {
    public static void main(String[] args) throws Exception {

        try {

            Biblioteca biblioteca = new Biblioteca();

            JAXBContext jaxbContext = JAXBContext.newInstance(Biblioteca.class, Usuarios.class, Libros.class,
                    Prestamos.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            biblioteca = (Biblioteca) jaxbUnmarshaller.unmarshal(new File("src/app/xmls/biblioteca.xml"));

            System.out.println("-----------------");

            for (Libro libro : biblioteca.getLibros().getLibros()) {
                System.out.println("\t ID Libro: " + libro.getId() + " ISBN:" + libro.getIsbn() + " páginas:"
                        + libro.getPaginas() + " editorial: " + libro.getEditorial() + " titulo: " + libro.getTitulo()
                        + " año de publicacion" + libro.getAnioPublicacion());
            }
            System.out.println("-----------------");

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

   
}