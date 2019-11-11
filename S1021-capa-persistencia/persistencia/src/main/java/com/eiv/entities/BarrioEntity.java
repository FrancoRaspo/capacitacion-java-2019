package com.eiv.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "barrios")
public class BarrioEntity {

    @EmbeddedId
    private BarrioPkEntity pk;

    @Column(name = "barrio_id", nullable = false, insertable = false, updatable = false)
    private Long id;
   
    @Column(length = 200, nullable = false)
    private String nombre;

    @OneToOne
    @JoinColumn(name = "localidad_id", referencedColumnName = "localidad_id", 
            nullable = false, insertable = false, updatable = false)
    private LocalidadEntity localidad;
    
    public BarrioEntity() {
        pk = new BarrioPkEntity();
    }
    
    public BarrioEntity(Long id, String nombre, LocalidadEntity localidad) {
        super();
        
        pk = new BarrioPkEntity(id, localidad.getId());
        
        this.id = id;
        this.nombre = nombre;
        this.localidad = localidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        pk.setBarrioId(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalidadEntity getLocalidad() {
        return localidad;
    }

    public void setLocalidad(LocalidadEntity localidad) {
        this.localidad = localidad;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BarrioEntity other = (BarrioEntity) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }
    
    
    
              
}
