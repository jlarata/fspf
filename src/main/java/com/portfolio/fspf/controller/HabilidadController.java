package com.portfolio.fspf.controller;

import com.portfolio.fspf.DTO.Mensaje;
import com.portfolio.fspf.entity.Habilidad;
import com.portfolio.fspf.service.HabilidadService;
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
@RequestMapping("/api/habilidad")

//@CrossOrigin(origins ="http://localhost:4200")
@CrossOrigin(origins ="https://portfolio-1dac6.web.app")
public class HabilidadController {
    
    @Autowired
    HabilidadService habilidadService;
        
    @GetMapping("/lista")
    public ResponseEntity<List<Habilidad>> getHabilidad(){
        List<Habilidad> lista = habilidadService.obtenerTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
        
    @GetMapping("/habilidadporid/{id}")
    public ResponseEntity<List<Habilidad>> getHabilidadByPersona(@PathVariable Long id){
        
        List<Habilidad> listah = habilidadService.obtenerTodos();
        
        listah.removeIf(habilidad -> !habilidad.getIdPersona().equals(id));
        return new ResponseEntity<>(listah, HttpStatus.OK);
    }
    
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Habilidad> getOne(@PathVariable Long id){
        if(!habilidadService.existePorId(id))
            return new ResponseEntity(new Mensaje("unexistent skill"), HttpStatus.NOT_FOUND);
       
        //ATENCION: AQUÍ EN NINGÚN LADO TENGO QUE PONER 'idcarrera'? este método llama al servicio que a su vez llama al repositorio.
        
        Habilidad habilidad = habilidadService.obtenerPorId(id).get();
        return new ResponseEntity<Habilidad>(habilidad, HttpStatus.OK);
    }
        
    @PostMapping("nuevo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody Habilidad habilidad){
        //atención aquí modifiqué el original porque no lo reconocía. si hubiera errores, revisar.
        if(habilidad.getIdPersona() == null || habilidad.getIdPersona()==0)
            return new ResponseEntity(new Mensaje("it's required to assign an idPersona to any new Skill"), HttpStatus.BAD_REQUEST);
        if(isBlank(habilidad.getNombreHabilidad()))
            return new ResponseEntity(new Mensaje("name of skills is required"), HttpStatus.BAD_REQUEST);
        if(isBlank(habilidad.getNivelHabilidad()))
            return new ResponseEntity(new Mensaje("skill level is required"), HttpStatus.BAD_REQUEST);
        if(isBlank(habilidad.getTipoHabilidad()))
            return new ResponseEntity(new Mensaje("skill type is required"), HttpStatus.BAD_REQUEST);        
    
    // evita la creación de dos objetos con el mismo nombre. en este caso no es necesario. ¿si lo saco se romperán los
    // existePorNombreHabilidad? veremos.
    //    if(habilidadService.existePorNombreHabilidad(habilidad.getNombreHabilidad()))
    //        return new ResponseEntity(new Mensaje("skill already exists"), HttpStatus.BAD_REQUEST);
        habilidadService.guardar(habilidad);
        return new ResponseEntity(new Mensaje("skill saved"), HttpStatus.CREATED);
    }
    
    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@RequestBody Habilidad habilidad, @PathVariable("id") Long id){
        if(!habilidadService.existePorId(id))
            return new ResponseEntity(new Mensaje("unexistent skill"), HttpStatus.NOT_FOUND);
        // Idem Postmapping ATENCIÓN.
        if(isBlank(habilidad.getNombreHabilidad()))
            return new ResponseEntity(new Mensaje("skill name is required"), HttpStatus.BAD_REQUEST);
        if(habilidadService.existePorNombreHabilidad(habilidad.getNombreHabilidad())) {
               if (!habilidadService.obtenerPorNombreHabilidad(habilidad.getNombreHabilidad()).get().getIdHabilidad().equals(id)) {
            return new ResponseEntity(new Mensaje("skill name already exists"), HttpStatus.BAD_REQUEST);
            }
        }
        
        
        
        Habilidad habiUpdate = habilidadService.obtenerPorId(id).get();
        habiUpdate.setNombreHabilidad(habilidad.getNombreHabilidad());
        habiUpdate.setIdPersona(habilidad.getIdPersona());
        habiUpdate.setTipoHabilidad(habilidad.getTipoHabilidad());
        habiUpdate.setNivelHabilidad(habilidad.getNivelHabilidad());
        
        habilidadService.guardar(habiUpdate);
        return new ResponseEntity(new Mensaje("skill updated"), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/borrar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!habilidadService.existePorId(id))
            return new ResponseEntity(new Mensaje("unexistent skill"), HttpStatus.NOT_FOUND);
        
        habilidadService.borrar(id);
        return new ResponseEntity(new Mensaje("skill deleted!"), HttpStatus.OK);
    }
    
}
