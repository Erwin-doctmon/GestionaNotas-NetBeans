package com.gestionanotas.web;

import com.gestionanotas.util.ConexionBD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String nombre = request.getParameter("nombre");
        String contrasena = request.getParameter("contrasena");

        if (nombre == null || contrasena == null || nombre.trim().isEmpty() || contrasena.trim().isEmpty()) {
            response.sendRedirect("index.jsp?error=campos_vacios");
            return;
        }

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM usuarios WHERE nombre = ? AND contrasena = ?")
        ) {
            stmt.setString(1, nombre.trim());
            stmt.setString(2, contrasena.trim());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // ✅ Obtener datos del usuario //
                    long idUsuario = rs.getLong("idUsuario"); 
                    String rol = rs.getString("rol");
                    String apellido = rs.getString("apellido");

                    // ✅ Guardar sesión //
                    HttpSession session = request.getSession();
                    session.setAttribute("idUsuario", idUsuario);
                    session.setAttribute("nombre", nombre);
                    session.setAttribute("apellido", apellido);
                    session.setAttribute("rol", rol);

                    // ✅ Redirigir según el rol //
                    if ("Rector".equalsIgnoreCase(rol)) {
                        response.sendRedirect("menuRector.jsp");
                    } else if ("Docente".equalsIgnoreCase(rol)) {
                        response.sendRedirect("menuDocente.jsp");
                    } else {
                        response.sendRedirect("menu.jsp");
                    }
                } else {
                    response.sendRedirect("index.jsp?error=credenciales");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?error=bd");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?error=general");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }
}
