package com.gestionanotas.web;

import com.gestionanotas.dao.UsuarioDAO;
import com.gestionanotas.modulo.Usuario;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/BuscarUsuario")
public class BuscarUsuarioServlet extends HttpServlet {

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String idParam = request.getParameter("ID");

    if (idParam == null || idParam.isEmpty()) {
        request.setAttribute("mensaje", "⚠ Debes ingresar un ID válido.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("buscarUsuario.jsp");
        dispatcher.forward(request, response);
        return;
    }

    try {
        Long id = Long.valueOf(idParam);
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.obtenerUsuarioPorId(id);

        if (usuario != null) {
            request.setAttribute("usuario", usuario);
        } else {
            request.setAttribute("mensaje", "❌ Usuario no encontrado.");
        }
    } catch (NumberFormatException e) {
        request.setAttribute("mensaje", "❌ El ID debe ser numérico.");
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher("buscarUsuario.jsp");
    dispatcher.forward(request, response);
}
}