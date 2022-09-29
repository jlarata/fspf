package com.portfolio.fspf.service;

import com.portfolio.fspf.entity.Usuario;
import com.portfolio.fspf.repository.UsuarioRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// implementa los métodos que comprueban si existe un usuario con un nombre o con un email específico
// además de guardar y obtener un Optinoal por nombre de usuario

@Service
@Transactional

public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public Optional<Usuario> getByNombreUsuario(String nu){
        return usuarioRepository.findByNombreUsuario(nu);
    }
    
    public boolean existePorNombre(String nu){
        return usuarioRepository.existsByNombreUsuario(nu);
    }
    
    public boolean existePorEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }
    
    public void guardar(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    
}
