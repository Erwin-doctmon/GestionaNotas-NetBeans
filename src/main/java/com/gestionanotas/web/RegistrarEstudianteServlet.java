package com.gestionanotas.web;

import com.gestionanotas.dao.EstudianteDAO;
import com.gestionanotas.modulo.Estudiante;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/RegistrarEstudiante")
public class RegistrarEstudianteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 🧾 Obtener datos del formulario
        String nombre = request.getParameter("nombre");
        String documentoEstudiante = request.getParameter("documentoEstudiante");
        String fechaNacimiento = request.getParameter("fechaNacimiento");
        String grupo = request.getParameter("grupo");
        String acudiente = request.getParameter("acudiente");
        String telefono = request.getParameter("telefono");

        // 🧱 Construir objeto estudiante
        Estudiante estudiante = new Estudiante(
            0, // ID autogenerado
            nombre,
            documentoEstudiante,
            fechaNacimiento,
            grupo,
            acudiente,
            telefono
        );

        // 💾 Insertar en BD
        EstudianteDAO dao = new EstudianteDAO();
        boolean resultado = dao.insertarEstudiante(estudiante);

        // 🚦 Redirigir según resultado
        if (resultado) {
            response.sendRedirect("registrarEstudiante.jsp?exito=true");
        } else {
            response.sendRedirect("registrarEstudiante.jsp?error=1");
        }
    }
}
