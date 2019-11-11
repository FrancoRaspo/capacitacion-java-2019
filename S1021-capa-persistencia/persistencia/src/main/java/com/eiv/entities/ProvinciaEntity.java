package com.eiv.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "provincias")
public class ProvinciaEntity {

    @Id
    @Column(name = "provincia_id")
    private Long id;
    
    @Column(length = 200, nullable = false, unique = true)
    private String nombre;
    
    @OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY)
    private List<LocalidadEntity> localidades;
    
    public List<LocalidadEntity> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(List<LocalidadEntity> localidades) {
        this.localidades = localidades;
    }

    public ProvinciaEntity() {
    }

    public ProvinciaEntity(Long id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
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
        ProvinciaEntity other = (ProvinciaEntity) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProvinciaEntity [id=" + id + ", nombre=" + nombre + "]";
    }
    
}