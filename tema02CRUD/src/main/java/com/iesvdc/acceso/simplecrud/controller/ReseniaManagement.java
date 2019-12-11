package com.iesvdc.acceso.simplecrud.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import com.iesvdc.acceso.simplecrud.model.Usuario;
import com.iesvdc.acceso.simplecrud.model.Conexion;
import com.iesvdc.acceso.simplecrud.model.Resenia;

public class ReseniaManagement extends HttpServlet {

    // private static final String JDBC_MYSQL_GESTION_RESERVAS =
    // "jdbc:mysql://192.168.99.101:33306/gestion_reservas";
    private static final String JDBC_MYSQL_BIBLIOTECA = "jdbc:mysql://localhost:33306/biblioteca";

    private Conexion conn;
    private Connection conexion;

    @Override
    public void init() throws ServletException {
        this.conn = new Conexion();
        this.conexion = conn.getConnection();
    }

    // findOne(id)
    @Override
    protected void doGet(HttpServletRequest req, // parámetros de la petición
            HttpServletResponse resp) // respuesta que genero
            throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String jsonObject = "{}";
        // buscamos en la base de datos el objeto y devolvemos sus datos

        String id = req.getRequestURI().substring(req.getContextPath().length());
        id = id.replace("/resenia/", "");
        jsonObject = "{salida: '" + id + "'}";

        // String id = req.getParameter("userid");

        
        try {
        
            String sql = "SELECT * FROM resenia WHERE id=?";

            PreparedStatement pstm = conexion.prepareStatement(sql);

            pstm.setInt(1, Integer.parseInt(id));

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                int id_prestamo = rs.getInt("id_prestamo");
                String comentario = rs.getString("comentario");
                Date fecha = rs.getDate("fecha");
                int estrellas = rs.getInt("estrellas");
                jsonObject = "{" + "\n" 
                        + "'id':'" + id + "'," + "\n" 
                        + "'id_prestamo':'" + id_prestamo + "'," + "\n"
                        + "'comentario':'" + comentario + "'," + "\n" 
                        + "'fecha':'" + fecha + "'," + "\n"
                        + "'estrellas':'" + estrellas + "'" + "\n"
                        + "}";

            }
        } catch (Exception ex) {
            resp.sendRedirect("error.jsp");
        }
        out.print(jsonObject.replaceAll("'", "\""));
        out.flush();
    }

    // CREAR
    @Override
    protected void doPost(HttpServletRequest req, // parámetros de la petición
            HttpServletResponse resp) // respuesta que genero
            throws ServletException, IOException {

        String id_prestamo = req.getParameter("id_prestamo");
        String comentario = req.getParameter("comentario");
        String fecha = req.getParameter("fecha");
        String estrellas = req.getParameter("estrellas");

        try {
            String sql = "INSERT INTO resenia (id_prestamo,comentario,fecha,estrellas) VALUES(?,?,?,?)";

            PreparedStatement pstm = conexion.prepareStatement(sql);

            pstm.setString(1, id_prestamo);
            pstm.setString(2, comentario);
            pstm.setString(3, fecha);
            pstm.setString(4, estrellas);           

            if (pstm.executeUpdate() > 0) {
                resp.getWriter().println("Resenia insertado");
            } else {
                resp.getWriter().println("No se ha podido insertar");
            }

        } catch (Exception ex) {
            resp.sendRedirect("error.jsp");
            // resp.getWriter().println(ex.getMessage());
            // resp.getWriter().println(ex.getLocalizedMessage());
            // resp.getWriter().println("Imposible conectar a la BBDD");
        }

        resp.sendRedirect(".");

    }

    // BORRAR
    @Override
    protected void doDelete(HttpServletRequest req, // parámetros de la petición
            HttpServletResponse resp) // respuesta que genero
            throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String jsonObject = "{}";
        // buscamos en la base de datos el objeto y devolvemos sus datos

        String id = req.getRequestURI().substring(req.getContextPath().length());
        id = id.replace("/resenia/", "");
        jsonObject = "{'error': '" + id + "'}";

        // String id = req.getParameter("userid");

        PreparedStatement pstm;

        try {
            String sql = "DELETE FROM resenia WHERE id=?";

            pstm = conexion.prepareStatement(sql);

            pstm.setInt(1, Integer.parseInt(id));

            if (pstm.executeUpdate() == 0) {

                jsonObject = "{ " + "'id':'" + id + "'}";

            }
        } catch (Exception ex) {
            resp.sendRedirect("error.jsp");
        }
        out.print(jsonObject.replaceAll("'", "\""));
        out.flush();
    }

    // ACTUALIZAR
    @Override
    protected void doPut(HttpServletRequest req, // parámetros de la petición
            HttpServletResponse resp) // respuesta que genero
            throws ServletException, IOException {

        Resenia resenia = new Gson().fromJson(req.getReader(), Resenia.class);
        try {
            String sql = "UPDATE resenia SET comentario=?, estrellas=? WHERE id=?";
            PreparedStatement pstm = conexion.prepareStatement(sql);

            pstm.setString(1, resenia.getComentario());
            pstm.setInt(2, resenia.getEstrellas());
            pstm.setInt(3, resenia.getId());

            if (pstm.executeUpdate() > 0) {
                resp.getWriter().println("Resenia insertado");
            } else {
                resp.getWriter().println("No se ha podido insertar");
            }
        } catch (Exception ex) {
            resp.sendRedirect("error.jsp");
            //resp.getWriter().println(ex.getMessage());
            //resp.getWriter().println(ex.getLocalizedMessage());
            // resp.getWriter().println("Imposible conectar a la BBDD");
        }

        resp.sendRedirect("..");

    }

    public void destroy() {

        if (conexion != null)
            try {
                conexion.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                
            }
    }
}