package com.portfolio.fspf.entity;

import com.sun.istack.NotNull;
import javax.persistence.*;



@Entity
//asigna el nombre a la tabla 
@Table(name = "producto")
public class Producto {

//ID indica que es la clave primaria de la entidad. acá había un notblank pero no pude importarlo así que voló.    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //indica que el nombre de cada producto es único
    @Column(unique = true)
    private String nombreProducto;
    
    @NotNull
    private int precio;
    
    public Producto() {
    }
    
    public Producto(String nombreProducto, int precio) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
    }
    
    public Long getId() {
        return id;
    }
    public void SetId(Long id) {
        this.id = id;
    }
    
    public String getNombreProducto() {
        return nombreProducto;
    }
    
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
    public int getPrecio() {
        return precio;
    }
    
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
}
