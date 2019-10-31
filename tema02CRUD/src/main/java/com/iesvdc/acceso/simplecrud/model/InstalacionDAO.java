package com.iesvdc.acceso.simplecrud.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstalacionDAO {
     // CRUD, findAll, finById, count     
    Connection conn;   
    
    public InstalacionDAO(){         
        conn = new Conexion().getConnection();     
    }     
    
    public InstalacionDAO(Connection conexion){         
        this.conn=conexion;     
    }

    public boolean create(Instalacion al){
        boolean exito=true;
        try {        
            // Conexion conexion = new Conexion();    
            // Connection conn = conexion.getConnection();
            String sql = "INSERT INTO instalacion VALUES (NULL,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, al.getName());
            pstmt.executeUpdate();  
            // conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR:  "+ex.getMessage());
            exito = false;
        } 
        return exito;
    }

    public Instalacion findById(Integer id){
        Instalacion al;
        try {            
            // Conexion conexion = new Conexion();    
            // Connection conn = conexion.getConnection();
            String sql = 
                "SELECT * FROM instalacion WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            System.err.println("\nID:: "+id+"\n");
            ResultSet rs  = pstmt.executeQuery();
            rs.next();
            al = new Instalacion(
                rs.getInt("id"),
                rs.getString("nombre"));                    
            // conn.close();
        } catch (SQLException ex) {
            al = null;
        } 
        return al;
    }
    
    public List<Instalacion> findAll() {
        Instalacion al;
        List<Instalacion> li_al = new ArrayList();
        try {            
            // conectamos a la BBDD
            // Conexion conexion = new Conexion();    
            // Connection conn = conexion.getConnection();
            // esta es la cadena SQL de conslulta
            String sql = "SELECT * FROM instalacion";
            // usamos este objeto porque es más seguro
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // ejecutar la consulta contra la base de datos y 
            // devuelve el resultado en el ResultSet (parecido a 
            // un Array con iterador
            ResultSet rs  = pstmt.executeQuery();
            // recorro el resultset mientras tengo datos
            while (rs.next()){
                al = new Instalacion(
                    rs.getInt("id"),
                    rs.getString("nombre"));
                li_al.add(al);
            }
            // cerramos la conexión
            // conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR"+ ex.getMessage());
            li_al = null;
        } 
        return li_al;
    }

    public List<Instalacion> findByNombre(String nombre){
        Instalacion al;
        List<Instalacion> li_al = new ArrayList();
        try {            
            // conectamos a la BBDD
            Conexion conexion = new Conexion();    
            Connection conn = conexion.getConnection();
            // esta es la cadena SQL de conslulta
            String sql = "SELECT * FROM instalacion WHERE nombre=?";
            // usamos este objeto porque es más seguro
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            // ejecutar la consulta contra la base de datos y 
            // devuelve el resultado en el ResultSet (parecido a 
            // un Array con iterador
            ResultSet rs  = pstmt.executeQuery();
            // recorro el resultset mientras tengo datos
            while (rs.next()){
                al = new Instalacion(
                    rs.getInt("id"),
                    rs.getString("nombre"));
                li_al.add(al);
            }
            // cerramos la conexión
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR"+ ex.getMessage());
            li_al = null;
        } 
        return li_al;
    }

    public boolean update(Integer old_id, Instalacion new_al) {
        boolean exito=true;
        try {            
            Conexion conexion = new Conexion();    
            Connection conn = conexion.getConnection();
            String sql = 
                "UPDATE instalacion SET id=?, nombre=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(3, old_id);
            pstmt.setInt(1, new_al.getId());
            pstmt.setString(2, new_al.getName());
            if (pstmt.executeUpdate()==0) {
                exito = false;
            }
            conn.close();
        } catch (SQLException ex) {
            exito = false;
        } 
        return exito;
    }

    public boolean delete(Instalacion al){        
        return delete(al.getId());
    }
    
    public boolean delete(Integer id_al){
        boolean exito=true;
        try {            
            Conexion conexion = new Conexion();    
            Connection conn = conexion.getConnection();
            String sql = "DELETE FROM instalacion WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id_al);
            if (pstmt.executeUpdate()==0) {
                exito = false;
            }
            conn.close();
        } catch (SQLException ex) {
            exito = false;
        } 
        return exito;
    }
    
}