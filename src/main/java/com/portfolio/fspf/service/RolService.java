package com.portfolio.fspf.service;

//implemente solamente el método para obtener un Rol a partir de un objeto de tipo RolNombre

import com.portfolio.fspf.entity.Rol;
import com.portfolio.fspf.enums.RolNombre;
import com.portfolio.fspf.repository.RolRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional

public class RolService {
    
    @Autowired
    RolRepository rolRepository;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }
    
}
