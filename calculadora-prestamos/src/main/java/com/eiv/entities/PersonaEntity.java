package com.eiv.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.eiv.enums.GeneroEnum;
import com.eiv.enums.SiNoEnum;

@Entity
@Table(name = "personas")
public class PersonaEntity {

    @EmbeddedId private PersonaPkEntity pk; 
    
    @OneToOne
    @JoinColumn(name = "id_tipodocumento", nullable = false, updatable = false, insertable = false)
    private TipoDocumentoEntity tipoDocumento;
    
    @Column(name = "numero_documento", nullable = false, updatable = false, insertable = false)
    private Long numeroDocumento;
        
    @Column(name = "nombre_apellido", length = 400, nullable = false)
    private String nombreApellido;
        
    @Column(name = "fecha_nacimiento",  nullable = false)
    private LocalDate fechaNacimiento;
    
    @Column (name = "genero",  length = 1, nullable = false)
    private GeneroEnum genero;
    
    @Column (name = "es_argentino", nullable = false)
    private SiNoEnum esArgentino;

    @Column (name = "correo_electronico", nullable = true)
    private String correoElectronico;
    
    @Column (name = "foto_cara", nullable = true)
    private Byte[] fotoCara;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_localidad", referencedColumnName = "id_localidad", nullable = false)
    private LocalidadEntity localidad;
    
    @Column (name = "codigo_postal", nullable = false)
    private String codigoPostal;

    
    public PersonaEntity() {
        pk = new PersonaPkEntity();
    }
    
    public PersonaEntity(
            TipoDocumentoEntity tipoDocumento, Long numeroDocumento, String nombreApellido,
            LocalDate fechaNacimiento, GeneroEnum genero, SiNoEnum esArgentino,
            String correoElectronico, Byte[] fotoCara, LocalidadEntity localidad,
            String codigoPostal) {
        super();
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombreApellido = nombreApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.esArgentino = esArgentino;
        this.correoElectronico = correoElectronico;
        this.fotoCara = fotoCara;
        this.localidad = localidad;
        this.codigoPostal = codigoPostal;
        
        pk = new PersonaPkEntity(tipoDocumento,numeroDocumento);
    }


    public TipoDocumentoEntity getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentoEntity tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Long getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public GeneroEnum getGenero() {
        return genero;
    }

    public void setGenero(GeneroEnum genero) {
        this.genero = genero;
    }

    public SiNoEnum getEsArgentino() {
        return esArgentino;
    }

    public void setEsArgentino(SiNoEnum esArgentino) {
        this.esArgentino = esArgentino;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Byte[] getFotoCara() {
        return fotoCara;
    }

    public void setFotoCara(Byte[] fotoCara) {
        this.fotoCara = fotoCara;
    }

    public LocalidadEntity getLocalidad() {
        return localidad;
    }

    public void setLocalidad(LocalidadEntity localidad) {
        this.localidad = localidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
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
        PersonaEntity other = (PersonaEntity) obj;
        if (pk == null) {
            if (other.pk != null) {
                return false;
            }
        } else if (!pk.equals(other.pk)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
