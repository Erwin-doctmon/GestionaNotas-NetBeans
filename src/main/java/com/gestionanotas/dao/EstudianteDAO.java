package com.gestionanotas.dao;

import com.gestionanotas.modulo.Estudiante;
import com.gestionanotas.util.ConexionBD;

import java.sql.*;

public class EstudianteDAO {

    private final String URL = "jdbc:mysql://localhost:3306/gestionanotas";
    private final String USUARIO = "root";
    private final String CLAVE = "";

    // Método para conectar
    private Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, CLAVE);
        } catch (Exception e) {
            System.err.println("❌ Error de conexión: " + e.getMessage());
            return null;
        }
    }

    // Método para insertar estudiante
    public boolean insertarEstudiante(Estudiante e) {
        String sql = "INSERT INTO estudiante (Nombres_Apellidos, Documento_Estudiante, FechaNacimiento, Grupo, Acudiente, Número_telefónico) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getDocumentoEstudiante());
            ps.setString(3, e.getFechaNacimiento());
            ps.setString(4, e.getGrupo());
            ps.setString(5, e.getAcudiente());
            ps.setString(6, e.getTelefono());

            int filas = ps.executeUpdate();
            System.out.println("✅ Estudiante registrado. Filas afectadas: " + filas);
            return filas > 0;

        } catch (SQLException ex) {
            System.err.println("❌ Error SQL al insertar estudiante: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    // Método para buscar estudiante por documento
    public Estudiante buscarPorDocumentoEstudiante(String documento) {
        String sql = "SELECT * FROM estudiante WHERE Documento_Estudiante = ?";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, documento);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Estudiante e = new Estudiante();
                    e.setID_Estudiante(rs.getLong("ID_Estudiante"));
                    e.setNombre(rs.getString("Nombres_Apellidos"));
                    e.setDocumentoEstudiante(rs.getString("Documento_Estudiante"));
                    e.setFechaNacimiento(rs.getString("FechaNacimiento"));
                    e.setGrupo(rs.getString("Grupo"));
                    e.setAcudiente(rs.getString("Acudiente"));
                    e.setTelefono(rs.getString("Número_telefónico"));
                    return e;
                }
            }

        } catch (SQLException ex) {
            System.err.println("❌ Error SQL al buscar estudiante: " + ex.getMessage());
            ex.printStackTrace();
        }

        return null;
    }

    // Método para buscar estudiante por ID
    public Estudiante buscarPorId(int idEstudiante) {
        String sql = "SELECT * FROM estudiante WHERE ID_Estudiante = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEstudiante);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setID_Estudiante(rs.getLong("ID_Estudiante"));
                estudiante.setNombre(rs.getString("Nombres_Apellidos"));
                estudiante.setDocumentoEstudiante(rs.getString("Documento_Estudiante"));
                estudiante.setFechaNacimiento(rs.getString("FechaNacimiento"));
                estudiante.setGrupo(rs.getString("Grupo"));
                estudiante.setAcudiente(rs.getString("Acudiente"));
                estudiante.setTelefono(rs.getString("Número_telefónico"));
                return estudiante;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error SQL al buscar estudiante por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
