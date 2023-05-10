/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.te_libros.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Cristhian
 */
public class ConexionDB {

  static String driver = "com.mysql.jdbc.Driver";
  static String url = "jdbc:mysql://localhost:3306/bd_biblio";
  static String usuario = "root";
  static String password = "";

  private Connection conn = null;

  public ConexionDB() {
    try {
      Class.forName(driver);
      conn = DriverManager.getConnection(url, usuario, password);
      if (conn != null) {
        System.out.println("[OK] conexion con la base de datos");
      }
    } catch (ClassNotFoundException e) {
      System.out.println("Error en driver: " + e.getMessage());
    } catch (SQLException e) {
      System.out.println("Error en SQL: " + e.getMessage());
    }
  }

  public Connection conectar() {
    return this.conn;
  }

  public void desconectar() {
    try {
      conn.close();
    } catch (SQLException e) {
      System.out.println("Error en SQL: " + e.getMessage());
    }
  }
}
