package com.portfolio.fspf.DTO;


import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;



//crea nueva cuenta. tiene los mismos atributos que la clase que representa a la entidad usuario
// a excepción del id y los roles que, en la nueva clase, serán una colección de Strings

// se comprueba que los campos no estén en blanco y que la dirección de correo esté bien formada
// por medio de las anotaciones NotBlank y Email


public class NuevoUsuario {
    @NotBlank
    private String nombre;
    
    @Email
    private String email;
 
    @NotBlank
    private String nombreUsuario;
    
    @NotBlank
    private String password;
    
    private Set<String> roles;
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRoles() {
        return roles;
    }
    
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
    
}
