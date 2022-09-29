package com.portfolio.fspf.service;

import com.portfolio.fspf.entity.Persona;
import com.portfolio.fspf.repository.PersonaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//indica que la clase es un servicio
@Service
//indica que realizará operaciones contra una base de datos
@Transactional
public class PersonaService {
    
    //inyección de dependencias para instanciar una interfaz tipo
    // PersonaRepository en el caso de que se necesite
    @Autowired
    PersonaRepository personaRepository;
    
    public List<Persona> obtenerTodos(){
        List<Persona> lista = personaRepository.findAll();
        return lista;
    }
    
    public Optional<Persona> obtenerPorId(Long id){
        return personaRepository.findById(id);
    }
    
    public Optional<Persona> obtenerPorNombreYApellido(String np){
        return personaRepository.findByNombreYApellido(np);
    }
    
    public void guardar(Persona persona){
        personaRepository.save(persona);
    }
    
    public void borrar(Long id){
        personaRepository.deleteById(id);
    }
    
    public boolean existePorNombreYApellido(String np){
        return personaRepository.existsByNombreYApellido(np);
        }
    
    public boolean existePorId(Long id){
        return personaRepository.existsById(id);
    }
}
