package com.example.loginfirebasemail77.modelos;

public class Lista {
    private String faculta;
    private String ubicación ;
    private String docente;
    private  String nombre;

    public Lista(String faculta, String ubicación, String docente, String nombre) {
        this.faculta = faculta;
        this.ubicación = ubicación;
        this.docente = docente;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Lista(String faculta, String ubicación, String docente) {
        this.faculta = faculta;
        this.ubicación = ubicación;
        this.docente = docente;
    }

    public String getFaculta() {
        return faculta;
    }

    public void setFaculta(String faculta) {
        this.faculta = faculta;
    }

    public String getUbicación() {
        return ubicación;
    }

    public void setUbicación(String ubicación) {
        this.ubicación = ubicación;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

}
