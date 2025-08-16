package com.gestionanotas.modulo;

public class Comunicacion {
    private int idMensaje;
    private String remitente;
    private String destinatario;
    private String asunto;
    private String mensaje;

    public Comunicacion() {}

    public Comunicacion(int idMensaje, String remitente, String destinatario, String asunto, String mensaje) {
        this.idMensaje = idMensaje;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    // Getters y setters
    public int getIdMensaje() { return idMensaje; }
    public void setIdMensaje(int idMensaje) { this.idMensaje = idMensaje; }

    public String getRemitente() { return remitente; }
    public void setRemitente(String remitente) { this.remitente = remitente; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}