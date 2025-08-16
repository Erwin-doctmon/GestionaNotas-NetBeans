package com.gestionanotas.modulo;

public class Estudiante {
    private long ID_Estudiante;
    private String Nombre;
    private String DocumentoEstudiante;
    private String FechaNacimiento;
    private String Grupo;
    private String Acudiente;
    private String Telefono;

    public Estudiante() {}

    public Estudiante(long ID_Estudiante, String Nombre, String DocumentoEstudiante, String FechaNacimiento, String Grupo, String Acudiente, String Telefono) {
        this.ID_Estudiante = ID_Estudiante;
        this.Nombre = Nombre;
        this.DocumentoEstudiante = DocumentoEstudiante;
        this.FechaNacimiento = FechaNacimiento;
        this.Grupo = Grupo;
        this.Acudiente = Acudiente;
        this.Telefono = Telefono;
    }

    // Getters y setters
    public long getID_Estudiante() { return ID_Estudiante; }
    public void setID_Estudiante(long ID_Estudiante) { this.ID_Estudiante = ID_Estudiante; }

    public String getNombre() { return Nombre; }
    public void setNombre(String Nombre) { this.Nombre = Nombre; }

    public String getDocumentoEstudiante() { return DocumentoEstudiante; }
    public void setDocumentoEstudiante(String DocumentoEstudiante) { this.DocumentoEstudiante = DocumentoEstudiante; }

    public String getFechaNacimiento() { return FechaNacimiento; }
    public void setFechaNacimiento(String FechaNacimiento) { this.FechaNacimiento = FechaNacimiento; }

    public String getGrupo() { return Grupo; }
    public void setGrupo(String Grupo) { this.Grupo = Grupo; }

    public String getAcudiente() { return Acudiente; }
    public void setAcudiente(String Acudiente) { this.Acudiente = Acudiente; }

    public String getTelefono() { return Telefono; }
    public void setTelefono(String Telefono) { this.Telefono = Telefono; }
}
