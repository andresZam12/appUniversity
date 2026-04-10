package com.universidad.model;

import com.universidad.model.interfaces.Autenticable;
import com.universidad.model.interfaces.Notificable;
import jakarta.persistence.Entity;

// Estudiante hereda de Persona e implementa Notificable y Autenticable
@Entity
public class Estudiante extends Persona implements Notificable, Autenticable {

    private String codigo;

    public Estudiante() {}

    public Estudiante(String nombre, String correo, String codigo) {
        super(nombre, correo);
        this.codigo = codigo;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    // -- Notificable --
    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("[Notificacion -> Estudiante " + getNombre() + "]: " + mensaje);
    }

    // -- Autenticable --
    @Override
    public boolean login(String usuario, String password) {
        // Logica simple: usuario == correo y password == codigo
        boolean acceso = getCorreo().equals(usuario) && codigo.equals(password);
        System.out.println("Login estudiante " + getNombre() + ": " + (acceso ? "OK" : "FALLIDO"));
        return acceso;
    }
}
