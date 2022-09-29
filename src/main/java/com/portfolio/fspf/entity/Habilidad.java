package com.portfolio.fspf.entity;

import javax.persistence.*;



@Entity
//asigna el nombre a la tabla 
@Table(name = "habilidad")
public class Habilidad {

//ID indica que es la clave primaria de la entidad. acá había un notblank pero no pude importarlo así que voló.    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idhabilidad;
    
    private Long idpersona;

    private String nombrehabilidad;
    private String nivelhabilidad;
    private String tipohabilidad;
   
    public Habilidad() {
    }
    
    public Habilidad(Long idpersona, String nombrehabilidad, String nivelhabilidad, String tipohabilidad) {
        this.idpersona = idpersona;
        this.nombrehabilidad = nombrehabilidad;
        this.nivelhabilidad = nivelhabilidad;
        this.tipohabilidad = tipohabilidad;
    }
    
    public Long getIdHabilidad() {
        return idhabilidad;
    }
    public void setIdHabilidad(Long id) {
        this.idhabilidad = id;
    }
    public Long getIdPersona() {
        return idpersona;
    }
    public void setIdPersona(Long id) {
        this.idpersona = id;
    }
    
    public String getNombreHabilidad() {
        return nombrehabilidad;
    }
    public void setNombreHabilidad(String nombrehabilidad) {
        this.nombrehabilidad = nombrehabilidad;
    }
    public String getNivelHabilidad() {
        return nivelhabilidad;
    }
    
    public void setNivelHabilidad(String nivelhabilidad) {
        this.nivelhabilidad = nivelhabilidad;
    }
    public String getTipoHabilidad() {
        return tipohabilidad;
    }
    
    public void setTipoHabilidad(String tipohabilidad) {
        this.tipohabilidad = tipohabilidad;
    }    
}
