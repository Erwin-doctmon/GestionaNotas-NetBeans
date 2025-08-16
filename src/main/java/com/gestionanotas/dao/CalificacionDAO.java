package com.gestionanotas.dao;

import com.gestionanotas.modulo.Calificacion;
import com.gestionanotas.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CalificacionDAO {

    private ConexionBD conexionBD;

    public CalificacionDAO() {
        this.conexionBD = new ConexionBD();
    }

    public boolean insertar(Calificacion calificacion) {
        String sql = "INSERT INTO calificacion (ID_Asignatura, Documento_Estudiante, Nota) VALUES (?, ?, ?)";

        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, calificacion.getIdAsignatura());
            stmt.setLong(2, calificacion.getDocumentoEstudiante());
            stmt.setDouble(3, calificacion.getNota());

            int filasInsertadas = stmt.executeUpdate();
            System.out.println("✅ Calificación insertada. Filas afectadas: " + filasInsertadas);
            return filasInsertadas > 0;

        } catch (SQLException e) {
            String mensaje = "SQL ERROR ➤ " + e.getMessage() +
                    " | Datos: Asignatura=" + calificacion.getIdAsignatura() +
                    ", Documento=" + calificacion.getDocumentoEstudiante() +
                    ", Nota=" + calificacion.getNota();
            System.err.println("❌ " + mensaje);
            e.printStackTrace();
            throw new RuntimeException(mensaje);  // ← Propaga el error al servlet
        }
    }

    public List<Calificacion> documentoEstudiante(String documento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
