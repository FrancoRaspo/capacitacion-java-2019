package com.eiv.dtos;

public class LocalidadFrm implements LocalidadDto {
    private String nombre;
    private Long id;
    private Long provinciaId;

    public Long getProvinciaId() {
        return provinciaId;
    }

    public void setProvinciaId(Long provinciaId) {
        this.provinciaId = provinciaId;
    }

    public LocalidadFrm() {}
    
    public LocalidadFrm(String nombre, Long id, Long provinciaId) {
        super();
        this.nombre = nombre;
        this.id = id;
        this.provinciaId = provinciaId;
    }
    
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
        LocalidadFrm other = (LocalidadFrm) obj;
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
        return "LocalidadFrm [nombre=" + nombre + ", Id=" + id + ", provinciaId=" + provinciaId
                + "]";
    }

    
}
