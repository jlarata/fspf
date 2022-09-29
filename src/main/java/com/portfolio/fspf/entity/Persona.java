package com.portfolio.fspf.entity;

import javax.persistence.*;



@Entity
//asigna el nombre a la tabla 
@Table(name = "persona")
public class Persona {

//ID indica que es la clave primaria de la entidad. acá había un notblank pero no pude importarlo así que voló.    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpersona;
    
    //indica que el nombre de cada producto es único
    @Column(unique = true)
    private String nombreYApellido;
    
    private String fechadenacimiento;
    private String domicilio;
    
    
    public Persona() {
    }
    
    public Persona(String nombreYApellido, String fechadenacimiento, String domicilio) {
        this.nombreYApellido = nombreYApellido;
        this.fechadenacimiento = fechadenacimiento;
        this.domicilio = domicilio;
    }
    
    public Long getIdPersona() {
        return idpersona;
    }
    public void SetIdPersona(Long idPersona) {
        this.idpersona = idPersona;
    }
    
    public String getNombreYApellido() {
        return nombreYApellido;
    }
    
    public void setNombreYApellido(String nombreyapellido) {
        this.nombreYApellido = nombreyapellido;
    }
    
    public String getfechadenacimiento() {
        return fechadenacimiento;
    }
    
    public void setfechadenacimiento(String fechadenacimiento) {
        this.fechadenacimiento = fechadenacimiento;
    }
    public String getDomicilio() {
        return domicilio;
    }
    
    public void setDomicilio (String domicilio) {
        this.domicilio = domicilio;
    }
    
}
