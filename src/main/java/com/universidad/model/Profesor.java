package com.universidad.model;

import com.universidad.model.interfaces.Autenticable;
import com.universidad.model.interfaces.Evaluador;
import jakarta.persistence.Entity;

// Profesor hereda de Persona e implementa Evaluador y Autenticable
@Entity
public class Profesor extends Persona implements Evaluador, Autenticable {

    private String especialidad;

    public Profesor() {}

    public Profesor(String nombre, String correo, String especialidad) {
        super(nombre, correo);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    // -- Evaluador --
    @Override
    public void evaluar(Estudiante estudiante, double nota) {
        System.out.println("Profesor " + getNombre() + " evalua a " + estudiante.getNombre()
                + " con nota: " + nota);
    }

    // -- Autenticable --
    @Override
    public boolean login(String usuario, String password) {
        boolean acceso = getCorreo().equals(usuario) && especialidad.equals(password);
        System.out.println("Login profesor " + getNombre() + ": " + (acceso ? "OK" : "FALLIDO"));
        return acceso;
    }
}
