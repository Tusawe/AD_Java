# Práctica 6

## Implementar el back-end y el front-end ahora como servicio REST con Jackson, HTML5 y JavaScript

Para esta práctica, vamos a tomar como partida este proyecto:

https://gitlab.iesvirgendelcarmen.com/juangu/GestionAcademicaJDBC


### Activando Jersey en nuestro servidor

Para gestionar nuestro servicio REST y el marshalling/unmarshalling de objetos tanto en XML como en JSON, vamos a utilizar [Jersey](https://jersey.github.io). Puedes encontrar toda la [documentación](https://jersey.github.io/documentation/latest/index.html) así como la [referencia a la API](https://jersey.github.io/apidocs/1.19.1/jersey/index.html) en su Web.


#### Modificacones al web.xml


Activar el servlet Jersey e indicarle dónde está el paquete que describe el servicio (que estará accesible desde la path /rest):

```xml
    <servlet>
        <servlet-name>jersey-servlet</servlet-name>
        <servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
        <init-param>
            <param-name>jersey.config.server.provider.packages</param-name>
            <param-value>com.iesvdc.acceso.controlador.resources</param-value>
        </init-param>
        <init-param>
            <param-name>com.sun.jersey.api.json.POJOMappingFeature</param-name>
            <param-value>true</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>jersey-servlet</servlet-name>
        <url-pattern>/rest/*</url-pattern>
    </servlet-mapping>
```


#### Modificaciones en el pom.xml

Añadir librerías (en *dependencies*) vía maven:

```xml
<!-- Dependencias para JAXB-->
    <dependency>
      <groupId>javax.xml.bind</groupId>
      <artifactId>jaxb-api</artifactId>
      <version>2.3.0</version>
    </dependency>
    <dependency>
      <groupId>com.sun.xml.bind</groupId>
      <artifactId>jaxb-core</artifactId>
      <version>2.3.0</version>
    </dependency>
    <dependency>
      <groupId>com.sun.xml.bind</groupId>
      <artifactId>jaxb-impl</artifactId>
      <version>2.3.0</version>
    </dependency>
<!-- Estamos usando Java Beans -->
    <dependency>
      <groupId>javax.activation</groupId>
      <artifactId>activation</artifactId>
      <version>1.1.1</version>
    </dependency>
<!-- Dependencias para Jersey Servlet -->
    <dependency>
      <groupId>org.glassfish.jersey.media</groupId>
      <artifactId>jersey-media-jaxb</artifactId>
      <version>2.25.1</version>
    </dependency>
    <dependency>
        <groupId>org.glassfish.jersey.media</groupId>
        <artifactId>jersey-media-json-jackson</artifactId>
        <version>2.25.1</version>
    </dependency>
    <dependency>
      <groupId>org.glassfish.jersey.containers</groupId>
      <artifactId>jersey-container-servlet-core</artifactId>
      <version>2.25.1</version>
    </dependency>
```

#### Ejemplo de servicio REST

```java
package com.iesvdc.acceso.controlador.resources;

import com.iesvdc.acceso.modelo.AlumnoDAO;
import com.iesvdc.acceso.modelo.AlumnoPOJO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Juangu <jgutierrez at iesvirgendelcarmen.coms>
 */
@Path("/")
public class AlumnoResource {

    @GET
    @Path("alumno")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<AlumnoPOJO> getAlumnos() {
        AlumnoDAO al_dao = new AlumnoDAO();
        List<AlumnoPOJO> list_al;
        try {
            list_al = al_dao.findAll();
        } catch (Exception ex) {
            list_al = new ArrayList<>();
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return list_al;
    }

    @GET
    @Path("alumno/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public AlumnoPOJO getAlumnoById(@PathParam("id") String id) {
        AlumnoDAO al_dao = new AlumnoDAO();
        AlumnoPOJO al;
        try {
            al = al_dao.findById(Integer.parseInt(id));
        } catch (Exception ex) {
            al = new AlumnoPOJO(-1, "Error", "Error");
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return al;
    }

    @GET
    @Path("alumno/apellido/{apellido}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<AlumnoPOJO> getAlumnoByApellido(@PathParam("apellido") String apellido) {
        AlumnoDAO al_dao = new AlumnoDAO();
        List<AlumnoPOJO> list_al;
        try {
            list_al = al_dao.findByApellido(apellido);
        } catch (Exception ex) {
            list_al = new ArrayList<>();
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return list_al;
    }

    @GET
    @Path("alumno/nombre/{nombre}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<AlumnoPOJO> getAlumnoByNombre(@PathParam("nombre") String nombre) {
        AlumnoDAO al_dao = new AlumnoDAO();
        List<AlumnoPOJO> list_al;
        try {
            list_al = al_dao.findByNombre(nombre);
        } catch (Exception ex) {
            list_al = new ArrayList<>();
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return list_al;
    }

    @PUT
    @Path("alumno/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void updateAlumno(@PathParam("id") Integer id, AlumnoPOJO al) {
        AlumnoDAO al_dao = new AlumnoDAO();
        try {
            al_dao.update(id, al);
        } catch (Exception ex) {
            Logger.getLogger(ex.getLocalizedMessage());
        }
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("alumno")
    public Response createAlumno(AlumnoPOJO al) {
        AlumnoDAO al_dao = new AlumnoDAO();
        try {
            al_dao.create(al);
        } catch (Exception ex) {
            Logger.getLogger(ex.getLocalizedMessage());
            return Response.status(400).entity(al).build();
        }
        return Response.status(200).entity(al).build();
    }
    
    @DELETE
    @Path("alumno/{id}")
    public void deleteAlumno(@PathParam("id") Integer id) {
        AlumnoDAO al_dao = new AlumnoDAO();
        try {
            al_dao.delete(id);
        } catch (Exception ex) {
            Logger.getLogger(ex.getLocalizedMessage());
        }
    }
}

```