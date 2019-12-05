package com.iesvdc.acceso.simplecrud.controller.resources;

import com.iesvdc.acceso.simplecrud.model.Instalacion;
import com.iesvdc.acceso.simplecrud.model.InstalacionDAO;

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

@Path("/")
public class InstalacionResource {

    @GET
    @Path("instalacion")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Instalacion> getInstalacions() {
        InstalacionDAO al_dao = new InstalacionDAO();
        List<Instalacion> list_al;
        try {
            list_al = al_dao.findAll();
        } catch (Exception ex) {
            list_al = new ArrayList<>();
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return list_al;
    }

    @GET
    @Path("instalacion/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Instalacion getInstalacionById(@PathParam("id") String id) {
        InstalacionDAO al_dao = new InstalacionDAO();
        Instalacion al;
        try {
            al = al_dao.findById(Integer.parseInt(id));
        } catch (Exception ex) {
            al = new Instalacion(-1, "Error");
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return al;
    }

    @GET
    @Path("instalacion/nombre/{nombre}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Instalacion> getInstalacionByNombre(@PathParam("nombre") String nombre) {
        InstalacionDAO al_dao = new InstalacionDAO();
        List<Instalacion> list_al;
        try {
            list_al = al_dao.findByNombre(nombre);
        } catch (Exception ex) {
            list_al = new ArrayList<>();
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return list_al;
    }

    @PUT
    @Path("instalacion/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void updateInstalacion(@PathParam("id") Integer id, Instalacion al) {
        InstalacionDAO al_dao = new InstalacionDAO();
        try {
            al_dao.update(id, al);
        } catch (Exception ex) {
            Logger.getLogger(ex.getLocalizedMessage());
        }
    }

    
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("instalacion")
    public Response createInstalacion(Instalacion al) {
        InstalacionDAO al_dao = new InstalacionDAO();
        try {
            al_dao.create(al);
        } catch (Exception ex) {
            Logger.getLogger(ex.getLocalizedMessage());
            return Response.status(400).entity(al).build();
        }
        return Response.status(200).entity(al).build();
    }
    
    @DELETE
    @Path("instalacion/{id}")
    public void deleteInstalacion(@PathParam("id") Integer id) {
        InstalacionDAO al_dao = new InstalacionDAO();
        try {
            al_dao.delete(id);
        } catch (Exception ex) {
            Logger.getLogger(ex.getLocalizedMessage());
        }
    }
}
