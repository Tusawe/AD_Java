package com.iesvdc.acceso.simplecrud.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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

public class LibroManagement extends HttpServlet {

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
        id = id.replace("/libro/", "");
        jsonObject = "{salida: '" + id + "'}";

        // String id = req.getParameter("userid");

        
        try {
        
            String sql = "SELECT * FROM libro WHERE id=?";

            PreparedStatement pstm = conexion.prepareStatement(sql);

            pstm.setInt(1, Integer.parseInt(id));

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                String isbn10 = rs.getString("isbn10");
                String isbn13 = rs.getString("isbn13");
                String titulo = rs.getString("titulo");
                String editorial = rs.getString("editorial");
                String fechaPublicacion = rs.getString("fechaPublicacion");
                String nPaginas = rs.getString("nPaginas");
                
                jsonObject = "{" + "\n" + "'id':'" + id + "'," + "\n" + "'isbn10':'" + isbn10 + "'," + "\n"
                        + "'isbn13':'" + isbn13 + "'," + "\n" + "'titulo':'" + titulo + "'" + "\n"
                        + "'editorial':'" + editorial + "'" + "\n" + "'fechaPublicacion':'" + fechaPublicacion + "'" + "\n"
                        + "'nPaginas':'" + nPaginas + "'" + "}";

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

        String isbn10 = req.getParameter("isbn10");
        String isbn13 = req.getParameter("isbn13");
        String titulo = req.getParameter("titulo");
        String editorial = req.getParameter("editorial");
        String fechaPublicacion = req.getParameter("fechaPublicacion");
        String nPaginas = req.getParameter("nPaginas");

        try {
            String sql = "INSERT INTO libro (isbn10,isbn13,titulo,editorial,fechaPublicacion,nPaginas) VALUES(?.?,?,?,?,?)";

            PreparedStatement pstm = conexion.prepareStatement(sql);

            pstm.setString(1, isbn10);
            pstm.setString(2, isbn13);
            pstm.setString(3, titulo);
            pstm.setString(4, editorial);
            pstm.setString(5, fechaPublicacion);
            pstm.setString(6, nPaginas);

            if (pstm.executeUpdate() > 0) {
                resp.getWriter().println("Libro insertado");
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
        id = id.replace("/libro/", "");
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
            String sql = "UPDATE usuario SET username=?, password=?, dni=?, nombre=?, apellido=?, email=?, telefono=?, tipo=? WHERE id=?";

            PreparedStatement pstm = conexion.prepareStatement(sql);

            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getPassword());
            pstm.setString(3, user.getDni());
            pstm.setString(4, user.getNombre());
            pstm.setString(5, user.getApellido());
            pstm.setString(6, user.getEmail());
            pstm.setString(7, user.getTelefono());
            pstm.setString(8, user.getTipo());
            pstm.setInt(9, user.getId());

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

        resp.sendRedirect(".");

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