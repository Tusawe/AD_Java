/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author juangu
 */
public class AlumnoDAO {
    // CRUD, findAll, finById, count
    Connection conn;
    Context ctx;
    DataSource ds;

    public AlumnoDAO(){
        conn = new Conexion().getConnection();
    }

    public AlumnoDAO(Connection conexion){
        this.conn=conexion;
    }

    public boolean create(AlumnoPOJO al){
        boolean exito=true;
        try {        
            // Conexion conexion = new Conexion();    
            // Connection conn = conexion.getConnection();
            String sql = 
                "INSERT INTO ALUMNO VALUES (NULL,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, al.getNombre());
            pstmt.setString(2, al.getApellido());
            pstmt.executeUpdate();  
            // conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR:  "+ex.getMessage());
            exito = false;
        } 
        return exito;
    }
    
    public AlumnoPOJO findById(Integer id){
        AlumnoPOJO al;
        try {            
            // Conexion conexion = new Conexion();    
            // Connection conn = conexion.getConnection();
            String sql = 
                "SELECT * FROM ALUMNO WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            System.err.println("\nID:: "+id+"\n");
            ResultSet rs  = pstmt.executeQuery();
            rs.next();
            al = new AlumnoPOJO(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apellido"));                    
            // conn.close();
        } catch (SQLException ex) {
            al = null;
        } 
        return al;
    } 
    
    public List<AlumnoPOJO> findAll() {
        AlumnoPOJO al;
        List<AlumnoPOJO> li_al = new ArrayList();
        try {            
            // conectamos a la BBDD
            // Conexion conexion = new Conexion();    
            // Connection conn = conexion.getConnection();
            // esta es la cadena SQL de conslulta
            String sql = "SELECT * FROM ALUMNO";
            // usamos este objeto porque es más seguro
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // ejecutar la consulta contra la base de datos y 
            // devuelve el resultado en el ResultSet (parecido a 
            // un Array con iterador
            ResultSet rs  = pstmt.executeQuery();
            // recorro el resultset mientras tengo datos
            while (rs.next()){
                al = new AlumnoPOJO(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"));
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
    
    
    /**
     * Este método busca Alumnos en la BBDD por nombre:
     * @param nombre
     * El nombre a buscar
     * @return 
     * Devuelve:
     * null: si hay algún error (no se puede conectar a la BBDD...). <br>
     * ArrayList vacío (length == 0): si no hay nadie con ese nombre. <br>  
     * ArrayList con Alumnos: si hay alumnos con ese nombre.<br>
     */
    public List<AlumnoPOJO> findByNombre(String nombre){
        AlumnoPOJO al;
        List<AlumnoPOJO> li_al = new ArrayList();
        try {            
            // conectamos a la BBDD
            Conexion conexion = new Conexion();    
            Connection conn = conexion.getConnection();
            // esta es la cadena SQL de conslulta
            String sql = "SELECT * FROM ALUMNO WHERE nombre=?";
            // usamos este objeto porque es más seguro
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            // ejecutar la consulta contra la base de datos y 
            // devuelve el resultado en el ResultSet (parecido a 
            // un Array con iterador
            ResultSet rs  = pstmt.executeQuery();
            // recorro el resultset mientras tengo datos
            while (rs.next()){
                al = new AlumnoPOJO(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"));
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
    
        
    /**
     * Este método busca Alumnos en la BBDD por apellido:
     * @param apellido
     * El nombre a buscar
     * @return 
     * Devuelve:
     * null: si hay algún error (no se puede conectar a la BBDD...). <br>
     * ArrayList vacío (length == 0): si no hay nadie con ese nombre. <br>  
     * ArrayList con Alumnos: si hay alumnos con ese nombre.<br>
     */
    public List<AlumnoPOJO> findByApellido(String apellido){
        AlumnoPOJO al;
        List<AlumnoPOJO> li_al = new ArrayList();
        try {            
            // conectamos a la BBDD
            Conexion conexion = new Conexion();    
            Connection conn = conexion.getConnection();
            // esta es la cadena SQL de conslulta
            String sql = "SELECT * FROM ALUMNO WHERE apellido=?";
            // usamos este objeto porque es más seguro
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, apellido);
            // ejecutar la consulta contra la base de datos y 
            // devuelve el resultado en el ResultSet (parecido a 
            // un Array con iterador
            ResultSet rs  = pstmt.executeQuery();
            // recorro el resultset mientras tengo datos
            while (rs.next()){
                al = new AlumnoPOJO(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"));
                li_al.add(al);
            }
            // cerramos la conexión
            conn.close();
        } catch (SQLException ex) {
            li_al = null;
        } 
        return li_al;
    }
    
    /**
     * Este método actualiza un alumno en la BBDD
     * @param old_al
     * El objeto que contiene los datos antiguos del alumno 
     * @param new_al
     * El objeto que contiene los datos nuevos del alumno 
     * @return 
     * true si se lleva a cabo correctamente <br>
     * false si no se actualiza nada (error de conexión, no 
     * estaba el alumno en la BBDD...) <br>
     */
    public boolean update(AlumnoPOJO old_al, AlumnoPOJO new_al) {
        
        return update(old_al.getId(),new_al);
    }
    
    /**
     * Este método actualiza un alumno en la BBDD
     * @param old_id
     * El id antiguo del alumno 
     * @param new_al
     * El objeto que contiene al alumno actualizado
     * @return 
     * true si se lleva a cabo correctamente <br>
     * false si no se actualiza nada (error de conexión, no 
     * estaba el alumno en la BBDD...) <br>
     */
    public boolean update(Integer old_id, AlumnoPOJO new_al) {
        boolean exito=true;
        try {            
            Conexion conexion = new Conexion();    
            Connection conn = conexion.getConnection();
            String sql = 
                "UPDATE ALUMNO SET id=?, nombre=?, apellido=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(4, old_id);
            pstmt.setInt(1, new_al.getId());
            pstmt.setString(2, new_al.getNombre());
            pstmt.setString(3, new_al.getApellido());
            if (pstmt.executeUpdate()==0) {
                exito = false;
            }
            conn.close();
        } catch (SQLException ex) {
            exito = false;
        } 
        return exito;
    }
    
    /**
     * Este método borra de la BBDD el Alumno cuyos datos 
     * coinciden con los de el objeto que se le pasa como 
     * parámetro
     * @param al alumno a borrar
     * @return 
     * true si borra un alumno <br>
     * false si el alumno no existe o no se puede conectar a la BBDD <br>
     */
    public boolean delete(AlumnoPOJO al){        
        return delete(al.getId());
    }
    
    public boolean delete(Integer id_al){
        boolean exito=true;
        try {            
            Conexion conexion = new Conexion();    
            Connection conn = conexion.getConnection();
            String sql = "DELETE FROM ALUMNO WHERE id=?";
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
