package com.gestionanotas.web;

import com.gestionanotas.dao.UsuarioDAO;
import com.gestionanotas.dao.AsignaturaDAO;
import com.gestionanotas.modulo.Usuario;
import com.gestionanotas.modulo.Asignatura;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/RegistrarUsuario")
public class RegistrarUsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO;
    private AsignaturaDAO asignaturaDAO;

    @Override
    public void init() throws ServletException {
        usuarioDAO = new UsuarioDAO();
        asignaturaDAO = new AsignaturaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String idUsuarioStr = request.getParameter("idUsuario");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String rol = request.getParameter("rol");
            String contrasena = request.getParameter("contrasena");

            long idUsuario;
            try {
                idUsuario = Long.parseLong(idUsuarioStr);
            } catch (NumberFormatException e) {
                response.sendRedirect("registrarUsuario.jsp?error=ID%20inválido");
                return;
            }

            // Verificar si ya existe el usuario
            List<Usuario> usuario = usuarioDAO.obtenerTodosLosUsuarios();
            for (Usuario u : usuario) {
                if (u.getIdUsuario() == idUsuario) {
                    response.sendRedirect("registrarUsuario.jsp?error=Usuario%20ya%20existe");
                    return;
                }
            }

            // Crear y guardar el nuevo usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setIdUsuario(idUsuario);
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setApellido(apellido);
            nuevoUsuario.setRol(rol);
            nuevoUsuario.setContrasena(contrasena);

            boolean exitoUsuario = usuarioDAO.registrarUsuario(nuevoUsuario);

            if (!exitoUsuario) {
                response.sendRedirect("registrarUsuario.jsp?error=Error%20al%20registrar%20usuario");
                return;
            }

            // Si deseas usar asignaturas, asegúrate de tener el campo en el JSP
            /*
            String[] asignaturasSeleccionadas = request.getParameterValues("asignaturas");
            if (asignaturasSeleccionadas != null && asignaturasSeleccionadas.length > 0) {
                for (String nombreAsignatura : asignaturasSeleccionadas) {
                    Asignatura asignatura = new Asignatura();
                    asignatura.setNombre(nombreAsignatura);
                    asignatura.setIdUsuario(idUsuario);

                    boolean exitoAsignatura = asignaturaDAO.insertarAsignatura(asignatura);
                    if (!exitoAsignatura) {
                        response.sendRedirect("registrarUsuario.jsp?error=Error%20en%20asignatura");
                        return;
                    }
                }
            }
            */

            response.sendRedirect("registrarUsuario.jsp?exito=true");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("registrarUsuario.jsp?error=Error%20en%20el%20servidor");
        }
    }

    @Override
    public void destroy() {
        usuarioDAO = null;
        asignaturaDAO = null;
    }
}
