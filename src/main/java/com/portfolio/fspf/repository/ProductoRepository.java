
package com.portfolio.fspf.repository;

import com.portfolio.fspf.entity.Producto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
// hereda de JpaRpository, que implementa las operaciones básicas CRUD
// como parámetros: la clase de la identidad sobre la que trabaja y 
// el tipo de dato de la clave primaria: Long.
public interface ProductoRepository extends JpaRepository<Producto, Long>{

//dos métodos: uno para obtener un producto y otro para comprobar si existe uno
//con un nombre determinado. Este para comprobar, antes de insertar un nuevo registro,
//si el nombre  del producto existe. En caso afirmativo, la app devuelve error.
//El primero deuvelve un Optional, que será nulo si la ocnsulta no encuentra ningún
//objeto con ese nombre. Si se implementa un método que retorne un objeto tipo producto,
//en el caso de que la consiulta no devuelva ningún resultado, el programa lanzará una excepción.

//Los nombres de los métodos comienzan por findBy y existBy y les sigue el nombre del atributo
//con la primera letra en mayúscula. findById y existById ya los implementa JPA.
    Optional<Producto> findByNombreProducto(String np);
    boolean existsByNombreProducto(String np);
    
        
    //Optional<Producto> findById(Long id); //<- no es necesario crearlo 
   // boolean existById(Long id); // <- dice que no es necesario crearlo pero sí.
    
}
