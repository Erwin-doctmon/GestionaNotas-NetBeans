<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Calificación</title>
    <style>
        /* MISMO DISEÑO QUE TENÍAS */
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #ffffff;
            color: #000000;
            padding: 40px;
            margin: 0;
        }
        form {
            max-width: 600px;
            margin: auto;
            background-color: yellow;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 12px rgba(0,0,0,0.1);
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
            color: #333333;
        }
        input {
            width: 100%;
            padding: 10px;
            margin-top: 6px;
            border: 1px solid #cccccc;
            border-radius: 6px;
            box-sizing: border-box;
        }
        .boton-grupo {
            display: flex;
            justify-content: space-between;
            gap: 10px;
            margin-top: 25px;
        }
        .btn, .volver-btn {
            flex: 1;
            padding: 12px;
            font-size: 16px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            text-align: center;
        }
        .btn {
            background-color: #28a745;
            color: white;
        }
        .btn:hover {
            background-color: #218838;
        }
        .volver-btn {
            background-color: #dc3545;
            color: white;
        }
        .volver-btn:hover {
            background-color: #c82333;
        }
        .mensaje {
            text-align: center;
            margin-top: 20px;
            padding: 10px;
            border-radius: 6px;
            font-weight: bold;
        }
        .mensaje.exito {
            color: #155724;
            background-color: #d4edda;
            border: 1px solid #c3e6cb;
        }
        .mensaje.error {
            color: #721c24;
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
        }
    </style>
</head>
<body>

<form action="RegistrarCalificacion" method="post">
    <h2>📘 Registrar Calificación</h2>

    <!-- ID del Docente -->
    <label for="ID_Usuario">ID del Docente:</label>
    <input type="number" name="idUsuario" id="ID_Usuario" placeholder="Ej: 1076543210" required />

    <!-- Documento del Estudiante -->
    <label for="Documento_Estudiante">Documento del Estudiante:</label>
    <input type="number" name="Documento_Estudiante" id="Documento_Estudiante" placeholder="Ej: 1023456789" required />

    <!-- Nota -->
    <label for="nota">Nota:</label>
    <input type="number" step="0.1" min="0" max="5" name="nota" id="nota" placeholder="Ej: 4.5" required />

    <div class="boton-grupo">
        <button type="submit" class="btn">Guardar Calificación</button>
        <button type="button" class="volver-btn" onclick="location.href='http://localhost:8080/mavenproject1/index.jsp'">Volver al inicio</button>

    </div>

    <% 
        String mensaje = request.getParameter("mensaje");
        String error = request.getParameter("error");

        if (mensaje != null) {
    %>
        <div class="mensaje exito">✅ <%= mensaje %></div>
    <% 
        } else if (error != null) { 
    %>
        <div class="mensaje error">❌ <%= error %></div>
    <% 
        } 
    %>
</form>

</body>
</html>
