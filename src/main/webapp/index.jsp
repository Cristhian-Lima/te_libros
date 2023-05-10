<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : listaLibros
    Created on : 9 may. de 2023, 20:22:33
    Author     : Cristhian
--%>
<%@page import="com.te_libros.modelo.Libro"%>
<%@page import="java.util.ArrayList"%>
<%
  ArrayList<Libro> libros = (ArrayList<Libro>) request.getAttribute("libros");
  for (Libro l : libros) {
    System.out.println(l.getTitulo());
  }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <h1>Lista de libros</h1>
    <p><a href="MainControler?op=nuevo">Nuevo</a></p>

    <table border="2">
      <thead>
        <tr>
          <th>ID</th>
          <th>ISBN</th>
          <th>Titulo</th>
          <th>Categoria</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
      <c:forEach var="item" items="${libros}">
        <tr>
          <td>${item.id}</td>
          <td>${item.isbn}</td>
          <td>${item.titulo}</td>
          <td>${item.categoria}</td>
          <td><a href="MainControler?op=eliminar&id=${item.id}" onclick="return(confirm('¿Esta seguro?'))">Eliminar</a></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</body>
</html>
