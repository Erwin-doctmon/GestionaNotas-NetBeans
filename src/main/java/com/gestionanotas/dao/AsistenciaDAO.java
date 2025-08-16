package com.gestionanotas.dao;

import com.gestionanotas.modulo.Asistencia;
import com.gestionanotas.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AsistenciaDAO {

    private ConexionBD conexionBD;

    public AsistenciaDAO() {
        this.conexionBD = new ConexionBD();
    }

    // 💾 Insertar asistencia en la BD (incluye Estado_Asistencia)
    public boolean insertarAsistencia(Asistencia asistencia) {
        String sql = "INSERT INTO asistencia (ID_Asignatura, Documento_Estudiante, Fecha, Estado_Asistencia) VALUES (?, ?, ?, ?)";

        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, asistencia.getIdAsignatura());
            stmt.setLong(2, asistencia.getDocumentoEstudiante());
            stmt.setDate(3, java.sql.Date.valueOf(asistencia.getFecha()));
            stmt.setString(4, asistencia.getEstadoAsistencia());

            int filas = stmt.executeUpdate();

            System.out.println("✅ ASISTENCIA INSERTADA. Filas afectadas: " + filas);
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("❌ ERROR SQL al insertar asistencia: " + e.getMessage());
            System.err.println("Datos ➤ Asignatura=" + asistencia.getIdAsignatura() +
                               ", Documento=" + asistencia.getDocumentoEstudiante() +
                               ", Fecha=" + asistencia.getFecha() +
                               ", Estado=" + asistencia.getEstadoAsistencia());
            e.printStackTrace(System.out);
            return false;
        }
    }
}
