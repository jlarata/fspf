package com.portfolio.fspf.controller;

import com.portfolio.fspf.DTO.Mensaje;
import com.portfolio.fspf.entity.Funcion;
import com.portfolio.fspf.service.FuncionService;
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
@RequestMapping("/api/funcion")

//@CrossOrigin(origins ="http://localhost:4200")
@CrossOrigin(origins ="https://portfolio-1dac6.web.app")
public class FuncionController {
    
    @Autowired
    FuncionService funcionService;
        
    @GetMapping("/lista")
    public ResponseEntity<List<Funcion>> getFuncion(){
        List<Funcion> lista = funcionService.obtenerTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/listaporid/{id}")
    public ResponseEntity<List<Funcion>> getFuncionByPersona(@PathVariable Long id){
        
        List<Funcion> listad = funcionService.obtenerTodos();
        
        listad.removeIf(funcion -> !funcion.getIdPersona().equals(id));
        return new ResponseEntity<>(listad, HttpStatus.OK);
    }
    
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Funcion> getOne(@PathVariable Long id){
        if(!funcionService.existePorId(id))
            return new ResponseEntity(new Mensaje("unexistent work experience"), HttpStatus.NOT_FOUND);
        Funcion funcion = funcionService.obtenerPorId(id).get();
        return new ResponseEntity<Funcion>(funcion, HttpStatus.OK);
    }
        
    @PostMapping("nuevo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody Funcion funcion){

        if(funcion.getIdPersona() == null || funcion.getIdPersona()==0)
            return new ResponseEntity(new Mensaje("is required to assign the new work experience to a (id)person"), HttpStatus.BAD_REQUEST);
        if(funcion.getIdInstitucion() == null || funcion.getIdInstitucion()==0)
            return new ResponseEntity(new Mensaje("is required to assign the new work experience to an institution"), HttpStatus.BAD_REQUEST);
        
        if(isBlank(funcion.getDescripcionFuncion()))
            return new ResponseEntity(new Mensaje("work experience description is required"), HttpStatus.BAD_REQUEST);
        if(isBlank(funcion.getPeriodoFuncion()))
            return new ResponseEntity(new Mensaje("work experience period is required"), HttpStatus.BAD_REQUEST);
        if(funcionService.existePorDescripcionFuncion(funcion.getDescripcionFuncion()))
            return new ResponseEntity(new Mensaje("work experience already in database"), HttpStatus.BAD_REQUEST);
        
        funcionService.guardar(funcion);
        return new ResponseEntity(new Mensaje("work experience was saved"), HttpStatus.CREATED);
    }
    
    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@RequestBody Funcion funcion, @PathVariable("id") Long id){
        if(!funcionService.existePorId(id))
            return new ResponseEntity(new Mensaje("unexistent work experience"), HttpStatus.NOT_FOUND);
        // Idem Postmapping ATENCIÓN.
        if(isBlank(funcion.getDescripcionFuncion()))
            return new ResponseEntity(new Mensaje("work experience description required"), HttpStatus.BAD_REQUEST);
        if(funcionService.existePorDescripcionFuncion(funcion.getDescripcionFuncion())) {
               if (!funcionService.obtenerPorDescripcionFuncion(funcion.getDescripcionFuncion()).get().getIdFuncion().equals(id)) {
            return new ResponseEntity(new Mensaje("there is another work experience description with that name"), HttpStatus.BAD_REQUEST);
            }
        }
        
        Funcion funcUpdate = funcionService.obtenerPorId(id).get();
        funcUpdate.setDescripcionFuncion(funcion.getDescripcionFuncion());
        funcUpdate.setPeriodoFuncion(funcion.getPeriodoFuncion());
        
        funcionService.guardar(funcUpdate);
        return new ResponseEntity(new Mensaje("work experience updated"), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/borrar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!funcionService.existePorId(id))
            return new ResponseEntity(new Mensaje("nonexistent work experience"), HttpStatus.NOT_FOUND);
        
        funcionService.borrar(id);
        return new ResponseEntity(new Mensaje("work experience was deleted"), HttpStatus.OK);
    }
    
}
