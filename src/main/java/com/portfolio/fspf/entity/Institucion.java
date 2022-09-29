package com.portfolio.fspf.entity;

import javax.persistence.*;



@Entity
//asigna el nombre a la tabla 
@Table(name = "institucion")
public class Institucion {

//ID indica que es la clave primaria de la entidad. acá había un notblank pero no pude importarlo así que voló.    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idinstitucion;
    
    private String nombreinstitucion;
    private String direccioninstitucion;
    private String contactotel;
    private String contactomail;
   
    public Institucion() {
    }
    
    public Institucion(String nombreinstitucion, String direccioninstitucion, String contactotel, String contactomail) {
        this.nombreinstitucion = nombreinstitucion;
        this.direccioninstitucion = direccioninstitucion;
        this.contactotel = contactotel;
        this.contactomail = contactomail;
    }
    
    public Long getIdInstitucion() {
        return idinstitucion;
    }
    public void setIdInstitucion(Long id) {
        this.idinstitucion = id;
    }
    public String getNombreInstitucion() {
        return nombreinstitucion;
    }
    public void setNombreInstitucion(String nombreinstitucion) {
        this.nombreinstitucion = nombreinstitucion;
    }
    public String getDireccionInstitucion() {
        return direccioninstitucion;
    }
    
    public void setDireccionInstitucion(String direccioninstitucion) {
        this.direccioninstitucion = direccioninstitucion;
    }
    public String getContactoTel() {
        return contactotel;
    }
    
    public void setContactoTel(String contactotel) {
        this.contactotel = contactotel;
    }
    public String getContactoMail() {
        return contactomail;
    }
    
    public void setContactoMail(String contactomail) {
        this.contactomail = contactomail;
    }
    
}
