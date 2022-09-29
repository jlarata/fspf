package com.portfolio.fspf.controller;

import com.portfolio.fspf.DTO.Mensaje;
import com.portfolio.fspf.entity.Carrera;
import com.portfolio.fspf.service.CarreraService;
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


@RestController
@RequestMapping("/api/carrera")

//@CrossOrigin(origins ="http://localhost:4200")
@CrossOrigin(origins ="https://portfolio-1dac6.web.app")
public class CarreraController {
    
    @Autowired
    CarreraService carreraService;
        
    @GetMapping("/lista")
    public ResponseEntity<List<Carrera>> getCarrera(){
        List<Carrera> lista = carreraService.obtenerTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
        
    @GetMapping("/listaporid/{id}")
    public ResponseEntity<List<Carrera>> getCarreraByPersona(@PathVariable Long id){
        
        List<Carrera> listac = carreraService.obtenerTodos();
        
        listac.removeIf(carrera -> !carrera.getIdPersona().equals(id));
        return new ResponseEntity<>(listac, HttpStatus.OK);
    }
    
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Carrera> getOne(@PathVariable Long id){
        if(!carreraService.existePorId(id))
            return new ResponseEntity(new Mensaje("no existe esa carrera"), HttpStatus.NOT_FOUND);
       
        //ATENCION: AQUÍ EN NINGÚN LADO TENGO QUE PONER 'idcarrera'? este método llama al servicio que a su vez llama al repositorio.
        
        Carrera carrera = carreraService.obtenerPorId(id).get();
        return new ResponseEntity<Carrera>(carrera, HttpStatus.OK);
    }
     
    
    @PostMapping("nuevo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody Carrera carrera){
        //atención aquí modifiqué el original porque no lo reconocía. si hubiera errores, revisar.
        if(carrera.getIdPersona() == null || carrera.getIdPersona()==0)
            return new ResponseEntity(new Mensaje("es obligatorio asignar la carrera a una Id Persona"), HttpStatus.BAD_REQUEST);
        if(isBlank(carrera.getInstitucion()))
            return new ResponseEntity(new Mensaje("la institución es obligatoria"), HttpStatus.BAD_REQUEST);
        if(isBlank(carrera.getTitulo()))
            return new ResponseEntity(new Mensaje("el título es obligatorio"), HttpStatus.BAD_REQUEST);
        if(carrera.getFinalizado() == null)
            return new ResponseEntity(new Mensaje("es obligatorio indicar si ha finalizado"), HttpStatus.BAD_REQUEST);
        if(carreraService.existePorTitulo(carrera.getTitulo()))
            return new ResponseEntity(new Mensaje("esa carrera ya fue ingresada"), HttpStatus.BAD_REQUEST);
        carreraService.guardar(carrera);
        return new ResponseEntity(new Mensaje("carrera guardada"), HttpStatus.CREATED);
    }
    
    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@RequestBody Carrera carrera, @PathVariable("id") Long id){
        if(!carreraService.existePorId(id))
            return new ResponseEntity(new Mensaje("no existe esa carrera"), HttpStatus.NOT_FOUND);
        // Idem Postmapping ATENCIÓN.
        if(isBlank(carrera.getTitulo()))
            return new ResponseEntity(new Mensaje("el título es obligatorio"), HttpStatus.BAD_REQUEST);
        if(carreraService.existePorTitulo(carrera.getTitulo())) {
               if (!carreraService.obtenerPorTitulo(carrera.getTitulo()).get().getIdCarrera().equals(id)) {
            return new ResponseEntity(new Mensaje("ese título ya existe"), HttpStatus.BAD_REQUEST);
            }
        }
        
        
        
        Carrera carrUpdate = carreraService.obtenerPorId(id).get();
        carrUpdate.setTitulo(carrera.getTitulo());
        carrUpdate.setIdPersona(carrera.getIdPersona());
        carrUpdate.setFinalizado(carrera.getFinalizado());
        carrUpdate.setPeriodo(carrera.getPeriodo());
        
        carreraService.guardar(carrUpdate);
        return new ResponseEntity(new Mensaje("carrera actualizada"), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/borrar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!carreraService.existePorId(id))
            return new ResponseEntity(new Mensaje("no existe esa carrera"), HttpStatus.NOT_FOUND);
        
        carreraService.borrar(id);
        return new ResponseEntity(new Mensaje("la carrera ha sido eliminada"), HttpStatus.OK);
    }
    
}
