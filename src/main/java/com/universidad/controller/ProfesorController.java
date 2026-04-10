package com.universidad.controller;

import com.universidad.model.Profesor;
import com.universidad.service.ProfesorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorService service;

    public ProfesorController(ProfesorService service) {
        this.service = service;
    }

    // GET /api/profesores
    @GetMapping
    public List<Profesor> listar() {
        return service.listarTodos();
    }

    // GET /api/profesores/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Profesor> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/profesores
    @PostMapping
    public Profesor crear(@RequestBody Profesor profesor) {
        return service.guardar(profesor);
    }

    // PUT /api/profesores/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Profesor> actualizar(@PathVariable Long id,
                                                @RequestBody Profesor datos) {
        return service.buscarPorId(id).map(p -> {
            p.setNombre(datos.getNombre());
            p.setCorreo(datos.getCorreo());
            p.setEspecialidad(datos.getEspecialidad());
            return ResponseEntity.ok(service.guardar(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/profesores/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.buscarPorId(id).isEmpty()) return ResponseEntity.notFound().build();
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // POST /api/profesores/{id}/evaluar?estudianteId=1&nota=4.5  -> Evaluador
    @PostMapping("/{id}/evaluar")
    public ResponseEntity<String> evaluar(@PathVariable Long id,
                                           @RequestParam Long estudianteId,
                                           @RequestParam double nota) {
        return ResponseEntity.ok(service.evaluar(id, estudianteId, nota));
    }

    // POST /api/profesores/{id}/login?usuario=x&password=y  -> Autenticable
    @PostMapping("/{id}/login")
    public ResponseEntity<Boolean> login(@PathVariable Long id,
                                          @RequestParam String usuario,
                                          @RequestParam String password) {
        return ResponseEntity.ok(service.login(id, usuario, password));
    }
}
