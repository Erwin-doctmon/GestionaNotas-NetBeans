package com.gestionanotas.modulo;

import java.util.Date;

public class Historial_Versión {
    private int idHistorial;
    private int idCalificacion;
    private Date fechaModificacion;
    private String estudiante;

    public Historial_Versión() {}

    public Historial_Versión(int idHistorial, int idCalificacion, Date fechaModificacion, String estudiante) {
        this.idHistorial = idHistorial;
        this.idCalificacion = idCalificacion;
        this.fechaModificacion = fechaModificacion;
        this.estudiante = estudiante;
    }

    // Getters y setters
    public int getIdHistorial() { return idHistorial; }
    public void setIdHistorial(int idHistorial) { this.idHistorial = idHistorial; }

    public int getIdCalificacion() { return idCalificacion; }
    public void setIdCalificacion(int idCalificacion) { this.idCalificacion = idCalificacion; }

    public Date getFechaModificacion() { return fechaModificacion; }
    public void setFechaModificacion(Date fechaModificacion) { this.fechaModificacion = fechaModificacion; }

    public String getEstudiante() { return estudiante; }
    public void setEstudiante(String estudiante) { this.estudiante = estudiante; }
}