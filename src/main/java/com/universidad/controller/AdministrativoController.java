package com.universidad.controller;

import com.universidad.model.Administrativo;
import com.universidad.service.AdministrativoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrativos")
public class AdministrativoController {

    private final AdministrativoService service;

    public AdministrativoController(AdministrativoService service) {
        this.service = service;
    }

    // GET /api/administrativos
    @GetMapping
    public List<Administrativo> listar() {
        return service.listarTodos();
    }

    // GET /api/administrativos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Administrativo> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/administrativos
    @PostMapping
    public Administrativo crear(@RequestBody Administrativo administrativo) {
        return service.guardar(administrativo);
    }

    // PUT /api/administrativos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Administrativo> actualizar(@PathVariable Long id,
                                                      @RequestBody Administrativo datos) {
        return service.buscarPorId(id).map(a -> {
            a.setNombre(datos.getNombre());
            a.setCorreo(datos.getCorreo());
            a.setArea(datos.getArea());
            return ResponseEntity.ok(service.guardar(a));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/administrativos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.buscarPorId(id).isEmpty()) return ResponseEntity.notFound().build();
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // POST /api/administrativos/{id}/aprobar?codigoSolicitud=SOL-001  -> Aprobador
    @PostMapping("/{id}/aprobar")
    public ResponseEntity<String> aprobar(@PathVariable Long id,
                                           @RequestParam String codigoSolicitud) {
        return ResponseEntity.ok(service.aprobarSolicitud(id, codigoSolicitud));
    }

    // POST /api/administrativos/{id}/notificar?mensaje=Hola  -> Notificable
    @PostMapping("/{id}/notificar")
    public ResponseEntity<String> notificar(@PathVariable Long id,
                                             @RequestParam String mensaje) {
        return ResponseEntity.ok(service.notificar(id, mensaje));
    }
}
