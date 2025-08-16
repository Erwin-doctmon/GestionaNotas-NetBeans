package com.gestionanotas.modulo;

import java.math.BigDecimal;
import java.util.Date;

public class Reporte {

    private int idReporte;
    private long idEstudiante;
    private String contenido;
    private BigDecimal nota;
    private String asignatura;
    private Date fecha;
    private int anio;
    private int periodo;
    private String desempenosAcademicos;

    // Constructor vacío
    public Reporte() {}

    // Getters y Setters
    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public String getDesempenosAcademicos() {
        return desempenosAcademicos;
    }

    public void setDesempenosAcademicos(String desempenosAcademicos) {
        this.desempenosAcademicos = desempenosAcademicos;
    }
}
