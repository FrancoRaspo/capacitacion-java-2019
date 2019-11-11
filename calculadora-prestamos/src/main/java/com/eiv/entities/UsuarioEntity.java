package com.eiv.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

    
    @EmbeddedId private PersonaPkEntity pk; 
    
    @OneToOne
    @MapsId("pk")
    @JoinColumns({
            @JoinColumn(name = "id_tipousuario", referencedColumnName = "id_tipousuario"),
            @JoinColumn(name = "numero_documento", referencedColumnName = "numero_documento")})
    private PersonaEntity persona;
    
    @Column(name = "nombre_usuario", length = 50, nullable = false)
    private String nombre;
    
    @Column(name = "hashed_pwd", length = 200, nullable = false)
    private String hashedPassword;
        
    public UsuarioEntity() {
        pk = new PersonaPkEntity();
    } 
        
    

    public UsuarioEntity(PersonaEntity persona, String nombre, String hashedPassword) {
        super();
        this.persona = persona;
        this.nombre = nombre;
        this.hashedPassword = hashedPassword;
        pk = new PersonaPkEntity(persona.getTipoDocumento(), persona.getNumeroDocumento());
    }



    public PersonaEntity getPersona() {
        return persona;
    }

    public void setPersona(PersonaEntity persona) {
        this.persona = persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

   

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
        UsuarioEntity other = (UsuarioEntity) obj;
        if (pk == null) {
            if (other.pk != null) {
                return false;
            }
        } else if (!pk.equals(other.pk)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UsuarioEntity [id=" + pk + ", nombre=" + nombre  + "]";
    }
    
    
    
}
