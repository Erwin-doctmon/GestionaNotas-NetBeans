package com.gestionanotas.web;

import com.gestionanotas.dao.UsuarioDAO;
import com.gestionanotas.modulo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/ActualizarUsuario")
public class ActualizarUsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String rol = request.getParameter("rol");
            String contrasena = request.getParameter("contrasena");

            // Validación de campos vacíos
            if (nombre == null || nombre.trim().isEmpty() ||
                apellido == null || apellido.trim().isEmpty() ||
                rol == null || rol.trim().isEmpty()) {

                Usuario usuario = new Usuario(idUsuario, nombre, apellido, rol, contrasena);
                request.setAttribute("error", "❌ Todos los campos son obligatorios.");
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("editarUsuario.jsp").forward(request, response);
                return;
            }

            Usuario usuarioExistente = usuarioDAO.obtenerUsuarioPorId(idUsuario);
            if (usuarioExistente == null) {
                request.setAttribute("error", "❌ Usuario no encontrado.");
                request.getRequestDispatcher("editarUsuario.jsp").forward(request, response);
                return;
            }

            // Si no se ingresa nueva contraseña, mantener la actual
            if (contrasena == null || contrasena.trim().isEmpty()) {
                contrasena = usuarioExistente.getContrasena();
            }

            Usuario usuario = new Usuario(idUsuario, nombre, apellido, rol, contrasena);
            boolean actualizado = usuarioDAO.actualizarUsuario(usuario);

            if (actualizado) {
                request.setAttribute("mensaje", "✅ Usuario actualizado correctamente.");
            } else {
                request.setAttribute("error", "❌ No se pudo actualizar el usuario.");
            }

            // Volvemos a mostrar el formulario con el mensaje y datos actualizados
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("editarUsuario.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(Long.parseLong(request.getParameter("idUsuario")));
            usuario.setNombre(request.getParameter("nombre"));
            usuario.setApellido(request.getParameter("apellido"));
            usuario.setRol(request.getParameter("rol"));
            usuario.setContrasena(request.getParameter("contrasena"));

            request.setAttribute("usuario", usuario);
            request.setAttribute("error", "❌ Error al procesar la actualización.");
            request.getRequestDispatcher("editarUsuario.jsp").forward(request, response);
        }
    }
}

