package com.gestionanotas.web;

import com.gestionanotas.dao.AsignaturaDAO;
import com.gestionanotas.dao.CalificacionDAO;
import com.gestionanotas.modulo.Calificacion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/RegistrarCalificacion")
public class RegistrarCalificacionServlet extends HttpServlet {

    public RegistrarCalificacionServlet() {
        super();
        System.out.println("✅ Servlet RegistrarCalificacion CARGADO correctamente.");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("🚨 Entró al método doPost de RegistrarCalificacion");

        try {
            // Leer parámetros crudos
            String idUsuarioStr = request.getParameter("idUsuario");
            String docEstudianteStr = request.getParameter("Documento_Estudiante");
            String notaStr = request.getParameter("nota");

            System.out.println("👉 Parámetros recibidos:");
            System.out.println("   idUsuario: " + idUsuarioStr);
            System.out.println("   Documento_Estudiante: " + docEstudianteStr);
            System.out.println("   Nota: " + notaStr);

            // Validación básica
            if (idUsuarioStr == null || docEstudianteStr == null || notaStr == null) {
                System.out.println("❌ Parámetros nulos.");
                response.sendRedirect("registrarCalificacion.jsp?error=Datos incompletos");
                return;
            }

            long idUsuario = Long.parseLong(idUsuarioStr);
            long documentoEstudiante = Long.parseLong(docEstudianteStr);
            double nota = Double.parseDouble(notaStr);

            // Buscar ID de asignatura
            AsignaturaDAO asignaturaDAO = new AsignaturaDAO();
            int idAsignatura = asignaturaDAO.obtenerIdAsignaturaPorUsuario(idUsuario);

            System.out.println("🔎 ID Asignatura obtenida: " + idAsignatura);

            if (idAsignatura <= 0) {
                response.sendRedirect("registrarCalificacion.jsp?error=Asignatura no encontrada");
                return;
            }

            // Crear calificación y registrar
            Calificacion calificacion = new Calificacion(idAsignatura, documentoEstudiante, nota);
            CalificacionDAO calificacionDAO = new CalificacionDAO();
            boolean exito = calificacionDAO.insertar(calificacion);

            if (exito) {
                System.out.println("✅ Calificación registrada.");
                response.sendRedirect("registrarCalificacion.jsp?mensaje=Registro exitoso");
            } else {
                System.out.println("❌ Registro fallido.");
                response.sendRedirect("registrarCalificacion.jsp?error=NO SE PUDO REGISTRAR LA CALIFICACION");
            }

        } catch (Exception e) {
            e.printStackTrace();

            // Mostrar error directamente en pantalla
            response.setContentType("text/html");
            response.getWriter().println("<h2>❌ ERROR DETECTADO</h2>");
            response.getWriter().println("<pre>" + e.getMessage() + "</pre>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,
                "🚫 Método GET no permitido. Usa el formulario para enviar la calificación.");
    }
}
