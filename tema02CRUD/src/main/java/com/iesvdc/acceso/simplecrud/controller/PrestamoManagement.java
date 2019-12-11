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
import com.iesvdc.acceso.simplecrud.model.Prestamo;

public class PrestamoManagement extends HttpServlet {

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
        id = id.replace("/prestamo/", "");
        jsonObject = "{salida: '" + id + "'}";

        // String id = req.getParameter("userid");

        
        try {
        
            String sql = "SELECT * FROM prestamo WHERE id=?";

            PreparedStatement pstm = conexion.prepareStatement(sql);

            pstm.setInt(1, Integer.parseInt(id));

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                int id_libro = rs.getInt("id_libro");
                int id_usuario = rs.getInt("id_usuario");
                Date fechaInicio = rs.getDate("fechaInicio");
                Date fechaEntrega = rs.getDate("fechaEntrega");
                String prorroga = rs.getString("prorroga");
                String estado = rs.getString("estado");
                jsonObject = "{" + "\n" 
                        + "'id':'" + id + "'," + "\n" 
                        + "'id_libro':'" + id_libro + "'," + "\n"
                        + "'id_usuario':'" + id_usuario + "'," + "\n" 
                        + "'fechaInicio':'" + fechaInicio + "'," + "\n"
                        + "'fechaEntrega':'" + fechaEntrega + "'," + "\n"
                        + "'prorroga':'" + prorroga + "'," + "\n"
                        + "'estado':'" + estado + "'" + "\n"
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

        String id_libro = req.getParameter("id_libro");
        String id_usuario = req.getParameter("id_usuario");
        String fechaInicio = req.getParameter("fechaInicio");
        String fechaEntrega = req.getParameter("fechaEntrega");
        String prorroga = req.getParameter("prorroga");
        String estado = req.getParameter("estado");

        try {
            String sql = "INSERT INTO prestamo (id_libro,id_usuario,fechaInicio,fechaEntrega,prorroga,estado) VALUES(?,?,?,?,?,?)";

            PreparedStatement pstm = conexion.prepareStatement(sql);

            pstm.setString(1, id_libro);
            pstm.setString(2, id_usuario);
            pstm.setString(3, fechaInicio);
            pstm.setString(4, fechaEntrega);
            pstm.setString(5, prorroga);
            pstm.setString(6, estado);          

            if (pstm.executeUpdate() > 0) {
                resp.getWriter().println("Prestamo insertado");
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
        id = id.replace("/prestamo/", "");
        jsonObject = "{'error': '" + id + "'}";

        // String id = req.getParameter("userid");

        PreparedStatement pstm;

        try {
            String sql = "DELETE FROM prestamo WHERE id=?";

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

        Prestamo prestamo = new Gson().fromJson(req.getReader(), Prestamo.class);
        try {
            String sql = "UPDATE prestamo SET prorroga=? WHERE id=?";
            PreparedStatement pstm = conexion.prepareStatement(sql);

            pstm.setInt(1, prestamo.getProrroga());
            pstm.setInt(2, prestamo.getId());

            if (pstm.executeUpdate() > 0) {
                resp.getWriter().println("Prestamo insertado");
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