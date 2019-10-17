package com.iesvirgendelcarmen.acceso;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCreate extends HttpServlet{
    
    @Override
    public void init() throws ServletException {
        
    }

    @Override
    public void destroy() {
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String username = req.getParameter("usarname");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        resp.getWriter().println("He leido estos parametros<br>");
        resp.getWriter().println("username="+username+"<br>");
        resp.getWriter().println("password="+password+"<br>");
        resp.getWriter().println("email="+email+"<br>");

    }

}