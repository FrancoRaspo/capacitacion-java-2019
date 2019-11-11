package com.eiv.dtos;

public class ProvinciaFrm implements ProvinciaDto {

    private Long id;
    private String nombre;
    
    
    public ProvinciaFrm() {}
    
    public ProvinciaFrm(Long id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public Long getId() {
        
        return this.id;
    }

    @Override
    public String getNombre() {

        return this.nombre;
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
        ProvinciaFrm other = (ProvinciaFrm) obj;
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
        return "ProvinciaFrm [id=" + id + ", nombre=" + nombre + "]";
    }
    
    

}
