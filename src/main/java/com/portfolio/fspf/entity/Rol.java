package com.portfolio.fspf.entity;

import com.portfolio.fspf.enums.RolNombre;
import com.sun.istack.NotNull;
import javax.persistence.*;

@Entity
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //indica que es un campo que se corresponde con un enum tipo String (RolNombre)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RolNombre rolNombre;
            
    public Rol() {
    }
    
    public Rol(@NotNull RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
    
    public Long getId() {
        return id;
    }
    public void SetId(Long id) {
        this.id = id;
    }
    public RolNombre getRolNombre() {
        return rolNombre;
    }
    public void setRolNombre(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
    
}
