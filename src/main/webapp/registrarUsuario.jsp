<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registro de Usuario</title>
    <style> 
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #eef2f7;
            display: flex;
            justify-content: center;
            padding: 40px 0;
            min-height: 100vh;
            overflow-y: auto;
        }

        .form-container {
            background-color: yellow;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            width: 400px;
        }

        h1 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
            font-size: 24px;
        }

        label {
            font-weight: bold;
            margin-top: 10px;
            display: block;
            color: #444;
        }

        input[type="text"],
        input[type="password"],
        select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff8c6;
        }

        select[multiple] {
            height: 100px;
        }

        .btn-registrar {
            background-color: #28a745;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            margin-top: 10px;
        }

        .btn-registrar:hover {
            background-color: #218838;
        }

        .volver-btn {
            background-color: #dc3545;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            margin-top: 10px;
        }

        .volver-btn:hover {
            background-color: #c82333;
        }

        .mensaje {
            margin-top: 10px;
            font-weight: bold;
            text-align: center;
        }

        .mensaje.exito {
            color: green;
        }

        .mensaje.error {
            color: red;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h1>🧑‍💼 Registro de Usuario</h1>

    <!-- Mostrar mensaje de error o éxito -->
    <%
        String error = request.getParameter("error");
        String exito = request.getParameter("exito");

        if (error != null) {
    %>
        <p class="mensaje error"><%= error %></p>
    <%
        } else if ("true".equals(exito)) {
    %>
        <p class="mensaje exito">✅ Usuario registrado correctamente</p>
    <%
        }
    %>

    <!-- Formulario -->
    <form action="RegistrarUsuario" method="post">
        <label for="idUsuario">Número de Documento:</label>
        <input type="text" name="idUsuario" id="idUsuario" required>

        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" id="nombre" required>

        <label for="apellido">Apellido:</label>
        <input type="text" name="apellido" id="apellido" required>

        <label for="rol">Rol:</label>
        <select name="rol" id="rol" required>
            <option value="">Seleccione Rol</option>
            <option value="Docente">Docente</option>
            <option value="Rector">Rector</option>
        </select>

        <label for="contrasena">Contraseña:</label>
        <input type="password" name="contrasena" id="contrasena" required>

        <button type="submit" class="btn-registrar">Registrar Usuario</button>
        <button type="button" class="volver-btn" onclick="location.href='index.jsp'">Volver al Panel Principal</button>
    </form>
</div>

</body>
</html>
