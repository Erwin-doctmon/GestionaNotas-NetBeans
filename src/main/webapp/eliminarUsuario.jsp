<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.gestionanotas.dao.UsuarioDAO" %>
<%@ page import="com.gestionanotas.modulo.Usuario" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String idParam = request.getParameter("idUsuario");
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        // 🔹 Procesar eliminación directamente en este JSP
        try {
            Long id = Long.parseLong(idParam);
            UsuarioDAO dao = new UsuarioDAO();
            boolean eliminado = dao.eliminarUsuario(id);

            if (eliminado) {
                response.sendRedirect("listarUsuario.jsp?mensaje=✅ Usuario eliminado correctamente");
                return; // importante: detener ejecución del JSP
            } else {
                response.sendRedirect("listarUsuario.jsp?mensaje=⚠️ No se pudo eliminar el usuario");
                return;
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("listarUsuario.jsp?mensaje=⚠️ ID inválido");
            return;
        }
    } else if (idParam != null && request.getAttribute("usuario") == null) {
        // 🔹 Mostrar confirmación antes de eliminar
        try {
            Long id = Long.parseLong(idParam);
            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuario = dao.obtenerUsuarioPorId(id);
            request.setAttribute("usuario", usuario);
        } catch (NumberFormatException e) {
            request.setAttribute("mensaje", "⚠️ ID inválido.");
        }
    }
%>

<html>
<head>
    <title>Eliminar Usuario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #fff9c4;
            padding: 20px;
        }
        .card {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            max-width: 400px;
            margin: auto;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            text-align: center;
        }
        .btn-eliminar {
            background-color: #d9534f;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
        }
        .btn-eliminar:hover {
            background-color: #c9302c;
        }
        .mensaje {
            font-size: 1.2em;
            margin-top: 20px;
            text-align: center;
            color: #555;
        }
    </style>
</head>
<body>
<div class="card">
    <c:choose>
        <c:when test="${empty mensaje}">
            <c:if test="${not empty usuario}">
                <h2>🗑️ ¿Deseas eliminar al usuario <strong>${usuario.nombre}</strong>?</h2>
                <form id="formEliminar" action="eliminarUsuario.jsp" method="post" onsubmit="return confirmarEliminacion();">
                    <input type="hidden" name="idUsuario" value="${usuario.idUsuario}" />
                    <button type="submit" class="btn-eliminar">Eliminar</button>
                </form>
            </c:if>
            <c:if test="${empty usuario}">
                <div class="mensaje">⚠️ No se encontró el usuario.</div>
            </c:if>
        </c:when>
        <c:otherwise>
            <div class="mensaje">${mensaje}</div>
        </c:otherwise>
    </c:choose>
</div>

<script>
function confirmarEliminacion() {
    return confirm("¿Estás seguro de que deseas eliminar este usuario?");
}
</script>

</body>
</html>
