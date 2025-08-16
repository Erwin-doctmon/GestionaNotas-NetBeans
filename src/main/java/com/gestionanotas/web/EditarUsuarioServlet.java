package com.gestionanotas.web;

import com.gestionanotas.dao.UsuarioDAO;
import com.gestionanotas.modulo.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/EditarUsuario")
public class EditarUsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("idUsuario");
        
        if (idParam == null || idParam.trim().isEmpty() || !idParam.matches("\\d+")) {
            response.sendRedirect("listarUsuario.jsp?error=ID inválido");
            return;
        }
        
        Long idUsuario = Long.parseLong(idParam);
        Usuario usuario = usuarioDAO.obtenerUsuarioPorId(idUsuario);
        
        if (usuario != null) {
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("editarUsuario.jsp").forward(request, response);
        } else {
            response.sendRedirect("listarUsuario.jsp?error=Usuario no encontrado");
        }
    }
}