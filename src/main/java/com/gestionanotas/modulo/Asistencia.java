package com.gestionanotas.modulo;

import java.time.LocalDate;

public class Asistencia {
    private int idAsistencia;
    private int idAsignatura;
    private long Documento_Estudiante;
    private LocalDate fecha;
    private String estadoAsistencia;

    public Asistencia() {}

    public Asistencia(int idAsignatura, long documentoEstudiante, LocalDate fecha, String estadoAsistencia) {
        this.idAsignatura = idAsignatura;
        this.Documento_Estudiante = documentoEstudiante;
        this.fecha = fecha;
        this.estadoAsistencia = estadoAsistencia;
    }

    // Getters
    public int getIdAsignatura() { return idAsignatura; }
    public long getDocumentoEstudiante() { return Documento_Estudiante; }
    public LocalDate getFecha() { return fecha; }
    public String getEstadoAsistencia() { return estadoAsistencia; }

    // Setters
    public void setIdAsignatura(int idAsignatura) { this.idAsignatura = idAsignatura; }
    public void setDocumentoEstudiante(long documentoEstudiante) { this.Documento_Estudiante = documentoEstudiante; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setEstadoAsistencia(String estadoAsistencia) { this.estadoAsistencia = estadoAsistencia; }

    public boolean esValida() {
        return idAsignatura > 0 &&
               Documento_Estudiante > 0 &&
               fecha != null &&
               estadoAsistencia != null &&
               !estadoAsistencia.isEmpty();
    }
}
