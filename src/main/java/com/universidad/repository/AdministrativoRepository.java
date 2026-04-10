package com.universidad.repository;

import com.universidad.model.Administrativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrativoRepository extends JpaRepository<Administrativo, Long> {
}
