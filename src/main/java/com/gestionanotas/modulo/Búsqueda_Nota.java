package com.gestionanotas.modulo;

public class Búsqueda_Nota {
    private int idBusqueda;
    private String criterioBusqueda;
    private String asignatura;

    public Búsqueda_Nota() {}

    public Búsqueda_Nota(int idBusqueda, String criterioBusqueda, String asignatura) {
        this.idBusqueda = idBusqueda;
        this.criterioBusqueda = criterioBusqueda;
        this.asignatura = asignatura;
    }

    // Getters y setters
    public int getIdBusqueda() { return idBusqueda; }
    public void setIdBusqueda(int idBusqueda) { this.idBusqueda = idBusqueda; }

    public String getCriterioBusqueda() { return criterioBusqueda; }
    public void setCriterioBusqueda(String criterioBusqueda) { this.criterioBusqueda = criterioBusqueda; }

    public String getAsignatura() { return asignatura; }
    public void setAsignatura(String asignatura) { this.asignatura = asignatura; }
}