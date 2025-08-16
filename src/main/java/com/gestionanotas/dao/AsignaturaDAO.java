package com.gestionanotas.dao;

import com.gestionanotas.modulo.Asignatura;
import com.gestionanotas.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsignaturaDAO {

    private ConexionBD conexionBD;

    public AsignaturaDAO() {
        this.conexionBD = new ConexionBD();
    }

    // ✅ Insertar asignatura vinculada a un usuario/docente
    public boolean insertarAsignatura(Asignatura asignatura) {
        String sql = "INSERT INTO asignatura (Nombre_Asignatura, ID_Usuario) VALUES (?, ?)";

        try (Connection conn = conexionBD.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, asignatura.getNombre());
            stmt.setLong(2, asignatura.getIdUsuario());

            int filas = stmt.executeUpdate();
            System.out.println("✅ Filas insertadas en asignatura: " + filas);
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar asignatura: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Obtener asignaturas de un docente específico (por ID_Usuario)
    public List<Asignatura> obtenerAsignaturasPorDocente(long idDocente) {
        List<Asignatura> lista = new ArrayList<>();
        String sql = "SELECT * FROM asignatura WHERE ID_Usuario = ?";

        try (Connection conn = conexionBD.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, idDocente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Asignatura asignatura = new Asignatura();
                asignatura.setIdAsignatura(rs.getInt("ID_Asignatura"));
                asignatura.setNombre(rs.getString("Nombre_Asignatura"));
                asignatura.setIdUsuario(rs.getLong("ID_Usuario"));
                lista.add(asignatura);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener asignaturas por docente: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    // ✅ Obtener todas las asignaturas existentes
    public List<Asignatura> obtenerTodasAsignaturas() {
        List<Asignatura> lista = new ArrayList<>();
        String sql = "SELECT * FROM asignatura";

        try (Connection conn = conexionBD.getConnection(); 
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Asignatura asignatura = new Asignatura();
                asignatura.setIdAsignatura(rs.getInt("ID_Asignatura"));
                asignatura.setNombre(rs.getString("Nombre_Asignatura"));
                asignatura.setIdUsuario(rs.getLong("ID_Usuario"));
                lista.add(asignatura);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar asignaturas: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    // ✅ Buscar asignatura por ID
    public Asignatura buscarPorId(int idAsignatura) {
        String sql = "SELECT * FROM asignatura WHERE ID_Asignatura = ?";

        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idAsignatura);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Asignatura asignatura = new Asignatura();
                asignatura.setIdAsignatura(rs.getInt("ID_Asignatura"));
                asignatura.setNombre(rs.getString("Nombre_Asignatura"));
                asignatura.setIdUsuario(rs.getLong("ID_Usuario"));
                return asignatura;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al buscar asignatura por ID: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }

    // ✅ Buscar asignatura por nombre y docente (idUsuario)
    public Asignatura buscarPorNombreYDocente(String nombreAsignatura, long idUsuario) {
        String sql = "SELECT * FROM asignatura WHERE Nombre_Asignatura = ? AND ID_Usuario = ?";

        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreAsignatura);
            stmt.setLong(2, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Asignatura asignatura = new Asignatura();
                asignatura.setIdAsignatura(rs.getInt("ID_Asignatura"));
                asignatura.setNombre(rs.getString("Nombre_Asignatura"));
                asignatura.setIdUsuario(rs.getLong("ID_Usuario"));
                return asignatura;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al buscar asignatura por nombre y docente: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    // ✅ NUEVO: Obtener solo el ID de la asignatura de un docente
    public int obtenerIdAsignaturaPorUsuario(long idUsuario) {
        int idAsignatura = -1;
        String sql = "SELECT ID_Asignatura FROM asignatura WHERE ID_Usuario = ? LIMIT 1";

        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                idAsignatura = rs.getInt("ID_Asignatura");
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener ID de asignatura por docente: " + e.getMessage());
            e.printStackTrace();
        }

        return idAsignatura;
    }
}
