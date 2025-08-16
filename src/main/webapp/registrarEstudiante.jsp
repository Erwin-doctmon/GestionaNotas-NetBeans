<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Estudiante</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #fffbea;
            padding: 50px;
        }

        form {
            max-width: 600px;
            margin: auto;
            background-color: yellow;
            padding: 35px;
            border-radius: 12px;
            box-shadow: 0 0 15px rgba(0, 128, 0, 0.2);
        }

        h2 {
            text-align: center;
            color: #cc0000;
            margin-bottom: 25px;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
            color: #006400;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-top: 6px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 14px;
            background-color: #fefcea;
        }

        .boton-grupo {
            text-align: center;
            margin-top: 30px;
        }

        .boton-grupo button {
            margin: 0 10px;
            padding: 12px 20px;
            font-size: 15px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
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
            background-color: #b02a37;
        }

        .mensaje {
            text-align: center;
            margin-top: 20px;
            font-weight: bold;
        }

        .exito {
            color: #28a745;
        }

        .error {
            color: #cc0000;
        }
    </style>
</head>
<body>

<form action="RegistrarEstudiante" method="post">
    <h2>Registrar Estudiante</h2>

    <label>Nombres y Apellidos:</label>
    <input type="text" name="nombre" required />

    <label>Documento:</label>
    <input type="text" name="documentoEstudiante" required />

    <label>Fecha de Nacimiento:</label>
    <input type="date" name="fechaNacimiento" required />

    <label>Grupo:</label>
    <input type="text" name="grupo" required />

    <label>Acudiente:</label>
    <input type="text" name="acudiente" required />

    <label>Número Telefónico del Acudiente:</label>
    <input type="text" name="telefono" maxlength="15" />

    <div class="boton-grupo">
        <button type="submit" class="btn">Guardar Estudiante</button>
        <button type="button" class="volver-btn" onclick="location.href='index.jsp'">Volver al inicio</button>
    </div>

    <% if ("true".equals(request.getParameter("exito"))) { %>
        <div class="mensaje exito">✅ Estudiante registrado correctamente</div>
    <% } else if ("1".equals(request.getParameter("error"))) { %>
        <div class="mensaje error">❌ Error al registrar estudiante</div>
    <% } %>
</form>

</body>
</html>
