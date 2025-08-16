package com.gestionanotas.modulo;

public class Calificacion {
    private int idCalificacion;
    private int idAsignatura;
    private long Documento_Estudiante; 
    private double nota;

    public Calificacion() {}

    public Calificacion(int idAsignatura, long documentoEstudiante, double nota) {
        this.idAsignatura = idAsignatura;
        this.Documento_Estudiante = documentoEstudiante;
        this.nota = nota;
    }

    // Getters
    public int getIdAsignatura() { return idAsignatura; }
    public long getDocumentoEstudiante() { return Documento_Estudiante; }
    public double getNota() { return nota; }

    // Setters
    public void setIdAsignatura(int idAsignatura) { this.idAsignatura = idAsignatura; }
    public void setDocumentoEstudiante(long documentoEstudiante) { this.Documento_Estudiante = documentoEstudiante; }
    public void setNota(double nota) { this.nota = nota; }

    public boolean esValida() {
        return idAsignatura > 0 &&
               Documento_Estudiante > 0 &&
               nota >= 0 && nota <= 5;
    }
}
