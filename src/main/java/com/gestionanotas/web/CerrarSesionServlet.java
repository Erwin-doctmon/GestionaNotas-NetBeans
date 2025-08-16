package com.gestionanotas.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CerrarSesion")
public class CerrarSesionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 🧹 Invalida la sesión actual
        request.getSession().invalidate();

        // 🔁 Redirige al mensaje de logout
        response.sendRedirect("index.jsp?cerra=true");
    }
}
