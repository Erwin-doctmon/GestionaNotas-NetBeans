<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.gestionanotas.modulo.Usuario" %>
<%
    Usuario usuario = (Usuario) request.getAttribute("usuario");
    String mensaje = (String) request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
<head>
    <title>🔍 Buscar Usuario</title>
<style>
    body {
        font-family: 'Segoe UI', sans-serif;
        background-color: #fefefe;
        margin: 0;
        padding: 40px;
    }

    .container {
        max-width: 600px;
        margin: auto;

        background: linear-gradient(to bottom, yellow, white, green);
        background-size: 100% 300%; 
        animation: moverColores 2s linear infinite;

        border: 2px solid #000000;
        border-radius: 10px;
        padding: 30px;
        box-shadow: 0 0 15px rgba(0,0,0,0.2);
        text-align: center;
    }

    @keyframes moverColores {
        0% { background-position: 0% 0%; }
        70% { background-position: 0% 70%; }
        0% { background-position: 0% 0%; }
        70% { background-position: 0% 70%; }

    }

    h2 {
        color: #27ae60;
    }

    p {
        font-size: 16px;
        color: #2c3e50;
    }

    .btn {
        margin-top: 20px;
        padding: 12px 20px;
        font-weight: bold;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        background-color: red;
        color: white;
        transition: background-color 0.3s ease;
    }

    .btn:hover {
        background-color: brown;
    }

    input[type="text"] {
        padding: 10px;
        width: 80%;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }
</style>

</head>
<body>
    <div class="container">
        <h2>🔍 Buscar Usuario por ID</h2>
        <form action="BuscarUsuario" method="get">
            <input type="text" name="ID" placeholder="Ingrese el ID del usuario" required />
            <br>
            <button class="btn" type="submit">Buscar</button>
        </form>

        <hr>

        <% if (usuario != null) { %>
            <h2>✅ Usuario encontrado</h2>
            <p><strong>ID:</strong> <%= usuario.getIdUsuario() %></p>
            <p><strong>Nombre:</strong> <%= usuario.getNombre() %></p>
            <p><strong>Apellido:</strong> <%= usuario.getApellido() %></p>
            <p><strong>Rol:</strong> <%= usuario.getRol() %></p>
            <p><strong>Contraseña:</strong> 🔒 Oculta por seguridad</p>
        <% } else if (mensaje != null) { %>
            <h2 style="color: #c0392b;"><%= mensaje %></h2>
        <% } %>

        <form action="${pageContext.request.contextPath}/BuscarUsuario" method="get">
            <a href="listarUsuario.jsp" class="btn">↩️ Volver a la lista</a>
        </form>
    </div>
</body>
</html>
