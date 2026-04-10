package com.universidad;

import com.universidad.model.Administrativo;
import com.universidad.model.Estudiante;
import com.universidad.model.Profesor;
import com.universidad.repository.AdministrativoRepository;
import com.universidad.repository.EstudianteRepository;
import com.universidad.repository.ProfesorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UniversidadApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversidadApplication.class, args);
    }

    // Carga datos de ejemplo al iniciar la aplicacion
    @Bean
    CommandLineRunner cargarDatos(EstudianteRepository estudianteRepo,
                                   ProfesorRepository profesorRepo,
                                   AdministrativoRepository adminRepo) {
        return args -> {
            estudianteRepo.save(new Estudiante("Ana Gomez",    "ana@uni.edu",    "EST-001"));
            estudianteRepo.save(new Estudiante("Luis Perez",   "luis@uni.edu",   "EST-002"));
            profesorRepo.save(new Profesor("Dr. Ramirez",      "ramirez@uni.edu","Matematicas"));
            profesorRepo.save(new Profesor("Dra. Torres",      "torres@uni.edu", "Fisica"));
            adminRepo.save(new Administrativo("Carlos Ruiz",   "carlos@uni.edu", "Registro"));
            adminRepo.save(new Administrativo("Maria Lopez",   "maria@uni.edu",  "Finanzas"));
            System.out.println("=== Datos de prueba cargados ===");
        };
    }
}
