package com.gestionanotas.modulo;

public class Notificacion {
    private int idNotificacion;
    private String metodoEnvio;
    private String destinatario;
    private String asunto;
    private String mensaje;

    public Notificacion() {}

    public Notificacion(int idNotificacion, String metodoEnvio, String destinatario, String asunto, String mensaje) {
        this.idNotificacion = idNotificacion;
        this.metodoEnvio = metodoEnvio;
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    // Getters y setters
    public int getIdNotificacion() { return idNotificacion; }
    public void setIdNotificacion(int idNotificacion) { this.idNotificacion = idNotificacion; }

    public String getMetodoEnvio() { return metodoEnvio; }
    public void setMetodoEnvio(String metodoEnvio) { this.metodoEnvio = metodoEnvio; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}