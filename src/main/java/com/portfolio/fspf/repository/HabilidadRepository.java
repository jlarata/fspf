
package com.portfolio.fspf.repository;

import com.portfolio.fspf.entity.Habilidad;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HabilidadRepository extends JpaRepository<Habilidad, Long>{
    Optional<Habilidad> findByNombrehabilidad(String np);
    boolean existsByNombrehabilidad(String np);
}
