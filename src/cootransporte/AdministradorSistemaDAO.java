package cootransporte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministradorSistemaDAO {

    public int insertar(AdministradorSistema a) throws SQLException {
        String sql = "INSERT INTO administradorsistema (nombre, identificacion, correo, contrasena, id_rol) VALUES (?,?,?,?,?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getIdentificacion());
            ps.setString(3, a.getCorreo());
            ps.setString(4, a.getContrasena());
            ps.setInt(5, a.getIdRol());

            int filas = ps.executeUpdate();
            if (filas == 0) return 0;

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    a.setIdAdmin(id);
                    return id;
                }
            }
            return 0;
        }
    }

    public AdministradorSistema obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM administradorsistema WHERE idadmin=?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    AdministradorSistema a = new AdministradorSistema();
                    a.setIdAdmin(rs.getInt("idadmin"));
                    a.setNombre(rs.getString("nombre"));
                    a.setIdentificacion(rs.getString("identificacion"));
                    a.setCorreo(rs.getString("correo"));
                    a.setContrasena(rs.getString("contrasena"));
                    a.setIdRol(rs.getInt("id_rol"));
                    return a;
                }
            }
        }
        return null;
    }

    public List<AdministradorSistema> obtenerTodos() throws SQLException {
        List<AdministradorSistema> lista = new ArrayList<>();
        String sql = "SELECT * FROM administradorsistema";
        try (Connection conn = ConexionBD.obtenerConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                AdministradorSistema a = new AdministradorSistema();
                a.setIdAdmin(rs.getInt("idadmin"));
                a.setNombre(rs.getString("nombre"));
                a.setIdentificacion(rs.getString("identificacion"));
                a.setCorreo(rs.getString("correo"));
                a.setContrasena(rs.getString("contrasena"));
                a.setIdRol(rs.getInt("id_rol"));
                lista.add(a);
            }
        }
        return lista;
    }

    public boolean actualizar(AdministradorSistema a) throws SQLException {
        String sql = "UPDATE administradorsistema SET nombre=?, identificacion=?, correo=?, contrasena=?, id_rol=? WHERE idadmin=?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getIdentificacion());
            ps.setString(3, a.getCorreo());
            ps.setString(4, a.getContrasena());
            ps.setInt(5, a.getIdRol());
            ps.setInt(6, a.getIdAdmin());
            int act = ps.executeUpdate();
            return act > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM administradorsistema WHERE idadmin=?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int borr = ps.executeUpdate();
            return borr > 0;
        }
    }
}
