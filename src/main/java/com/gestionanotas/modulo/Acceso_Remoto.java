package com.gestionanotas.modulo;

import java.util.Date;

public class Acceso_Remoto {
    private int idSesion;
    private String usuario;
    private String dispositivo;
    private Date fecha;

    public Acceso_Remoto() {}

    public Acceso_Remoto(int idSesion, String usuario, String dispositivo, Date fecha) {
        this.idSesion = idSesion;
        this.usuario = usuario;
        this.dispositivo = dispositivo;
        this.fecha = fecha;
    }

    public int getIdSesion() { return idSesion; }
    public void setIdSesion(int idSesion) { this.idSesion = idSesion; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getDispositivo() { return dispositivo; }
    public void setDispositivo(String dispositivo) { this.dispositivo = dispositivo; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    // Métodos funcionales del diagrama
    public void iniciarSesionRemota() {
        System.out.println("Sesión remota iniciada por: " + usuario + " desde el dispositivo: " + dispositivo + " a las " + fecha);
    }

    public void accederFuncionalidades() {
        System.out.println("Accediendo a funcionalidades del sistema como: " + usuario);
    }
}
