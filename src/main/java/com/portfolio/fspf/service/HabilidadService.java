package com.portfolio.fspf.service;

import com.portfolio.fspf.entity.Habilidad;
import com.portfolio.fspf.repository.HabilidadRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HabilidadService {
    
    @Autowired
    HabilidadRepository habilidadRepository;
    
    public List<Habilidad> obtenerTodos(){
        List<Habilidad> lista = habilidadRepository.findAll();
        return lista;
    }
    
    public Optional<Habilidad> obtenerPorId(Long id){
        return habilidadRepository.findById(id);
    }
    
    public Optional<Habilidad> obtenerPorNombreHabilidad(String np){
        return habilidadRepository.findByNombrehabilidad(np);
    }
    
    public void guardar(Habilidad habilidad){
        habilidadRepository.save(habilidad);
    }
    
    public void borrar(Long id){
        habilidadRepository.deleteById(id);
    }
    
    public boolean existePorNombreHabilidad(String np){
        return habilidadRepository.existsByNombrehabilidad (np);
        }
    
    public boolean existePorId(Long id){
        return habilidadRepository.existsById(id);
    }
}
