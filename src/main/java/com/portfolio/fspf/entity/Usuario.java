package com.portfolio.fspf.entity;

import com.sun.istack.NotNull;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String nombre;
    
    @NotNull
    @Column(unique = true)
    private String nombreUsuario;
    
    @NotNull
    @Column(unique = true)
    private String email;
    
    @NotNull
    private String password;
    
    
    //el campo roles es un conjunto -Set- de objetos tipo Rol. 
    //manytomany indica que un Usuario puede tener uno o más roles (puede ser user y/o admin) y viceversa.
    //jointable significa que JPA crea la tabla usuario_rol con dos campos: usuario_id que apunta a la id del Usuario
    // y rol_id que apunta a la ide del Rol. Las claves primarias son los id de Usuario y Rol, mientras que las claves 
    //foráneas son los dos campos de la tabla usuario_rol
    //joinColumn indica el nombre del campo de la clave en la entidad Usuario mientras que inverseJoinColumn indica la del Rol.
    
    @NotNull
    @ManyToMany
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();
    
    public Usuario() {
    }
    
    public Usuario(@NotNull String nombre, @NotNull String nombreUsuario, @NotNull String email, @NotNull String password) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
    }
    
    public Long getId(){
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<Rol> getRoles() {
        return roles;
    }
    
    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
    
}
