<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gestionanotas.modulo.Usuario" %>
<%@ page import="com.gestionanotas.dao.UsuarioDAO" %>

<%
    UsuarioDAO dao = new UsuarioDAO();
    List<Usuario> usuarios = dao.obtenerTodosLosUsuarios();
    String mensaje = request.getParameter("mensaje");
%>

<!DOCTYPE html>
<html>
<head>
    <title>📋 Gestión de Usuarios</title>
    <link rel="stylesheet" href="estilos.css">
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f6f9;
            margin: 0;
            padding: 20px;
        }

        h1 {
            color: #2c3e50;
            text-align: center;
        }

        .mensaje-exito {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
            padding: 15px;
            margin: 20px auto;
            border-radius: 5px;
            text-align: center;
            font-weight: bold;
            width: 90%;
        }

        table {
            width: 90%;
            margin: auto;
            border-collapse: collapse;
            background-color: #ffffff;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #34495e;
            color: white;
        }

        tr:hover {
            background-color: #ecf0f1;
        }

        .btn {
            padding: 6px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
        }

        .editar {
            background-color: #2980b9;
            color: white;
        }

        .eliminar {
            background-color: #c0392b;
            color: white;
        }

        .buscar {
            background-color: #27ae60;
            color: white;
        }

        .acciones {
            display: flex;
            gap: 8px;
            justify-content: center;
        }

        .contenedor-volver {
            text-align: center;
            margin-top: 20px;
            margin-bottom: 40px;
        }

        .volver {
            background-color: #c0392b;
            color: white;
        }

        .volver:hover {
            background-color: #922b21;
        }
    </style>
</head>
<body>
    
    <c:if test="${not empty param.mensaje}">
    <div style="background-color:#d4edda; padding:10px; border-radius:5px; color:#155724; margin-bottom:15px;">
        ${param.mensaje}
    </div>
</c:if>

    <h1>📋 Gestión de Usuarios</h1>

    <% if (mensaje != null && !mensaje.trim().isEmpty()) { %>
        <div class="mensaje-exito"><%= mensaje %></div>
    <% } %>

    <table>
        <tr>
            <th>ID</th>
            <th>👤 Nombre</th>
            <th>👥 Apellido</th>
            <th>🎓 Rol</th>
            <th>🔒 Contraseña</th>
            <th>⚙️ Acciones</th>
        </tr>

        <% for (Usuario u : usuarios) { %>
            <tr>
                <td><%= u.getIdUsuario() %></td>
                <td><%= u.getNombre() %></td>
                <td><%= u.getApellido() %></td>
                <td><%= u.getRol() %></td>
                <td><%= (u.getContrasena() != null && !u.getContrasena().isEmpty()) ? "🔒 Oculta por seguridad" : "🔒 No registrada" %></td>
                <td class="acciones">
                    <form action="EditarUsuario" method="get" style="display:inline;">
                        <input type="hidden" name="idUsuario" value="<%= u.getIdUsuario() %>">
                        <button class="btn editar">✏️ Editar</button>
                    </form>

                    <form action="EliminarUsuario" method="post" style="display:inline;">
                        <input type="hidden" name="idUsuario" value="<%= u.getIdUsuario() %>">
                        <button class="btn eliminar" type="submit" onclick="return confirm('¿Estás seguro de eliminar este usuario?')">🗑️ Eliminar</button>
                    </form>

                    <form action="BuscarUsuario" method="get" style="display:inline;">
                        <input type="hidden" name="idUsuario" value="<%= u.getIdUsuario() %>">
                        <button class="btn buscar">🔍 Buscar</button>
                    </form>
                </td>
            </tr>
        <% } %>
    </table>

    <!-- Botón Volver al inicio fuera de la tabla -->
    <div class="contenedor-volver">
        <form action="index.jsp" method="get">
            <button class="btn volver">⬅️ Volver al inicio</button>
        </form>
    </div>
</body>
</html>
