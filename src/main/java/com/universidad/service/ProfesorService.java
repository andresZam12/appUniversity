package com.universidad.service;

import com.universidad.model.Estudiante;
import com.universidad.model.Profesor;
import com.universidad.repository.EstudianteRepository;
import com.universidad.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {

    private final ProfesorRepository repo;
    private final EstudianteRepository estudianteRepo;

    public ProfesorService(ProfesorRepository repo, EstudianteRepository estudianteRepo) {
        this.repo = repo;
        this.estudianteRepo = estudianteRepo;
    }

    public List<Profesor> listarTodos() {
        return repo.findAll();
    }

    public Optional<Profesor> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public Profesor guardar(Profesor profesor) {
        return repo.save(profesor);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    // Delega la logica de la interfaz Evaluador
    public String evaluar(Long profesorId, Long estudianteId, double nota) {
        Optional<Profesor> prof = repo.findById(profesorId);
        Optional<Estudiante> est = estudianteRepo.findById(estudianteId);

        if (prof.isEmpty()) return "Profesor no encontrado";
        if (est.isEmpty()) return "Estudiante no encontrado";

        prof.get().evaluar(est.get(), nota);
        return "Profesor " + prof.get().getNombre() + " evaluo a "
                + est.get().getNombre() + " con nota " + nota;
    }

    // Delega la logica de la interfaz Autenticable
    public boolean login(Long id, String usuario, String password) {
        return repo.findById(id)
                .map(p -> p.login(usuario, password))
                .orElse(false);
    }
}
