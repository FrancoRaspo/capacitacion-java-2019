package com.eiv.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PersonaPkEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "id_tipodocumento", nullable = false)
    private Long tipoDocumentoId;
    
    @Column(name = "numero_documento", nullable = false)
    private Long nroDocumento;
    
    
    public PersonaPkEntity() {}
    
    public PersonaPkEntity(TipoDocumentoEntity tipoDocumento, Long nroDocumento) {
        super();
        this.tipoDocumentoId = tipoDocumento.getId();
        this.nroDocumento = nroDocumento;
    }
    
    public Long getTipoDocumentoId() {
        return tipoDocumentoId;
    }
    
    public void setTipoDocumentoId(Long tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId;
    }
    
    public Long getNroDocumento() {
        return nroDocumento;
    }
    
    public void setNroDocumento(Long nroDocumento) {
        this.nroDocumento = nroDocumento;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nroDocumento == null) ? 0 : nroDocumento.hashCode());
        result = prime * result + ((tipoDocumentoId == null) ? 0 : tipoDocumentoId.hashCode());
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
        PersonaPkEntity other = (PersonaPkEntity) obj;
        if (nroDocumento == null) {
            if (other.nroDocumento != null) {
                return false;
            }
        } else if (!nroDocumento.equals(other.nroDocumento)) {
            return false;
        }
        if (tipoDocumentoId == null) {
            if (other.tipoDocumentoId != null) {
                return false;
            }
        } else if (!tipoDocumentoId.equals(other.tipoDocumentoId)) {
            return false;
        }
        return true;
    }
    
    
            
}
