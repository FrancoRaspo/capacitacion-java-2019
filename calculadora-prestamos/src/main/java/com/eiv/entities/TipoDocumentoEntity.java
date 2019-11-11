package com.eiv.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.eiv.enums.SiNoEnum;

@Entity
@Table(name = "tipos_documentos")

public class TipoDocumentoEntity {
    
    @Id
    @Column(name = "id_tipodocumento", nullable = false)
    private long id; 
    
    @Column(name = "nombre", length = 200, nullable = false, unique = true)
    private String nombre;
    
    @Column(name = "abreviatura", length = 8, nullable = false)
    private String abreviatura;

    @Column(name = "validar_como_cuit", nullable = false)
    private SiNoEnum validarComoCuit;

    
    public TipoDocumentoEntity(){}
    
    public TipoDocumentoEntity(long id, String nombre, String abreviatura,
            SiNoEnum validarComoCuit) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.validarComoCuit = validarComoCuit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public SiNoEnum getValidarComoCuit() {
        return validarComoCuit;
    }

    public void setValidarComoCuit(SiNoEnum validarComoCuit) {
        this.validarComoCuit = validarComoCuit;
    } 
    
    
    
}
