package cootransporte;

public class AdministradorSistema {
    private int idAdmin;
    private String nombre;
    private String identificacion;
    private String correo;
    private String contrasena;
    private int idRol;

    public AdministradorSistema() {}

    public AdministradorSistema(String nombre, String identificacion, String correo, String contrasena, int idRol) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.correo = correo;
        this.contrasena = contrasena;
        this.idRol = idRol;
    }

    // Getters y setters
    public int getIdAdmin() { return idAdmin; }
    public void setIdAdmin(int idAdmin) { this.idAdmin = idAdmin; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public int getIdRol() { return idRol; }
    public void setIdRol(int idRol) { this.idRol = idRol; }

    @Override
    public String toString() {
        return "AdministradorSistema{" +
                "idAdmin=" + idAdmin +
                ", nombre='" + nombre + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", idRol=" + idRol +
                '}';
    }
}
