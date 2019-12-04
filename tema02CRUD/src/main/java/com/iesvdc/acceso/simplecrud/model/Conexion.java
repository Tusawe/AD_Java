package com.iesvdc.acceso.simplecrud.model;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class Conexion {

    Connection conn;
    Context ctx;
    DataSource ds;

    public Conexion() {
        try {
            if (ctx == null)
                ctx = new InitialContext();
            if (ds == null)
                ds = (DataSource) ((Context) ctx.lookup("java:comp/env")).lookup("jdbc/biblioteca");
            conn = ds.getConnection();
        } catch (Exception ex) {
            System.out.println("## Conexion ERROR ## " + ex.getLocalizedMessage());
            ctx = null;
            ds = null;
            conn = null;
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void destroy() {
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (SQLException e) {
                
            }
        }
    }
}
