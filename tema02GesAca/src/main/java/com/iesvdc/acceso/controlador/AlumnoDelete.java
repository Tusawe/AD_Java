/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.controlador;

import com.iesvdc.acceso.modelo.AlumnoDAO;
import com.iesvdc.acceso.modelo.AlumnoPOJO;
import com.iesvdc.acceso.modelo.Conexion;
import com.iesvdc.acceso.vista.VistaAlumno;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author juangu
 */
public class AlumnoDelete extends HttpServlet {

    // Connection conn;

    @Override 
    public void init() throws ServletException {
        // Conexion conexion = new Conexion(); 
        // this.conn = conexion.getConnection();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");        
       
        
        try {
            PrintWriter out = response.getWriter();
            Integer id = Integer.parseInt(request.getParameter("id"));
            
            VistaAlumno va = new VistaAlumno(out);
            va.pintaCabecera("Borrado de Alumno");
            
            if (id!=null) {
                AlumnoDAO al_dao = new AlumnoDAO();
                AlumnoPOJO al = al_dao.findById(id);
                if (al!=null) {
                // formulario (obj alumno, destino post, enabled) 
                    va.formulario(al,"¿Realmente quiere borrar este alumno?","AlumnoDelete", false);
                } else {
                    va.error("Borrando Alumno", "No puedo encontrar ese ID de alumno");
                }
            } else {
                va.error("Borrando Alumno", "No puedo encontrar el alumno");
            }
            va.pintaPie();
        } catch (NumberFormatException ne) {
            response.sendRedirect("AlumnoRead");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");        
       
        
        try {
            PrintWriter out = response.getWriter();
            Integer id = Integer.parseInt(request.getParameter("id"));
            
            VistaAlumno va = new VistaAlumno(out);
            
            va.pintaCabecera("Borrar alumno");
            
            AlumnoDAO al_dao = new AlumnoDAO();
            if (al_dao.delete(id)){
                response.sendRedirect("Alumno");
            } else {
                va.error("Borrando alumno", "Ha sido imposible borrar el alumno de la base de datos");
            }
            
            va.pintaPie();
            
        } catch (IOException | NumberFormatException ex) {
            // REGISTRAR INCIDENTE (posible inyección de código)
            response.sendRedirect("AlumnoRead");
        }
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public void destroy(){
        // try {
        //     if (conn!=null) conn.close();
        // } catch (SQLException sqle) {

        // }

    }
}
