package com.movil1.ejercicio2_4;

public class Firma {
    private String descripcion;
    private byte[]  image;

    public Firma() {
    }

    public Firma(String descripcion, byte[] image) {
        this.descripcion = descripcion;
        this.image = image;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
