/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.controlador;

import com.iesvdc.acceso.modelo.AlumnoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

/**
 *
 * @author juangu
 */
import com.iesvdc.acceso.modelo.AlumnoPOJO;
import com.iesvdc.acceso.modelo.Conexion;
import com.iesvdc.acceso.vista.VistaAlumno;
public class AlumnoCreate extends HttpServlet {

	Connection conn;

    @Override 
    public void init() throws ServletException {
        Conexion conexion = new Conexion(); 
        this.conn = conexion.getConnection();
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
        try (PrintWriter out = response.getWriter()) {
            VistaAlumno va = new VistaAlumno(out);
            va.pintaCabecera("Alta Alumno");
            
            // va.pintaAltaForm();
            va.formulario("Introduzca los datos del nuevo alumno", "AlumnoCreate", true);
            
            va.pintaPie();
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
        
        AlumnoPOJO al = new AlumnoPOJO( 
            request.getParameter("nombre").trim().toUpperCase(),
            request.getParameter("apellido").trim().toUpperCase());
        
        AlumnoDAO al_dao = new AlumnoDAO();
        if (al_dao.create(al)) { // true si exito
            response.sendRedirect("AlumnoRead");
        } else { // false si error
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {                
                VistaAlumno va = new VistaAlumno(out);
                va.pintaCabecera("Alta de alumnos");
                va.error("Error al dar de alta el alumno", "No ha sido posible dar de alta el alumno. Intentelo de nuevo pasados unos minutos. Si el error persiste, contacte con el servicio técnico.");
                va.pintaPie();
            }
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
    }// </editor-fold>

}
