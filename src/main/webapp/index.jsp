<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Panel Principal - GestionaNotas</title>
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f6f9;
            display: flex;
        }

        .sidebar {
            width: 250px;
            background-color: #ffcc00;
            color: #333;
            height: 100vh;
            padding: 20px;
            box-shadow: 2px 0 5px rgba(0,0,0,0.1);
            position: fixed;
            transition: width 0.3s ease;
            overflow: hidden;
        }

        .sidebar.collapsed {
            width: 60px;
        }

        .toggle-btn {
            background-color: #333;
            color: white;
            border: none;
            padding: 8px 12px;
            border-radius: 4px;
            cursor: pointer;
            margin-bottom: 20px;
            font-size: 14px;
        }

        .sidebar h2 {
            font-size: 20px;
            margin-bottom: 30px;
            text-align: center;
        }

        .nav-link {
            display: flex;
            align-items: center;
            margin: 12px 0;
            color: #333;
            text-decoration: none;
            font-size: 16px;
            padding: 8px 12px;
            border-radius: 6px;
            transition: background-color 0.2s;
        }

        .nav-link:hover {
            background-color: #ffe066;
        }

        .nav-link.active {
            background-color: #28a745;
            color: white;
        }

        .nav-link span {
            margin-right: 10px;
            font-size: 18px;
        }

        .footer {
            position: absolute;
            bottom: 20px;
            left: 20px;
            right: 20px;
            font-size: 12px;
            color: #555;
            text-align: center;
        }

        .main-content {
            margin-left: 250px;
            padding: 40px;
            flex-grow: 1;
            transition: margin-left 0.3s ease;
        }

        .main-content.expanded {
            margin-left: 60px;
        }

        .grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 20px;
        }

        .card {
            background-color: white;
            border-radius: 10px;
            padding: 25px;
            box-shadow: 0 0 12px rgba(0,0,0,0.08);
            text-align: center;
            transition: transform 0.2s;
            border: 1px solid black;
        }

        .card:hover {
            transform: scale(1.03);
        }

        .icon {
            font-size: 40px;
            margin-bottom: 10px;
        }

        .card-title {
            font-size: 18px;
            margin-bottom: 10px;
            font-weight: bold;
            color: #003366;
        }

        .card-description {
            font-size: 14px;
            margin-bottom: 15px;
            color: #555;
        }

        .card-button {
            background-color: #28a745;
            color: white;
            padding: 8px 16px;
            border: none;
            border-radius: 6px;
            font-size: 14px;
            cursor: pointer;
        }

        .card-button:hover {
            background-color: #218838;
        }
    </style>
    <script>
        function toggleSidebar() {
            const sidebar = document.querySelector('.sidebar');
            const mainContent = document.querySelector('.main-content');
            sidebar.classList.toggle('collapsed');
            mainContent.classList.toggle('expanded');
        }

        function setActive(link) {
            document.querySelectorAll('.nav-link').forEach(el => el.classList.remove('active'));
            link.classList.add('active');
        }
    </script>
</head>
<body>

<div class="sidebar">
    <button class="toggle-btn" onclick="toggleSidebar()">☰</button>
    <h2>GestionaNotas</h2>
    <a class="nav-link" href="registrarUsuario.jsp" onclick="setActive(this)"><span>🧑‍💼</span>Registrar Usuario</a>
    <a class="nav-link" href="listarUsuario.jsp" onclick="setActive(this)"><span>📋</span>Listar Usuarios</a>
    <a class="nav-link" href="registrarEstudiante.jsp" onclick="setActive(this)"><span>🧑‍🎓</span>Registrar Estudiante</a>
    <a class="nav-link" href="registrarAsignatura.jsp" onclick="setActive(this)"><span>📚</span>Registrar Asignatura</a>
    <a class="nav-link" href="registrarCalificacion.jsp" onclick="setActive(this)"><span>📝</span>Registrar Calificación</a>
    <a class="nav-link" href="registrarAsistencia.jsp" onclick="setActive(this)"><span>📆</span>Registrar Asistencia</a>
    <a class="nav-link" href="registrarReporte.jsp" onclick="setActive(this)"><span>📑</span>Informe Académico</a>
    <a class="nav-link" href="CerrarSesion" onclick="setActive(this)"><span>🔒</span>Cerrar Sesión</a>

    <div class="footer">
        © 2025 GestionaNotas<br>
    </div>
</div>

<div class="main-content">
    <h1>¡Bienvenido(a) al Software GestionaNotas!</h1>
    <div class="grid">
        <div class="card">
            <div class="icon">🧑‍💼</div>
            <div class="card-title">Registrar Usuario</div>
            <div class="card-description">Crear cuentas para docentes y administradores</div>
            <button class="card-button" onclick="location.href='registrarUsuario.jsp'">Ingresar</button>
        </div>

        <div class="card">
            <div class="icon">📋</div>
            <div class="card-title">Listar Usuarios</div>
            <div class="card-description">Consultar todos los usuarios registrados</div>
            <button class="card-button" onclick="location.href='listarUsuario.jsp'">Ingresar</button>
        </div>

        <div class="card">
            <div class="icon">🧑‍🎓</div>
            <div class="card-title">Registrar Estudiante</div>
            <div class="card-description">Agregar estudiantes con grupo y acudiente</div>
            <button class="card-button" onclick="location.href='registrarEstudiante.jsp'">Ingresar</button>
        </div>

        <div class="card">
            <div class="icon">📚</div>
            <div class="card-title">Registrar Asignatura</div>
            <div class="card-description">Agregar asignaturas al sistema académico</div>
            <button class="card-button" onclick="location.href='registrarAsignatura.jsp'">Ingresar</button>
        </div>

        <div class="card">
            <div class="icon">📝</div>
            <div class="card-title">Registrar Calificación</div>
            <div class="card-description">Asignar notas a estudiantes por asignatura</div>
            <button class="card-button" onclick="location.href='registrarCalificacion.jsp'">Ingresar</button>
        </div>

        <div class="card">
            <div class="icon">📆</div>
            <div class="card-title">Registrar Asistencia</div>
            <div class="card-description">Marcar asistencia de estudiantes por grupo</div>
            <button class="card-button" onclick="location.href='registrarAsistencia.jsp'">Ingresar</button>
        </div>

        <div class="card">
            <div class="icon">📑</div>
            <div class="card-title">Informe Académico</div>
            <div class="card-description">Crear evaluaciones cualitativas por periodo</div>
            <button class="card-button" onclick="location.href='registrarReporte.jsp'">Ingresar</button>
        </div>

    </div>
</div>

</body>
</html>
