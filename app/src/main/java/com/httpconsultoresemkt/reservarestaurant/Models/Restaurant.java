package com.httpconsultoresemkt.reservarestaurant.Models;

/**
 * Created by mnitsch on 04/05/2016.
 */
public class Restaurant {
    private String tipoDeComida;
    private String horario;
    private String lugar;
    private int image;
    private String nombre;
    

    public String tipoDeComida() {
        return tipoDeComida;
    }

    public void tipoDeComida(String tipoDeCompr) {
        this.tipoDeComida = tipoDeCompr;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Restaurant(String tipoDeComida, String horario, String lugar, int image, String nombre) {
        this.tipoDeComida = tipoDeComida;
        this.horario = horario;
        this.lugar = lugar;
        this.image = image;
        this.nombre = nombre;
    }
}
