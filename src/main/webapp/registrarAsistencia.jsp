<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>📅 Registro de Asistencia</title>
    <style>
        body {
            background-color: #f9f9f9;
            font-family: 'Segoe UI', sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: yellow;
            border: 2px solid #000;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 0 10px rgba(0,0,0,0.2);
            text-align: center;
            width: 400px;
        }
        h2 {
            color: #000;
        }
        input[type="text"],
        select {
            padding: 10px;
            width: 90%;
            margin: 10px 0;
            border: 1px solid #000;
            border-radius: 5px;
            font-size: 14px;
        }
        button {
            padding: 10px 20px;
            margin: 10px 5px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            color: #fff;
        }
        .btn-guardar {
            background-color: #28a745;
        }
        .btn-volver {
            background-color: #dc3545; 
        }
        .mensaje {
            margin-top: 10px;
            font-weight: bold;
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
    <div class="container">
        <h2>🕐 Registrar Asistencia</h2>
        <form action="RegistrarAsistencia" method="post">
            <label for="idUsuario">🎓 Documento Docente:</label><br>
            <input type="text" id="idUsuario" name="idUsuario" required><br>

            <label for="Documento_Estudiante">📅 Documento Estudiante:</label><br>
            <input type="text" id="Documento_Estudiante" name="Documento_Estudiante" required><br>

            <label for="estadoAsistencia">📌 Estado de Asistencia:</label><br>
            <select id="estadoAsistencia" name="estadoAsistencia" required>
                <option value="">-- Seleccione --</option>
                <option value="Presente">Presente</option>
                <option value="Ausente">Ausente</option>
                <option value="No vino">No vino</option>
            </select><br>

            <button type="submit" class="btn-guardar">Guardar Asistencia ✅</button>
            <a href="index.jsp"><button type="button" class="btn-volver">Volver al Inicio ⬅️</button></a>
        </form>

        <div class="mensaje">
            <% 
                String mensaje = request.getParameter("mensaje");
                String error = request.getParameter("error");
                if (mensaje != null) {
            %>
                <p class="mensaje exito"><%= mensaje %></p>
            <% } else if (error != null) { %>
                <p class="mensaje error"><%= error %></p>
            <% } %>
        </div>
    </div>
</body>
</html>
