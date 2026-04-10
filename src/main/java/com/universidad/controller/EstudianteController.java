package com.universidad.controller;

import com.universidad.model.Estudiante;
import com.universidad.service.EstudianteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService service;

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    // GET /api/estudiantes  -> lista todos
    @GetMapping
    public List<Estudiante> listar() {
        return service.listarTodos();
    }

    // GET /api/estudiantes/{id}  -> busca por id
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/estudiantes  -> crea nuevo
    @PostMapping
    public Estudiante crear(@RequestBody Estudiante estudiante) {
        return service.guardar(estudiante);
    }

    // PUT /api/estudiantes/{id}  -> actualiza
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizar(@PathVariable Long id,
                                                  @RequestBody Estudiante datos) {
        return service.buscarPorId(id).map(e -> {
            e.setNombre(datos.getNombre());
            e.setCorreo(datos.getCorreo());
            e.setCodigo(datos.getCodigo());
            return ResponseEntity.ok(service.guardar(e));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/estudiantes/{id}  -> elimina
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.buscarPorId(id).isEmpty()) return ResponseEntity.notFound().build();
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // POST /api/estudiantes/{id}/notificar?mensaje=Hola  -> Notificable
    @PostMapping("/{id}/notificar")
    public ResponseEntity<String> notificar(@PathVariable Long id,
                                             @RequestParam String mensaje) {
        return ResponseEntity.ok(service.notificar(id, mensaje));
    }

    // POST /api/estudiantes/{id}/login?usuario=x&password=y  -> Autenticable
    @PostMapping("/{id}/login")
    public ResponseEntity<Boolean> login(@PathVariable Long id,
                                          @RequestParam String usuario,
                                          @RequestParam String password) {
        return ResponseEntity.ok(service.login(id, usuario, password));
    }
}
