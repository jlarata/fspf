package com.portfolio.fspf.controller;

import com.portfolio.fspf.DTO.Mensaje;
import com.portfolio.fspf.entity.Institucion;
import com.portfolio.fspf.service.InstitucionService;
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
@RequestMapping("/api/institucion")

//@CrossOrigin(origins ="http://localhost:4200")
@CrossOrigin(origins ="https://portfolio-1dac6.web.app")
public class InstitucionController {
    
    @Autowired
    InstitucionService institucionService;
        
    @GetMapping("/lista")
    public ResponseEntity<List<Institucion>> getInstitucion(){
        List<Institucion> lista = institucionService.obtenerTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

// este método lo diseñé para cuando necesite filtrar un join pero en este caso no será necesario.    
//    @GetMapping("/listaporid/{id}")
//    public ResponseEntity<List<Carrera>> getCarreraByPersona(@PathVariable Long id){
//        
//        List<Carrera> listac = carreraService.obtenerTodos();
//        
//        listac.removeIf(carrera -> !carrera.getIdPersona().equals(id));
//        return new ResponseEntity<>(listac, HttpStatus.OK);
//    }
    
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Institucion> getOne(@PathVariable Long id){
        if(!institucionService.existePorId(id))
            return new ResponseEntity(new Mensaje("no existe esa institucion"), HttpStatus.NOT_FOUND);
        Institucion institucion = institucionService.obtenerPorId(id).get();
        return new ResponseEntity<Institucion>(institucion, HttpStatus.OK);
    }
        
    @PostMapping("nuevo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody Institucion institucion){
//este if era para asegurar que se asigne el objeto a otro objeto (con una columna idéntica), aquí no es necesario
//        if(institucion.getIdInstitucion() == null || institucion.getIdInstitucion()==0)
//            return new ResponseEntity(new Mensaje("es obligatorio asignar la carrera a una Id Persona"), HttpStatus.BAD_REQUEST);
        if(isBlank(institucion.getNombreInstitucion()))
            return new ResponseEntity(new Mensaje("institution name is required"), HttpStatus.BAD_REQUEST);
        if(isBlank(institucion.getDireccionInstitucion()))
            return new ResponseEntity(new Mensaje("institution adress is required"), HttpStatus.BAD_REQUEST);
        if(isBlank(institucion.getContactoTel()))
            return new ResponseEntity(new Mensaje("institution phone number is required"), HttpStatus.BAD_REQUEST);
        if(isBlank(institucion.getContactoMail()))
            return new ResponseEntity(new Mensaje("institution mail is required"), HttpStatus.BAD_REQUEST);      
        if(institucionService.existePorNombreInstitucion(institucion.getNombreInstitucion()))
            return new ResponseEntity(new Mensaje("institution already in database"), HttpStatus.BAD_REQUEST);
        
        institucionService.guardar(institucion);
        return new ResponseEntity(new Mensaje("institution was saved"), HttpStatus.CREATED);
    }
    
    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@RequestBody Institucion institucion, @PathVariable("id") Long id){
        if(!institucionService.existePorId(id))
            return new ResponseEntity(new Mensaje("unexistent institution"), HttpStatus.NOT_FOUND);
        // Idem Postmapping ATENCIÓN.
        if(isBlank(institucion.getNombreInstitucion()))
            return new ResponseEntity(new Mensaje("institution name is required"), HttpStatus.BAD_REQUEST);
        if(institucionService.existePorNombreInstitucion(institucion.getNombreInstitucion())) {
               if (!institucionService.obtenerPorNombreInstitucion(institucion.getNombreInstitucion()).get().getIdInstitucion().equals(id)) {
            return new ResponseEntity(new Mensaje("there is another institution with that name"), HttpStatus.BAD_REQUEST);
            }
        }
        
        Institucion instUpdate = institucionService.obtenerPorId(id).get();
        instUpdate.setNombreInstitucion(institucion.getNombreInstitucion());
        instUpdate.setDireccionInstitucion(institucion.getDireccionInstitucion());
        instUpdate.setContactoTel(institucion.getContactoTel());
        instUpdate.setContactoMail(institucion.getContactoMail());
        
        institucionService.guardar(instUpdate);
        return new ResponseEntity(new Mensaje("institution updated"), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/borrar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!institucionService.existePorId(id))
            return new ResponseEntity(new Mensaje("nonexistent institution"), HttpStatus.NOT_FOUND);
        
        institucionService.borrar(id);
        return new ResponseEntity(new Mensaje("institution was deleted"), HttpStatus.OK);
    }
    
}



