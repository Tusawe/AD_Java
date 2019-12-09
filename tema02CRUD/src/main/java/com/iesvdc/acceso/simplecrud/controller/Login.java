package com.iesvdc.acceso.simplecrud.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iesvdc.acceso.simplecrud.model.Conexion;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Conexion conn = new Conexion();
		try {
			Connection conexion = conn.getConnection();

			// get request parameters for userID and password
			String user = request.getParameter("username");
			String pwd = request.getParameter("pwd");

			String sql = "SELECT * FROM usuario WHERE username=? AND password=?";
			PreparedStatement pstm = conexion.prepareStatement(sql);
			pstm.setString(1, user);
			pstm.setString(2, pwd);
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				// setting session to expiry in 30 mins
				session.setMaxInactiveInterval(30 * 60);
				Cookie userName = new Cookie("ges_res.user", user);
				userName.setMaxAge(30 * 60);
				response.addCookie(userName);
				response.sendRedirect("index.jsp");
			} else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
				PrintWriter out = response.getWriter();
				// out.println("<font color=red>Either user name or password is wrong."+"</font>");
				rd.include(request, response);
			}
		
			conexion.close();
		} catch (SQLException e) {
			// response.sendRedirect("login.jsp");
			response.getWriter().print(e.getLocalizedMessage());
		}
	}

}

// mvn clean && mvn compile && mvn package && mvn tomcat7:run