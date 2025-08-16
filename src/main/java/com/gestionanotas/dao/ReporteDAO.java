package com.gestionanotas.dao;

import com.gestionanotas.modulo.Reporte;
import com.gestionanotas.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAO {

    private Connection conexionBD;

    public ReporteDAO() {
        conexionBD = ConexionBD.getConnection();
    }

    // Registrar reporte y retornar el ID generado
    public long registrarReporte(Reporte reporte) {
        String sql = "INSERT INTO reporte (ID_Estudiante, contenido, nota, asignatura, anio, periodo, desempenosAcademicos, fecha) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
        try (PreparedStatement stmt = conexionBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, reporte.getIdEstudiante());
            stmt.setString(2, reporte.getContenido());
            stmt.setBigDecimal(3, reporte.getNota());
            stmt.setString(4, reporte.getAsignatura());
            stmt.setInt(5, reporte.getAnio());
            stmt.setInt(6, reporte.getPeriodo());
            stmt.setString(7, reporte.getDesempenosAcademicos());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getLong(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Buscar reporte por ID
    public Reporte buscarPorId(long idReporte) {
        String sql = "SELECT * FROM reporte WHERE IdReporte = ?";
        try (PreparedStatement stmt = conexionBD.prepareStatement(sql)) {
            stmt.setLong(1, idReporte);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Reporte reporte = new Reporte();
                    reporte.setIdReporte(rs.getInt("IdReporte"));
                    reporte.setIdEstudiante(rs.getLong("ID_Estudiante"));
                    reporte.setContenido(rs.getString("contenido"));
                    reporte.setNota(rs.getBigDecimal("nota"));
                    reporte.setAsignatura(rs.getString("asignatura"));
                    reporte.setFecha(rs.getTimestamp("fecha"));
                    reporte.setAnio(rs.getInt("anio"));
                    reporte.setPeriodo(rs.getInt("periodo"));
                    reporte.setDesempenosAcademicos(rs.getString("desempenosAcademicos"));
                    return reporte;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Listar todos los reportes
    public List<Reporte> listarReportes() {
        List<Reporte> lista = new ArrayList<>();
        String sql = "SELECT * FROM reporte";
        try (Statement stmt = conexionBD.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Reporte reporte = new Reporte();
                reporte.setIdReporte(rs.getInt("IdReporte"));
                reporte.setIdEstudiante(rs.getLong("ID_Estudiante"));
                reporte.setContenido(rs.getString("contenido"));
                reporte.setNota(rs.getBigDecimal("nota"));
                reporte.setAsignatura(rs.getString("asignatura"));
                reporte.setFecha(rs.getTimestamp("fecha"));
                reporte.setAnio(rs.getInt("anio"));
                reporte.setPeriodo(rs.getInt("periodo"));
                reporte.setDesempenosAcademicos(rs.getString("desempenosAcademicos"));
                lista.add(reporte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Actualizar reporte
    public boolean actualizarReporte(Reporte reporte) {
        String sql = "UPDATE reporte SET ID_Estudiante = ?, contenido = ?, nota = ?, asignatura = ?, anio = ?, periodo = ?, desempenosAcademicos = ? WHERE idReporte = ?";
        try (PreparedStatement stmt = conexionBD.prepareStatement(sql)) {
            stmt.setLong(1, reporte.getIdEstudiante());
            stmt.setString(2, reporte.getContenido());
            stmt.setBigDecimal(3, reporte.getNota());
            stmt.setString(4, reporte.getAsignatura());
            stmt.setInt(5, reporte.getAnio());
            stmt.setInt(6, reporte.getPeriodo());
            stmt.setString(7, reporte.getDesempenosAcademicos());
            stmt.setInt(8, reporte.getIdReporte());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar reporte
    public boolean eliminarReporte(int idReporte) {
        String sql = "DELETE FROM reporte WHERE IdReporte = ?";
        try (PreparedStatement stmt = conexionBD.prepareStatement(sql)) {
            stmt.setInt(1, idReporte);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
