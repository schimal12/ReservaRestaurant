package com.httpconsultoresemkt.reservarestaurant.Models;

/**
 * Created by mnitsch on 04/05/2016.
 */
public class Reservacion {
    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(String cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    private String restaurant;
    private String nombre;
    private String telefono;
    private String email;

    public Reservacion(String restaurant, String nombre, String telefono, String email, String cantidadPersonas, String fecha, String horaLlegada, String motivo) {
        this.restaurant = restaurant;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.cantidadPersonas = cantidadPersonas;
        this.fecha = fecha;
        this.horaLlegada = horaLlegada;
        this.motivo = motivo;
    }

    private String cantidadPersonas;
    private String fecha;
    private String horaLlegada;
    private String motivo;
}
