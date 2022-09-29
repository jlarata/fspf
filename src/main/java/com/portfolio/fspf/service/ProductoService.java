package com.portfolio.fspf.service;

import com.portfolio.fspf.entity.Producto;
import com.portfolio.fspf.repository.ProductoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//indica que la clase es un servicio
@Service
//indica que realizará operaciones contra una base de datos
@Transactional
public class ProductoService {
    
    //inyección de dependencias para instanciar una interfaz tipo
    //ProductoRepositry en el caso de que se necesite
    @Autowired
    ProductoRepository productoRepository;
    
    public List<Producto> obtenerTodos(){
        List<Producto> lista = productoRepository.findAll();
        return lista;
    }
    
    public Optional<Producto> obtenerPorId(Long id){
        return productoRepository.findById(id);
    }
    
    public Optional<Producto> obtenerPorNombre(String np){
        return productoRepository.findByNombreProducto(np);
    }
    
    public void guardar(Producto producto){
        productoRepository.save(producto);
    }
    
    public void borrar(Long id){
        productoRepository.deleteById(id);
    }
    
    public boolean existePorNombre(String np){
        return productoRepository.existsByNombreProducto(np);
        }
    
    public boolean existePorId(Long id){
        return productoRepository.existsById(id);
    }
}
