package com.portfolio.fspf.entity;

import javax.persistence.*;



@Entity
//asigna el nombre a la tabla 
@Table(name = "carrera")
public class Carrera {

//ID indica que es la clave primaria de la entidad. acá había un notblank pero no pude importarlo así que voló.    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcarrera;
    
    private Long idpersona;

    private String periodo;
    private String institucion;
    private String titulo;
    private Boolean finalizado;
   
    public Carrera() {
    }
    
    public Carrera(Long idPersona, String periodo, String institucion, String titulo, Boolean finalizado) {
        this.idpersona = idPersona;
        this.periodo = periodo;
        this.institucion = institucion;
        this.titulo = titulo;
        this.finalizado = finalizado;
    }
    
    public Long getIdCarrera() {
        return idcarrera;
    }
    public void setIdCarrera(Long id) {
        this.idcarrera = id;
    }
    public Long getIdPersona() {
        return idpersona;
    }
    public void setIdPersona(Long id) {
        this.idpersona = id;
    }

    
    public String getPeriodo() {
        return periodo;
    }
    
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    public String getInstitucion() {
        return institucion;
    }
    
    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Boolean getFinalizado() {
        return finalizado;
    }
    
    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }



    
}
