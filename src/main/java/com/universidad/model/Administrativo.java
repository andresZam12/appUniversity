package com.universidad.model;

import com.universidad.model.interfaces.Aprobador;
import com.universidad.model.interfaces.Notificable;
import jakarta.persistence.Entity;

// Administrativo hereda de Persona e implementa Aprobador y Notificable
@Entity
public class Administrativo extends Persona implements Aprobador, Notificable {

    private String area;

    public Administrativo() {}

    public Administrativo(String nombre, String correo, String area) {
        super(nombre, correo);
        this.area = area;
    }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    // -- Aprobador --
    @Override
    public void aprobarSolicitud(String codigoSolicitud) {
        System.out.println("Administrativo " + getNombre() + " aprueba solicitud: " + codigoSolicitud);
    }

    // -- Notificable --
    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("[Notificacion -> Administrativo " + getNombre() + "]: " + mensaje);
    }
}
