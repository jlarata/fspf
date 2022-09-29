package com.portfolio.fspf.controller;

import com.portfolio.fspf.DTO.Mensaje;
import com.portfolio.fspf.entity.Producto;
import com.portfolio.fspf.service.ProductoService;
import java.util.List;
import static org.apache.commons.lang3.StringUtils.isBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//estas dos anotaciones indican que es un servicio REST y la URL donde se accede
//a esos servicios.

@RestController
@RequestMapping("/api/productos")
//esto habilita la política cors en el controller, sino lo habilitamos se bloquea el llamado desde el cliente
// (y por cierto no se ve nada, excepto que abras la consola. simpático)

//además, aparentemente por esto mismo, el orden de los factores importa. si se levanta la API REST
//sin haber levantado el cliente localhost4200, crashea.

//@CrossOrigin(origins ="http://localhost:4200")
@CrossOrigin(origins ="https://portfolio-1dac6.web.app")

public class ProductoController {
    
    @Autowired
    ProductoService productoService;
    
    //Todos los métodos devuelven un objeto de la clase ResponseEntity que tiene un 
    //estado (httpstatus) con un flag numérico. ok es un valor 200, not found es 404, etc.
    //Esto le da info al cliente Angular para que devuelva error según el código.
    //Los valores los utilizan los métodos de suscripción a un observable.
    
    @GetMapping("/lista")
    public ResponseEntity<List<Producto>> getLista(){
        List<Producto> lista = productoService.obtenerTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Producto> getOne(@PathVariable Long id){
        if(!productoService.existePorId(id))
            return new ResponseEntity(new Mensaje("no existe ese producto"), HttpStatus.NOT_FOUND);
       
        //el método get() sirve para convertir un Optional en el objeto que envuelve:
        Producto producto = productoService.obtenerPorId(id).get();
        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
    }
    
    
    //el método create() no devuelve ningún tipo específico de objeto. Se le indica con
    //requestbody que se enviará un objeto tipo json con los campos correspondientes a un objeto de la
    // clase producto.
    
    @PostMapping("nuevo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody Producto producto){
        //atención aquí modifiqué el original porque no lo reconocía. si hubiera errores, revisar.
        if(isBlank(producto.getNombreProducto()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if((Integer)producto.getPrecio() == null || producto.getPrecio()==0)
            return new ResponseEntity(new Mensaje("el precio es obligatorio"), HttpStatus.BAD_REQUEST);
        if(productoService.existePorNombre(producto.getNombreProducto()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        productoService.guardar(producto);
        return new ResponseEntity(new Mensaje("producto guardado"), HttpStatus.CREATED);
    }
    
    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@RequestBody Producto producto, @PathVariable("id") Long id){
        if(!productoService.existePorId(id))
            return new ResponseEntity(new Mensaje("no existe ese producto"), HttpStatus.NOT_FOUND);
        // Idem Postmapping ATENCIÓN.
        if(isBlank(producto.getNombreProducto()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if((Integer)producto.getPrecio() == null || producto.getPrecio()==0)
            return new ResponseEntity (new Mensaje("el precio es obligatorio"), HttpStatus.BAD_REQUEST);
        
        //acá se tuvieron que dar maña para evitar problemas cunando editás un campo pero no los dos.
        //el if busca que no se actualice un producto con un nombre ya existente
        //pero hay que hacerlo de manera que no se active esta alarma cuando se modifica
        // otro campo (por ejemplo el precio). entonces chequea que no se repita el nombre
        // si y solo si no se repite la ID (en cuyo caso no habría problema) porque sería
        //una modificacion de otro campo dentro del mismo registro).
        if(productoService.existePorNombre(producto.getNombreProducto()) &&
                productoService.obtenerPorNombre(producto.getNombreProducto()).get().getId() !=id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        
        Producto prodUpdate = productoService.obtenerPorId(id).get();
        prodUpdate.setNombreProducto(producto.getNombreProducto());
        prodUpdate.setPrecio(producto.getPrecio());
        
        productoService.guardar(prodUpdate);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/borrar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        //if(!productoService.existePorId(id))
        //    return new ResponseEntity(new Mensaje("no existe ese producto"), HttpStatus.NOT_FOUND);
        
        productoService.borrar(id);
        return new ResponseEntity(new Mensaje("el producto ha sido eliminado"), HttpStatus.OK);
    }
    
}
