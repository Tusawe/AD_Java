package com.iesvdc.acceso.simplecrud.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import com.iesvdc.acceso.simplecrud.model.Usuario;
import com.iesvdc.acceso.simplecrud.model.Conexion;

public class UserManagement extends HttpServlet {

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
        id = id.replace("/user/", "");
        jsonObject = "{salida: '" + id + "'}";

        // String id = req.getParameter("userid");

        
        try {
        
            String sql = "SELECT * FROM usuario WHERE id=?";

            PreparedStatement pstm = conexion.prepareStatement(sql);

            pstm.setInt(1, Integer.parseInt(id));

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String dni = rs.getString("dni");
                String telefono = rs.getString("telefono");
                String tipo = rs.getString("tipo");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                jsonObject = "{" + "\n" 
                        + "'id':'" + id + "'," + "\n" 
                        + "'username':'" + username + "'," + "\n"
                        + "'password':'" + password + "'," + "\n" 
                        + "'email':'" + email + "'," + "\n"
                        + "'dni':'" + dni + "'," + "\n"
                        + "'telefono':'" + telefono + "'," + "\n"
                        + "'tipo':'" + tipo + "'," + "\n"
                        + "'nombre':'" + nombre + "'," + "\n"
                        + "'apellido':'" + apellido + "'" + "\n"
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

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String dni = req.getParameter("dni");
        String telefono = req.getParameter("telefono");
        String tipo = req.getParameter("tipo");
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");

        try {
            String sql = "INSERT INTO usuario (username,password,dni,nombre,apellido,email,telefono,tipo) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement pstm = conexion.prepareStatement(sql);

            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, dni);
            pstm.setString(4, nombre);
            pstm.setString(5, apellido);
            pstm.setString(6, email);
            pstm.setString(7, telefono);
            pstm.setString(8, tipo);            

            if (pstm.executeUpdate() > 0) {
                resp.getWriter().println("Usuario insertado");
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
        id = id.replace("/user/", "");
        jsonObject = "{'error': '" + id + "'}";

        // String id = req.getParameter("userid");

        PreparedStatement pstm;

        try {
            String sql = "DELETE FROM usuario WHERE id=?";

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

        Usuario user = new Gson().fromJson(req.getReader(), Usuario.class);
        try {
            String sql = "UPDATE usuario SET username=?, email=? WHERE id=?";
            PreparedStatement pstm = conexion.prepareStatement(sql);

            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getEmail());
            pstm.setInt(3, user.getId());

            if (pstm.executeUpdate() > 0) {
                resp.getWriter().println("Usuario insertado");
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