package com.portfolio.fspf.service;

import com.portfolio.fspf.entity.Carrera;
import com.portfolio.fspf.repository.CarreraRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CarreraService {
    
    @Autowired
    CarreraRepository carreraRepository;
    
    public List<Carrera> obtenerTodos(){
        List<Carrera> lista = carreraRepository.findAll();
        return lista;
    }
    
    public Optional<Carrera> obtenerPorId(Long id){
        return carreraRepository.findById(id);
    }
    
    public Optional<Carrera> obtenerPorTitulo(String np){
        return carreraRepository.findByTitulo(np);
    }
    
    public void guardar(Carrera carrera){
        carreraRepository.save(carrera);
    }
    
    public void borrar(Long id){
        carreraRepository.deleteById(id);
    }
    
    public boolean existePorTitulo(String np){
        return carreraRepository.existsByTitulo (np);
        }
    
    public boolean existePorId(Long id){
        return carreraRepository.existsById(id);
    }
}
