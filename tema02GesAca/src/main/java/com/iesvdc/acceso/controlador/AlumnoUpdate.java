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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author juangu
 */
public class AlumnoUpdate extends HttpServlet {

    // Connection conn;

    // @Override 
    // public void init() throws ServletException {
    //     Conexion conexion = new Conexion(); 
    //     this.conn = conexion.getConnection();
    // }
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
        try{
            PrintWriter out = response.getWriter();
            Integer id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            
            VistaAlumno va = new VistaAlumno(out);
            va.pintaCabecera("Actualización de alumno");
            AlumnoPOJO al = new AlumnoPOJO(id, nombre, apellido);
            va.formulario(al,"Actualice los datos del alumno","AlumnoUpdate",true);
            va.pintaPie();
        } catch (Exception ex) {
            
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
        try{
            PrintWriter out = response.getWriter();
            Integer id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            
            AlumnoPOJO al = new AlumnoPOJO(id, nombre, apellido); 
            AlumnoDAO al_dao = new AlumnoDAO();
            al_dao.update(id, al);
            response.sendRedirect("AlumnoRead");
        } catch (Exception ex) {
            
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

    // public void destroy(){
    //     try {
    //         if (conn!=null) conn.close();
    //     } catch (SQLException sqle) {

    //     }

    // }

}
