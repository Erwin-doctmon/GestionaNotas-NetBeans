package com.gestionanotas.web;

import com.gestionanotas.dao.AsistenciaDAO;
import com.gestionanotas.dao.AsignaturaDAO;
import com.gestionanotas.modulo.Asistencia;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/RegistrarAsistencia")
public class RegistrarAsistenciaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 📥 Obtener datos del formulario
            String documentoStr = request.getParameter("Documento_Estudiante");
            String idUsuarioStr = request.getParameter("idUsuario");
            String estadoAsistencia = request.getParameter("estadoAsistencia");

            System.out.println("📥 DATOS RECIBIDOS DEL FORMULARIO:");
            System.out.println("Documento_Estudiante = " + documentoStr);
            System.out.println("idUsuario = " + idUsuarioStr);
            System.out.println("Estado_Asistencia = " + estadoAsistencia);

            // ✅ Validar datos
            if (documentoStr == null || idUsuarioStr == null || estadoAsistencia == null ||
                documentoStr.isEmpty() || idUsuarioStr.isEmpty() || estadoAsistencia.isEmpty()) {
                System.out.println("❌ DATOS INCOMPLETOS - Redirigiendo al formulario...");
                response.sendRedirect("registrarAsistencia.jsp?error=Datos incompletos");
                return;
            }

            long documentoEstudiante = Long.parseLong(documentoStr);
            long idUsuario = Long.parseLong(idUsuarioStr);

            // Obtener ID de asignatura correspondiente al docente
            AsignaturaDAO asignaturaDAO = new AsignaturaDAO();
            int idAsignatura = asignaturaDAO.obtenerIdAsignaturaPorUsuario(idUsuario);

            System.out.println("🎯 ID Asignatura obtenido: " + idAsignatura);

            if (idAsignatura <= 0) {
                System.out.println("❌ No se encontró asignatura para el docente.");
                response.sendRedirect("registrarAsistencia.jsp?error=Asignatura no encontrada para el docente");
                return;
            }

            // 📝 Crear objeto Asistencia con asignatura correcta
            Asistencia asistencia = new Asistencia(idAsignatura, documentoEstudiante, LocalDate.now(), estadoAsistencia);
            asistencia.setEstadoAsistencia(estadoAsistencia); // 🆕 Asignar estado


            // 💾 Insertar en BD
            AsistenciaDAO asistenciaDAO = new AsistenciaDAO();
            boolean exito = asistenciaDAO.insertarAsistencia(asistencia);

            if (exito) {
                System.out.println("✅ ASISTENCIA REGISTRADA CORRECTAMENTE");
                response.sendRedirect("registrarAsistencia.jsp?mensaje=Asistencia registrada exitosamente");
            } else {
                System.out.println("❌ NO SE PUDO REGISTRAR LA ASISTENCIA");
                response.sendRedirect("registrarAsistencia.jsp?error=No se pudo registrar la asistencia");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("❌ ERROR DETECTADO: " + e.getMessage());
            String mensajeError = java.net.URLEncoder.encode("Error: " + e.getMessage(), "UTF-8");
            response.sendRedirect("registrarAsistencia.jsp?error=" + mensajeError);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,
                "🚫 Método GET no permitido. Usa el formulario para registrar asistencia.");
    }
}
