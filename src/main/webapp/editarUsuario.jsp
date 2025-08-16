<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.gestionanotas.modulo.Usuario" %>
<%
    Usuario usuario = (Usuario) request.getAttribute("usuario");
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
    <title>✏️ Editar Usuario</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f4f4;
            padding: 40px;
        }

        
        .form-container {
            max-width: 600px;
            margin: auto;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0,0,0,0.2);
            position: relative;
            z-index: 1;
            background: linear-gradient(to bottom, yellow, green);
            background-size: 100% 400%;
            animation: verticalScroll 2s ease infinite;
        }

        @keyframes verticalScroll {
            0% { background-position: 0% 0%; }
            70% { background-position: 0% 70%; }
            0% { background-position: 0% 0%; }
        }

        h2 {
            text-align: center;
            color: #000000;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
            color: #34495e;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .btn-container {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }

        .btn {
            width: 48%;
            padding: 12px;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
        }

        .guardar { background-color: #27ae60; color: white; }
        .cancelar { background-color: #c0392b; color: white; }

        .mensaje-error {
            color: #e74c3c;
            font-weight: bold;
            text-align: center;
            margin-top: 15px;
        }

        small {
            display: block;
            margin-top: 5px;
            color: #7f8c8d;
        }
        
        .mensaje-exito {
        color: green;
        background: #e6ffe6;
        padding: 10px;
        border-radius: 5px;
        margin-top: 15px;
    }
    .mensaje-error {
        color: red;
        background: #ffe6e6;
        padding: 10px;
        border-radius: 5px;
        margin-top: 15px;
    }
    </style>
</head>
<body>
<div class="form-container">
    <h2>✏️ Editar Usuario</h2>

    <% if (usuario != null) { %>
        <form action="ActualizarUsuario" method="post">
            <input type="hidden" name="idUsuario" value="<%= usuario.getIdUsuario() %>">

            <label>Nombre:</label>
            <input type="text" name="nombre" value="<%= usuario.getNombre() %>" required>

            <label>Apellido:</label>
            <input type="text" name="apellido" value="<%= usuario.getApellido() %>" required>

            <label>Rol:</label>
            <input type="text" name="rol" value="<%= usuario.getRol() %>" required>

            <label>Contraseña:</label>
            <input type="password" name="contrasena" placeholder="Nueva contraseña (opcional)">
            <small>Si no se ingresa una nueva, se conservará la actual 🔒</small>

            <div class="btn-container">
                <button type="submit" class="btn guardar">✅ Actualizar</button>
                <a href="listarUsuario.jsp" class="btn cancelar">❌ Cancelar</a>
            </div>
        </form>

        <!-- Mensaje de confirmación o error después de actualizar -->
        <% if (request.getAttribute("mensaje") != null) { %>
            <p class="mensaje-exito">✅ <%= request.getAttribute("mensaje") %></p>
        <% } else if (request.getAttribute("error") != null) { %>
            <p class="mensaje-error">❌ <%= request.getAttribute("error") %></p>
        <% } %>

    <% } else { %>
        <p class="mensaje-error">❌ Error: No se pudo cargar la información del usuario.</p>
        <div style="text-align: center; margin-top: 20px;">
            <a href="listarUsuario.jsp" class="btn cancelar">↩️ Volver</a>
        </div>
    <% } %>
</div>

</div>
</body>
</html>
