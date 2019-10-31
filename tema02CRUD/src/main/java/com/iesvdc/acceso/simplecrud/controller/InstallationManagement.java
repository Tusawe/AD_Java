package com.iesvdc.acceso.simplecrud.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


import com.iesvdc.acceso.simplecrud.model.Conexion;
import com.iesvdc.acceso.simplecrud.model.Instalacion;

public class InstallationManagement extends HttpServlet {

    @Override
    public void init() throws ServletException {
    }

    // Obtener una instalacion
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Devuelve un JSON
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        // La gitanada padre
        String jsonObject = "{}";
        // buscamos en la base de datos el objeto y devolvemos sus datos

        // Limpiamos de la URL el /user/ para quedarnos con el ID
        String id = req.getRequestURI().substring(req.getContextPath().length());
        id = id.replace("/installation/", "");

        // Creado este por si hay un fallo al devolver la instalación, lo devuelve así,
        // evita ataques
        jsonObject = "{salida: '" + id + "'}";

        // Trabajo con la BBDD
        Conexion conn = new Conexion();
        Connection conexion = conn.getConnection();
        PreparedStatement pstm;

        try {
            
            String sql = "SELECT * FROM instalacion WHERE id=?";

            pstm = conexion.prepareStatement(sql);

            pstm.setInt(1, Integer.parseInt(id));

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                jsonObject = "{" + "\n" + "'id':'" + id + "'," + "\n" + "'nombre':'" + nombre + "'" + "\n" + "}";
            }

            conexion.close();

        } catch (Exception ex) {

        }
        out.print(jsonObject.replaceAll("'", "\""));
        out.flush();
    }

    // CREAR
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");

        Conexion conn = new Conexion();
        Connection conexion = conn.getConnection();
        PreparedStatement pstm;
        String jdbcURL;


        try {
            
            String sql = "INSERT INTO instalacion (nombre) VALUES(?)";

            pstm = conexion.prepareStatement(sql);

            pstm.setString(1, name);

            if (pstm.executeUpdate() > 0) {
                resp.getWriter().println("Usuario insertado");
            } else {
                resp.getWriter().println("No se ha podido insertar");
            }

            conexion.close();
        } catch (Exception ex) {
            resp.getWriter().println(ex.getMessage());
            resp.getWriter().println(ex.getLocalizedMessage());
            // resp.getWriter().println("Imposible conectar a la BBDD");
        } 

        resp.sendRedirect(".");

    }

    // BORRAR
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String jsonObject = "{}";
        // buscamos en la base de datos el objeto y devolvemos sus datos

        String id = req.getRequestURI().substring(req.getContextPath().length());
        id = id.replace("/installation/", "");
        jsonObject = "{'error': '" + id + "'}";

        // String id = req.getParameter("userid");

        Conexion conn = new Conexion();
        Connection conexion = conn.getConnection();
        PreparedStatement pstm;
       
        try {
            String sql = "DELETE FROM instalacion WHERE id=?";

            pstm = conexion.prepareStatement(sql);

            pstm.setInt(1, Integer.parseInt(id));

            if (pstm.executeUpdate() == 0) {

                jsonObject = "{ " + "'id':'" + id + "'}";

            }
            conexion.close();
        } catch (Exception ex) {

        }
        out.print(jsonObject.replaceAll("'", "\""));
        out.flush();

    }

    // ACTUALIZAR
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Instalacion installation = new Gson().fromJson(req.getReader(), Instalacion.class);

        try {
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String sql = "UPDATE instalacion SET nombre=? WHERE id=?";

            PreparedStatement pstm = conexion.prepareStatement(sql);

            pstm.setString(1, installation.getName());
            pstm.setInt(2, installation.getId());

            if (pstm.executeUpdate() > 0) {
                resp.getWriter().println("Usuario actualizado");
            } else {
                resp.getWriter().println("No se ha podido insertar");
            }

            conexion.close();
        } catch (Exception ex) {
            resp.getWriter().println(ex.getMessage());
            resp.getWriter().println(ex.getLocalizedMessage());
            // resp.getWriter().println("Imposible conectar a la BBDD");
        }

        resp.sendRedirect("../");

    }

}