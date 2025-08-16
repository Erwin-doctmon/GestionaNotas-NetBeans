<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Reporte Académico</title>
    <link rel="stylesheet" href="../resources/css/style_reporte.css">
    <style>
body {
    font-family: 'Segoe UI', Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 20px;
}

.contenedor-reporte {
    max-width: 700px;
    margin: auto;
    background-color: #fff;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0,0,0,0.1);
}

.titulo {
    text-align: center;
    color: #222;
    font-size: 24px;
    margin-bottom: 25px;
}

.formulario-reporte label {
    display: block;
    margin-top: 15px;
    font-weight: bold;
    color: #333;
}

.campo, .campo-desempenos {
    width: 100%;
    padding: 10px;
    margin-top: 5px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 14px;
    transition: border-color 0.3s;
}

.campo:focus, .campo-desempenos:focus {
    border-color: #4CAF50;
    outline: none;
}

.campo-desempenos {
    height: 120px;
    resize: vertical;
}

.amarillo { background-color: #fff8c6; }
.verde    { background-color: #d4f4d4; }
.rojo     { background-color: #f8d4d4; }
.blanco   { background-color: #ffffff; }
.azul     { background-color: #e0ecff; color: #000; }

.botones {
    margin-top: 25px;
    display: flex;
    justify-content: space-between;
    flex-wrap: wrap;
    gap: 10px;
}

.btn {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    text-decoration: none;
    font-weight: bold;
    cursor: pointer;
    transition: background-color 0.3s;
}

.btn.verde {
    background-color: #4CAF50;
    color: white;
}

.btn.verde:hover {
    background-color: #45a049;
}

.btn.rojo {
    background-color: #f44336;
    color: white;
}

.btn.rojo:hover {
    background-color: #d32f2f;
}

.mensaje {
    margin-top: 20px;
    padding: 12px;
    background-color: #e7f3fe;
    border-left: 5px solid #2196F3;
    color: #333;
    font-weight: bold;
    border-radius: 5px;
}

</style>
</head>
<body>
<div class="contenedor-reporte">
    <h1 class="titulo">📋 Registro de Reporte Académico</h1>

    <form action="RegistrarReporte" method="post" class="formulario-reporte">
        <label>Nombre del estudiante:</label>
        <input type="text" name="Nombres_Apellidos" required class="campo amarillo">

        <label>Documento:</label>
        <input type="text" name="documentoEstudiante" class="campo blanco">

        <label>Grupo:</label>
        <input type="text" name="grupo" required class="campo verde">

        <label>Acudiente:</label>
        <input type="text" name="acudiente" required class="campo rojo">
        
         <%
             java.util.Calendar cal = java.util.Calendar.getInstance();
             int mesActual = cal.get(java.util.Calendar.MONTH) + 1;
    int anioAcademico = (mesActual >= 2) ? cal.get(java.util.Calendar.YEAR) : cal.get(java.util.Calendar.YEAR) - 1;
         %>

        <label>Año:</label>
        <input type="text" name="anio" value="<%= anioAcademico %>" readonly class="campo blanco">
        
        <label>Período:</label>
        <select name="periodo" class="campo azul">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
        </select>

        <label>Asignatura:</label>
        <input type="text" name="asignatura" required class="campo azul">

        <label>Nota:</label>
        <input type="number" name="nota" step="0.01" min="0" max="5" required class="campo verde">

        <label>Desempeños Académicos:</label>
        <textarea name="desempenosAcademicos" class="campo-desempenos blanco" required></textarea>

        <div class="botones">
            <button type="submit" class="btn verde">Guardar Reporte</button>
            <a href="index.jsp" class="btn rojo">Volver al inicio</a>
        </div>
    <%-- Mensaje de confirmación --%>
    <%
        String mensaje = request.getParameter("mensaje");
        if (mensaje != null) {
    %>
        <div class="mensaje">
            <%= mensaje %>
        </div>
    <%
        }
    %>

</div>
</body>
</html>
