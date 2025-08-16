package com.gestionanotas.modulo;

public class Asignatura {
    private int idAsignatura;
    private String Nombre_Asignatura;
    private long idUsuario;
    public Asignatura() {}

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getNombre() {
        return Nombre_Asignatura;
    }

    public void setNombre(String nombre) {
        this.Nombre_Asignatura = nombre;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
