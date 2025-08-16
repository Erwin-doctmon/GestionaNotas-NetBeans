<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cierre de Sesión</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f0f4f8;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .mensaje-container {
            background-color: #fff;
            padding: 30px 40px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            text-align: center;
        }

        h2 {
            color: green;
            margin-bottom: 15px;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: green;
            color: white;
            text-decoration: none;
            border-radius: 6px;
        }

        a:hover {
            background-color: yellow;
        }
    </style>
</head>
<body>
    <div class="mensaje-container">
        <% if ("Sesión-Cerrada/Exitosamente".equals(request.getParameter("cerrado"))) { %>
            <h2>✅ La sesión se ha cerrado correctamente.</h2>
        <% } else { %>
            <h2>⚠️ No se pudo cerrar la sesión.</h2>
        <% } %>
        <a href="index.jsp">Volver al inicio</a>
    </div>
</body>
</html>

