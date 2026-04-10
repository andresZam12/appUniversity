package com.universidad.service;

import com.universidad.model.Administrativo;
import com.universidad.repository.AdministrativoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministrativoService {

    private final AdministrativoRepository repo;

    public AdministrativoService(AdministrativoRepository repo) {
        this.repo = repo;
    }

    public List<Administrativo> listarTodos() {
        return repo.findAll();
    }

    public Optional<Administrativo> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public Administrativo guardar(Administrativo administrativo) {
        return repo.save(administrativo);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    // Delega la logica de la interfaz Aprobador
    public String aprobarSolicitud(Long id, String codigoSolicitud) {
        return repo.findById(id).map(a -> {
            a.aprobarSolicitud(codigoSolicitud);
            return "Solicitud " + codigoSolicitud + " aprobada por " + a.getNombre();
        }).orElse("Administrativo no encontrado");
    }

    // Delega la logica de la interfaz Notificable
    public String notificar(Long id, String mensaje) {
        return repo.findById(id).map(a -> {
            a.enviarNotificacion(mensaje);
            return "Notificacion enviada a administrativo " + a.getNombre();
        }).orElse("Administrativo no encontrado");
    }
}
