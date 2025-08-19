package com.gestionanotas.web;

import com.gestionanotas.dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EliminarUsuario")
public class EliminarUsuarioServlet extends HttpServlet {

    private UsuarioDAO dao = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Long idUsuario = Long.parseLong(request.getParameter("idUsuario")); 
            boolean eliminado = dao.eliminarUsuario(idUsuario);

            if (eliminado) {
                request.setAttribute("mensaje", "✅ Usuario eliminado correctamente.");
            } else {
                request.setAttribute("mensaje", "⚠️ No se pudo eliminar el usuario.");
            }

        } catch (NumberFormatException e) {
            request.setAttribute("mensaje", "⚠️ ID de usuario inválido.");
        } catch (Exception e) {
            request.setAttribute("mensaje", "❌ Error al eliminar usuario: " + e.getMessage());
        }

        // Redirige de nuevo al JSP de listado
        request.getRequestDispatcher("listarUsuario.jsp").forward(request, response);
    }
}
