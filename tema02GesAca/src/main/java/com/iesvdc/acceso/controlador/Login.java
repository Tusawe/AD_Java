package com.iesvdc.acceso.controlador;
 
import java.io.IOException;
// import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
 
/**
 * Servlet implementation class LoginServlet
 */
 
@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String userID = "admin";
    private final String password = "1234";
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        // get request parameters for userID and password
        String usuario = request.getParameter("usuario");
        String pwd = request.getParameter("password");
 
        if (userID.equals(usuario) && password.equals(pwd)) {
            Cookie usuarioCookies = new Cookie("gesacauser", usuario);
            // setting cookie to expiry in 60 mins
            usuarioCookies.setMaxAge(60 * 60);
            response.addCookie(usuarioCookies);
            response.sendRedirect("index.jsp");
        } else {
            //RequestDispatcher rd = getServletContext().getRequestDispatcher("/Alumno");
            //rd.include(request, response);
            response.sendRedirect("login.jsp");
        }
 
    }
}

