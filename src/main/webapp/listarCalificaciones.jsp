<%@ page import="com.gestionanotas.dao.calificacionDAO" %>
<%@ page import="com.gestionanotas.modulo.Calificacion" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    calificacionDAO dao = new calificacionDAO();
    List<Calificacion> lista = dao.listarCalificaciones();
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Calificaciones</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #004080;
            color: white;
            text-align: center;
            padding: 20px 0;
        }

        .container {
            padding: 30px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 0 8px rgba(0,0,0,0.1);
        }

        th {
            background-color: #0066cc;
            color: white;
            padding: 12px;
            text-align: center;
        }

        td {
            padding: 10px;
            text-align: center;
            border-bottom: 1px solid #ccc;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .volver-btn {
            background-color: #0066cc;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
            display: inline-block;
        }

        .volver-btn:hover {
            background-color: #004a99;
        }
    </style>
</head>
<body>
    <header>
        <h1>Listado de Calificaciones Registradas</h1>
    </header>

    <div class="container">
        <table>
            <thead>
                <tr>
                    <th>ID Asignatura</th>
                    <th>ID Estudiante</th>
                    <th>Nota</th>
                </tr>
            </thead>
            <tbody>
                <% for (Calificacion c : lista) { %>
                    <tr>
                        <td><%= c.getIdAsignatura() %></td>
                        <td><%= c.getDocumentoEstudiante() %></td>
                        <td><%= c.getNota() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <a class="volver-btn" href="index.jsp">⬅ Volver al Panel Principal</a>
    </div>
</body>
</html>
