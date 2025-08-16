package com.gestionanotas.web;

import com.gestionanotas.dao.AsignaturaDAO;
import com.gestionanotas.dao.UsuarioDAO;
import com.gestionanotas.modulo.Asignatura;
import com.gestionanotas.modulo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/RegistrarAsignatura")
public class RegistrarAsignaturaServlet extends HttpServlet {

    private AsignaturaDAO asignaturaDAO;
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        asignaturaDAO = new AsignaturaDAO();
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recuperar datos del formulario con nombres que coinciden con el JSP
        String idUsuarioStr = request.getParameter("idUsuario");  // ID del docente
        String nombreAsignatura = request.getParameter("Nombre_Asignatura");  // Nombre de la asignatura

        // Validar si los datos están vacíos
        if (idUsuarioStr == null || idUsuarioStr.trim().isEmpty() ||
            nombreAsignatura == null || nombreAsignatura.trim().isEmpty()) {
            response.sendRedirect("registrarAsignatura.jsp?advertencia=Datos%20incompletos");
            return;
        }

        try {
            long idUsuario = Long.parseLong(idUsuarioStr.trim());
            nombreAsignatura = nombreAsignatura.trim();

            // Verificar si el docente existe en la BD
            Usuario usuario = usuarioDAO.obtenerUsuarioPorId(idUsuario);
            if (usuario == null) {
                response.sendRedirect("registrarAsignatura.jsp?error=El%20ID%20de%20docente%20no%20existe");
                return;
            }

            // Crear objeto Asignatura con los datos recibidos
            Asignatura asignatura = new Asignatura();
            asignatura.setNombre(nombreAsignatura);  // Usa el setter correcto
            asignatura.setIdUsuario(idUsuario);

            // Insertar asignatura en la BD
            boolean exito = asignaturaDAO.insertarAsignatura(asignatura);

            if (exito) {
                response.sendRedirect("registrarAsignatura.jsp?mensaje=Asignatura%20registrada%20correctamente");
            } else {
                response.sendRedirect("registrarAsignatura.jsp?error=No%20se%20pudo%20registrar");
            }

        } catch (NumberFormatException e) {
            response.sendRedirect("registrarAsignatura.jsp?error=ID%20inv%C3%A1lido");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("registrarAsignatura.jsp?error=Error%20del%20servidor");
        }
    }

    @Override
    public void destroy() {
        asignaturaDAO = null;
        usuarioDAO = null;
    }
}
