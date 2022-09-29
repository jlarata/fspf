package com.portfolio.fspf.service;

import com.portfolio.fspf.entity.Institucion;
import com.portfolio.fspf.repository.InstitucionRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InstitucionService {
    
    @Autowired
    InstitucionRepository institucionRepository;
    
    public List<Institucion> obtenerTodos(){
        List<Institucion> lista = institucionRepository.findAll();
        return lista;
    }
    
    public Optional<Institucion> obtenerPorId(Long id){
        return institucionRepository.findById(id);
    }
    
    public Optional<Institucion> obtenerPorNombreInstitucion(String np){
        return institucionRepository.findByNombreinstitucion(np);
    }
    
    public void guardar(Institucion institucion){
        institucionRepository.save(institucion);
    }
    
    public void borrar(Long id){
        institucionRepository.deleteById(id);
    }
    
    public boolean existePorNombreInstitucion(String np){
        return institucionRepository.existsByNombreinstitucion (np);
        }
    
    public boolean existePorId(Long id){
        return institucionRepository.existsById(id);
    }
}

