package com.gestionanotas.dao;

import com.gestionanotas.modulo.Usuario;
import java.sql.*;

public class BuscarUsuarioDAO {

    private Connection conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/gestionanotas";  // Ajusta según tu BD
        String user = "root";  // Cambia si tu BD tiene otra credencial
        String pass = "";      // Cambia si tu BD tiene contraseña
        return DriverManager.getConnection(url, user, pass);
    }

    public Usuario obtenerPorId(Long idUsuario) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                usuario.setIdUsuario(rs.getLong("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
