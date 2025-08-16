<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>🧾 Registrar Asignatura</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f1f3f6;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            padding: 40px 0;
        }

        .form-container {
            background-color: yellow;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
            width: 350px;
            position: relative;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 15px;
        }

        label {
            font-weight: bold;
            display: block;
            margin-top: 15px;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #aaa;
            border-radius: 8px;
        }

        .btn-registrar {
            margin-top: 20px;
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-weight: bold;
            text-transform: uppercase;
        }

        .btn-registrar:hover {
            background-color: green;
            color: black;
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
            background-color: red;
        }

        .mensaje {
            margin-top: 15px;
            text-align: center;
            font-weight: bold;
            font-size: 16px;
            padding: 10px;
            border-radius: 8px;
            animation: fadeOut 5s forwards;
        }

        .mensaje.exito {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }

        .mensaje.error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }

        .mensaje.advertencia {
            background-color: #fff3cd;
            color: #856404;
            border: 1px solid #ffeeba;
        }

        @keyframes fadeOut {
            0% { opacity: 1; }
            80% { opacity: 1; }
            100% { opacity: 0; display: none; }
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>🧾 Registrar Asignatura</h2>

    <form action="RegistrarAsignatura" method="post">
        <!-- 🔹 Campo ID del Docente (corresponde a ID_Usuario en la BD) -->
        <label for="idUsuario">ID del Docente:</label>
        <input type="number" name="idUsuario" id="idUsuario" placeholder="Ej: 107654567" required>

        <!-- 🔹 Campo Nombre de la Asignatura (corresponde a Nombre_Asignatura en la BD) -->
        <label for="Nombre_Asignatura">Nombre de la Asignatura:</label>
        <input type="text" name="Nombre_Asignatura" id="Nombre_Asignatura" placeholder="Ej: Matemáticas" required>

        <button type="submit" class="btn-registrar">REGISTRAR</button>
    </form>

    <button class="volver-btn" onclick="location.href='index.jsp'">Volver al Panel Principal</button>

    <%
        String mensaje = request.getParameter("mensaje");
        String error = request.getParameter("error");
        String advertencia = request.getParameter("advertencia");

        if (mensaje != null) {
    %>
        <p class="mensaje exito">✔️ <%= mensaje %></p>
    <%
        } else if (error != null) {
    %>
        <p class="mensaje error">❌ <%= error %></p>
    <%
        } else if (advertencia != null) {
    %>
        <p class="mensaje advertencia">⚠️ <%= advertencia %></p>
    <%
        }
    %>
</div>

<script>
    setTimeout(() => {
        const mensajes = document.querySelectorAll('.mensaje');
        mensajes.forEach(msg => msg.style.display = 'none');
    }, 5000);
</script>

