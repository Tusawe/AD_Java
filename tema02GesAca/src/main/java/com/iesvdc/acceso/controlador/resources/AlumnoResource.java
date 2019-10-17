/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
/*
    @GET
    @Path("alumno/nombre/{nombre}/apellido/{apellido}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<AlumnoPOJO> getAlumnoByNombreApellido(
            @PathParam("nombre") String nombre,
            @PathParam("apellido") String apellido) {
        AlumnoDAO al_dao = new AlumnoDAO();
        List<AlumnoPOJO> list_al;
        try {
            list_al = al_dao.findByNombreApellido(nombre, apellido);
        } catch (Exception ex) {
            list_al = new ArrayList<>();
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return list_al;
    }*/

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
