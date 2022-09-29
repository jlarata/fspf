package com.portfolio.fspf.service;

import com.portfolio.fspf.entity.Funcion;
import com.portfolio.fspf.repository.FuncionRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FuncionService {
    
    @Autowired
    FuncionRepository funcionRepository;
    
    public List<Funcion> obtenerTodos(){
        List<Funcion> lista = funcionRepository.findAll();
        return lista;
    }
    
    public Optional<Funcion> obtenerPorId(Long id){
        return funcionRepository.findById(id);
    }
    
    public Optional<Funcion> obtenerPorDescripcionFuncion(String np){
        return funcionRepository.findByDescripcionfuncion(np);
    }
    
    public void guardar(Funcion funcion){
        funcionRepository.save(funcion);
    }
    
    public void borrar(Long id){
        funcionRepository.deleteById(id);
    }
    
    public boolean existePorDescripcionFuncion(String np){
        return funcionRepository.existsByDescripcionfuncion (np);
        }
    
    public boolean existePorId(Long id){
        return funcionRepository.existsById(id);
    }
}

