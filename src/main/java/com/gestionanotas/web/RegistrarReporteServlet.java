package com.gestionanotas.web;

import com.gestionanotas.dao.ReporteDAO;
import com.gestionanotas.dao.EstudianteDAO;
import com.gestionanotas.modulo.Reporte;
import com.gestionanotas.modulo.Estudiante;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/RegistrarReporte")
public class RegistrarReporteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 🔍 Depuración: mostrar todos los parámetros recibidos
        System.out.println("Parámetros recibidos en el request:");
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = params.nextElement();
            String paramValue = request.getParameter(paramName);

            if (paramValue == null || paramValue.trim().isEmpty()) {
                System.out.println("⚠️ Parámetro vacío o nulo: '" + paramName + "'");
            } else {
                System.out.println("✅ " + paramName + " = '" + paramValue + "'");
            }
        }

        try {
            // 1. Recoger datos del formulario
            String nombre = request.getParameter("Nombres_Apellidos");
            String documentoEstudiante = request.getParameter("documentoEstudiante");
            String grupo = request.getParameter("grupo");
            String acudiente = request.getParameter("acudiente");
            String desempenos = request.getParameter("desempenosAcademicos");
            String periodoStr = request.getParameter("periodo");
            String anioStr = request.getParameter("anio");
            String asignatura = request.getParameter("asignatura");
            String notaStr = request.getParameter("nota");

            // 2. Validación básica
            if (asignatura == null || asignatura.trim().isEmpty() || notaStr == null || notaStr.trim().isEmpty()) {
                String error = "❌ Asignatura y nota son obligatorias.";
                System.out.println("[Error] " + error);
                response.sendRedirect("registrarReporte.jsp?mensaje=" + URLEncoder.encode(error, "UTF-8"));
                return;
            }

            BigDecimal nota;
            try {
                nota = new BigDecimal(notaStr.trim());
            } catch (NumberFormatException ex) {
                String error = "❌ La nota ingresada no es válida.";
                System.out.println("[Error] " + error);
                response.sendRedirect("registrarReporte.jsp?mensaje=" + URLEncoder.encode(error, "UTF-8"));
                return;
            }

            int anioAcademico = 0;
            int periodoAcademico = 0;

            try {
                anioAcademico = Integer.parseInt(anioStr.trim());
                periodoAcademico = Integer.parseInt(periodoStr.trim());
            } catch (NumberFormatException ex) {
                String error = "❌ Año o período inválido.";
                System.out.println("[Error] " + error);
                response.sendRedirect("registrarReporte.jsp?mensaje=" + URLEncoder.encode(error, "UTF-8"));
                return;
            }

            if (anioAcademico < 2020 || anioAcademico > 2030) {
                String error = "❌ El año académico debe estar entre 2020 y 2030.";
                System.out.println("[Error] " + error);
                response.sendRedirect("registrarReporte.jsp?mensaje=" + URLEncoder.encode(error, "UTF-8"));
                return;
            }

            // 3. Buscar estudiante por documento
            EstudianteDAO estudianteDAO = new EstudianteDAO();
            Estudiante estudiante = estudianteDAO.buscarPorDocumentoEstudiante(documentoEstudiante);

            if (estudiante == null) {
                String error = "❌ Estudiante no encontrado con documento: " + documentoEstudiante;
                System.out.println("[Error] " + error);
                response.sendRedirect("registrarReporte.jsp?mensaje=" + URLEncoder.encode(error, "UTF-8"));
                return;
            }

            long idEstudiante = estudiante.getID_Estudiante();
            System.out.println("👤 Estudiante encontrado: " + estudiante);

            // 4. Crear objeto Reporte
            Reporte reporte = new Reporte();
            reporte.setIdEstudiante(idEstudiante);
            reporte.setContenido(desempenos);
            reporte.setAsignatura(asignatura);
            reporte.setNota(nota);
            reporte.setFecha(new Date());
            reporte.setAnio(anioAcademico);
            reporte.setPeriodo(periodoAcademico);
            reporte.setDesempenosAcademicos(desempenos);

            System.out.println("📝 Reporte a guardar: " + reporte);

            // 5. Guardar en la base de datos y obtener ID
            ReporteDAO reporteDAO = new ReporteDAO();
            long IdReporte = reporteDAO.registrarReporte(reporte); // ← debe retornar el ID generado

            // 6. Redirigir a la plantilla si se guardó correctamente
            if (IdReporte > 0) {
                System.out.println("✅ Reporte guardado con ID: " + IdReporte);
                response.sendRedirect("plantillaReporte?Id=" + IdReporte);
            } else {
                String error = "❌ No se pudo guardar el reporte.";
                System.out.println("[Error] " + error);
                response.sendRedirect("registrarReporte.jsp?mensaje=" + URLEncoder.encode(error, "UTF-8"));
            }

        } catch (Exception e) {
            System.out.println("💥 [Excepción] Error inesperado al procesar el reporte:");
            e.printStackTrace();
            response.sendRedirect("registrarReporte.jsp?mensaje=" + URLEncoder.encode("❌ Error al procesar el reporte.", "UTF-8"));
        }
    }
}