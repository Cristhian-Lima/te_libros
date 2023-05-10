/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.te_libros.controlador;

import com.te_libros.modelo.Libro;
import com.te_libros.utiles.ConexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cristhian
 */
@WebServlet(name = "MainControler", urlPatterns = {"/MainControler"})
public class MainControler extends HttpServlet {

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
    String op = ((request.getParameter("op") != null) ? request.getParameter("op")
            : "list");

    ArrayList<Libro> libros = new ArrayList<>();
    ConexionDB canal = new ConexionDB();
    Connection conn = canal.conectar();
    PreparedStatement ps;
    ResultSet rs;

    if (op.equals("list")) {
      try {
        // TODO: listar

        String sql = "SELECT * FROM libros";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
          Libro libro = new Libro();
          libro.setId(rs.getInt("id"));
          libro.setIsbn(rs.getString("isbn"));
          libro.setTitulo(rs.getString("titulo"));
          libro.setCategoria(rs.getString("categoria"));

          libros.add(libro);
        }

        request.setAttribute("libros", libros);
        request.getRequestDispatcher("index.jsp").forward(request, response);

      } catch (SQLException ex) {
        Logger.getLogger(MainControler.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    if (op.equals("nuevo")) {
      // TODO: adicionar libro
      Libro libro = new Libro();

      request.setAttribute("libro", libro);
      request.getRequestDispatcher("nuevo.jsp").forward(request, response);
    }

    if (op.equals("eliminar")) {
      try {
        // TODO: eliminar
        int id = Integer.parseInt(request.getParameter("id"));

        String sql = "DELETE FROM libros WHERE id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        ps.executeUpdate();
        response.sendRedirect("MainControler");
      } catch (SQLException ex) {
        Logger.getLogger(MainControler.class.getName()).log(Level.SEVERE, null, ex);
      }
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
    try {
      ConexionDB canal = new ConexionDB();
      Connection conn = canal.conectar();
      PreparedStatement ps;
      ResultSet rs;

      Libro libro = new Libro();

      int id = Integer.parseInt(request.getParameter("id"));
      libro.setId(id);
      libro.setIsbn(request.getParameter("isbn"));
      libro.setTitulo(request.getParameter("titulo"));
      libro.setCategoria(request.getParameter("categoria"));

      if (id == 0) {
        String sql = "INSERT INTO libros(isbn, titulo, categoria) VALUES (?, ?, ?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, libro.getIsbn());
        ps.setString(2, libro.getTitulo());
        ps.setString(3, libro.getCategoria());

        ps.executeUpdate();
      }
      /*else {
        String sql = "INSERT INTO libros VALUES (?, ?, ?, ?)";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, libro.getId());
        ps.setString(2, libro.getIsbn());
        ps.setString(3, libro.getTitulo());
        ps.setString(4, libro.getCategoria());

        ps.executeUpdate();
      }*/

    } catch (SQLException ex) {
      Logger.getLogger(MainControler.class.getName()).log(Level.SEVERE, null, ex);
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
