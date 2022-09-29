
package com.portfolio.fspf.repository;

import com.portfolio.fspf.entity.Funcion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FuncionRepository extends JpaRepository<Funcion, Long>{
    Optional<Funcion> findByDescripcionfuncion(String np);
    boolean existsByDescripcionfuncion(String np);
}
