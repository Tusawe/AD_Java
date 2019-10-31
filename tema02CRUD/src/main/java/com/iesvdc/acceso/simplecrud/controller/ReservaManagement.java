package com.iesvdc.acceso.simplecrud.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iesvdc.acceso.simplecrud.model.Conexion;

public class ReservaManagement extends HttpServlet {
    /*
    @Override 
    public void init() throws ServletException {
        
    }

    // findOne(id)
    @Override
    protected void doGet(
        HttpServletRequest req, // parámetros de la petición
        HttpServletResponse resp) // respuesta que genero
        throws ServletException, IOException {
            resp.setContentType("application/json");   
            PrintWriter out = resp.getWriter();
            String jsonObject="{}";
            // buscamos en la base de datos el objeto y devolvemos sus datos

            String id = req.getRequestURI().substring(req.getContextPath().length());
            id=id.replace("/user/", "");
            jsonObject="{salida: '"+id+"'}";

            // String id = req.getParameter("userid");

            Connection conexion;
            PreparedStatement pstm;
            String jdbcURL;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion =  DriverManager.getConnection(jdbcURL, "root", "example");

                String sql = "SELECT * FROM usuario WHERE id=?";

                pstm = conexion.prepareStatement(sql);

                pstm.setInt(1, Integer.parseInt(id));

                ResultSet rs = pstm.executeQuery();

                if ( rs.next() ) {  
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    // String id = rs.getString("id");
                    jsonObject="{"+ "\n"+
                        "'id':'"+id+"',"+ "\n"+
                        "'username':'"+username+"',"+ "\n"+
                        "'password':'"+password+"',"+ "\n"+
                        "'email':'"+email+"'"+ "\n"+
                        "}"; 
                    
                }
            }catch(Exception ex){

            }
            out.print(jsonObject.replaceAll("'", "\""));
            out.flush();
    }

    // CREAR
    @Override
    protected void doPost(
        HttpServletRequest req, // parámetros de la petición
        HttpServletResponse resp) // respuesta que genero
        throws ServletException, IOException {
       
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String email = req.getParameter("email");

            Connection conexion;
            PreparedStatement pstm;
            String jdbcURL;

            jdbcURL = JDBC_MYSQL_GESTION_RESERVAS;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion =  DriverManager.getConnection(jdbcURL, "root", "example");

                String sql = "INSERT INTO usuario (username,password,email) VALUES(?,?,?)";

                pstm = conexion.prepareStatement(sql);
                
                pstm.setString(1, username);
                pstm.setString(2, password);
                pstm.setString(3, email);

                if (pstm.executeUpdate() >0) {
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
    protected void doDelete(
        HttpServletRequest req, // parámetros de la petición
        HttpServletResponse resp) // respuesta que genero
        throws ServletException, IOException {
            resp.setContentType("application/json");   
            PrintWriter out = resp.getWriter();
            String jsonObject="{}";
            // buscamos en la base de datos el objeto y devolvemos sus datos

            String id = req.getRequestURI().substring(req.getContextPath().length());
            id=id.replace("/user/", "");
            jsonObject="{'error': '"+id+"'}";

            // String id = req.getParameter("userid");

            Connection conexion;
            PreparedStatement pstm;
            String jdbcURL;

            jdbcURL = JDBC_MYSQL_GESTION_RESERVAS;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion =  DriverManager.getConnection(jdbcURL, "root", "example");

                String sql = "DELETE FROM usuario WHERE id=?";

                pstm = conexion.prepareStatement(sql);

                pstm.setInt(1, Integer.parseInt(id));

                if ( pstm.executeUpdate()==0 ) {  
                    
                    jsonObject="{ "+
                        "'id':'"+id+"'}"; 
                    
                }
            }catch(Exception ex){

            }
            out.print(jsonObject.replaceAll("'", "\""));
            out.flush();

    }

    // ACTUALIZAR
    @Override
    protected void doPut(
        HttpServletRequest req, // parámetros de la petición
        HttpServletResponse resp) // respuesta que genero
        throws ServletException, IOException {

        Usuario user = new Gson().fromJson(req.getReader(), Usuario.class);

        String jdbcURL = JDBC_MYSQL_GESTION_RESERVAS;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conexion =  DriverManager.getConnection(jdbcURL, "root", "example");

                String sql = "UPDATE usuario SET username=?, password=?, email=? WHERE id=?"; 

                PreparedStatement pstm = conexion.prepareStatement(sql);
                
                pstm.setString(1, user.getUsername());
                pstm.setString(2, user.getPassword());
                pstm.setString(3, user.getEmail());
                pstm.setInt(4, user.getId());

                if (pstm.executeUpdate() >0) {
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
        
    }*/
}