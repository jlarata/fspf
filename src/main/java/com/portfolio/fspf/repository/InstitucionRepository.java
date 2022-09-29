
package com.portfolio.fspf.repository;

import com.portfolio.fspf.entity.Institucion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InstitucionRepository extends JpaRepository<Institucion, Long>{
    Optional<Institucion> findByNombreinstitucion(String np);
    boolean existsByNombreinstitucion(String np);
}
