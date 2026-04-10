package com.universidad.service;

import com.universidad.model.Estudiante;
import com.universidad.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    private final EstudianteRepository repo;

    public EstudianteService(EstudianteRepository repo) {
        this.repo = repo;
    }

    public List<Estudiante> listarTodos() {
        return repo.findAll();
    }

    public Optional<Estudiante> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public Estudiante guardar(Estudiante estudiante) {
        return repo.save(estudiante);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    // Delega la logica de la interfaz Notificable
    public String notificar(Long id, String mensaje) {
        return repo.findById(id).map(e -> {
            e.enviarNotificacion(mensaje);
            return "Notificacion enviada a " + e.getNombre();
        }).orElse("Estudiante no encontrado");
    }

    // Delega la logica de la interfaz Autenticable
    public boolean login(Long id, String usuario, String password) {
        return repo.findById(id)
                .map(e -> e.login(usuario, password))
                .orElse(false);
    }
}
