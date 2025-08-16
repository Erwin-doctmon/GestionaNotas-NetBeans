package com.gestionanotas.dao;

import com.gestionanotas.modulo.Usuario;
import com.gestionanotas.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Connection conexion;

    public UsuarioDAO() {
        this.conexion = ConexionBD.getConnection();
        if (conexion == null) {
            System.err.println("❌ Error: conexión a la base de datos no establecida.");
        } else {
            System.out.println("✅ Conexión establecida correctamente.");
        }
    }

    // 🔹 Crear usuario
    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (ID_Usuario, Nombre, Apellido, Contrasena, Rol) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            System.out.println("📥 Registrando usuario:");
            System.out.println("ID: " + usuario.getIdUsuario());
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Apellido: " + usuario.getApellido());
            System.out.println("Rol: " + usuario.getRol());
            System.out.println("Contraseña: " + (usuario.getContrasena() != null ? "***" : null));

            stmt.setLong(1, usuario.getIdUsuario());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getApellido());
            stmt.setString(4, usuario.getContrasena());
            stmt.setString(5, usuario.getRol());

            int filas = stmt.executeUpdate();
            System.out.println("✅ Filas insertadas: " + filas);
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al registrar usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Leer todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getLong("ID_Usuario"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setApellido(rs.getString("Apellido"));
                usuario.setContrasena(rs.getString("Contrasena"));
                usuario.setRol(rs.getString("Rol"));
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener usuarios: " + e.getMessage());
            e.printStackTrace();
        }

        return usuarios;
    }

    // 🔹 Leer usuario por ID
    public Usuario obtenerUsuarioPorId(Long id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE ID_Usuario = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getLong("ID_Usuario"));
                    usuario.setNombre(rs.getString("Nombre"));
                    usuario.setApellido(rs.getString("Apellido"));
                    usuario.setContrasena(rs.getString("Contrasena"));
                    usuario.setRol(rs.getString("Rol"));
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener usuario por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return usuario;
    }

    // 🔹 Actualizar usuario
    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET Nombre = ?, Apellido = ?, Contrasena = ?, Rol = ? WHERE ID_Usuario = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getContrasena());
            stmt.setString(4, usuario.getRol());
            stmt.setLong(5, usuario.getIdUsuario());

            int filas = stmt.executeUpdate();
            System.out.println("🔄 Filas actualizadas: " + filas);
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Eliminar usuario
    public boolean eliminarUsuario(Long idUsuario) {
    String sql = "DELETE FROM usuario WHERE ID_Usuario = ?";
    System.out.println("🔧 DAO: Intentando eliminar usuario con ID = " + idUsuario);

    try {
        if (conexion == null || conexion.isClosed()) {
            System.err.println("⚠️ Conexión a base de datos no disponible.");
            return false;
        }

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setLong(1, idUsuario);
            System.out.println("📤 Ejecutando SQL: " + sql);
            int filas = stmt.executeUpdate();
            System.out.println("🗑️ Filas eliminadas: " + filas);
            return filas > 0;
        }
    } catch (SQLException e) {
        System.err.println("❌ Error al eliminar usuario: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
}