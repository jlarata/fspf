package com.portfolio.fspf.entity;

import javax.persistence.*;



@Entity
//asigna el nombre a la tabla 
@Table(name = "funcion")
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idfuncion;
    
    private Long idpersona;
    private Long idinstitucion;

    private String descripcionfuncion;
    private String periodofuncion;
   
    public Funcion() {
    }
    
    public Funcion(Long idpersona, Long idinstitucion, String descripcionfuncion, String periodofuncion) {
        this.idpersona = idpersona;
        this.idinstitucion = idinstitucion;
        this.descripcionfuncion = descripcionfuncion;
        this.periodofuncion = periodofuncion;
    }
    
    public Long getIdFuncion() {
        return idfuncion;
    }
    public void setIdFuncion(Long id) {
        this.idfuncion = id;
    }
    public Long getIdPersona() {
        return idpersona;
    }
    public void setIdPersona(Long id) {
        this.idpersona = id;
    }
    public Long getIdInstitucion() {
        return idinstitucion;
    }
    public void setIdInstitucion(Long id) {
        this.idinstitucion = id;
    }
    
    public String getDescripcionFuncion() {
        return descripcionfuncion;
    }
    public void setDescripcionFuncion(String descripcionfuncion) {
        this.descripcionfuncion = descripcionfuncion;
    }
    public String getPeriodoFuncion() {
        return periodofuncion;
    }
    
    public void setPeriodoFuncion(String periodofuncion) {
        this.periodofuncion = periodofuncion;
    }
}
