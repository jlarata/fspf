
package com.portfolio.fspf.repository;

import com.portfolio.fspf.entity.Carrera;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long>{
    Optional<Carrera> findByTitulo(String np);
    boolean existsByTitulo(String np);
}
