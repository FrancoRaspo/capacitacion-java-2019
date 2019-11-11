package com.eiv.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Table;

import com.eiv.enums.SistemaAmotrizacionEnum;

@Entity
@Table(name = "lineas")
public class LineaEntity {

    @Id
    @Column(name = "linea_id", nullable = false)
    private long id; 
    
    @Column(name = "nombre", length = 200, nullable = false)
    private String nombre;
    
    @Column(name = "sistema_amortizacion", length = 1, nullable = false)
    private SistemaAmotrizacionEnum sistemaAmortizacion; 
    
    @Column(name = "tasa_min",  nullable = false, scale = 18, precision = 2)
    private BigDecimal tasaMin;
    
    @Column(name = "tasa_max", nullable = false, scale = 18, precision = 2)
    private BigDecimal tasaMax;
    
    @Column(name = "cuotas_min",  nullable = false)
    private Long cuotasMin;
    
    @Column(name = "cuotas_max", nullable = false)
    private Long cuotasMax;
    
    @Column(name = "capital_min",  nullable = false, scale = 18, precision = 2)
    private BigDecimal capitalMin;
    
    @Column(name = "capital_max", nullable = false, scale = 18, precision = 2)
    private BigDecimal capitalMax;
    
    
    @Column(name = "fecha_alta", nullable = false)
    private LocalDate fechaAlta;
        
    @JoinColumns ({
            @JoinColumn(name = "usuario_tipo_documento_id",
                    referencedColumnName = "id_tipodocumento", nullable = false),
            @JoinColumn(name = "usuario_tipo_documento_id", 
                    referencedColumnName = "numero_documento", nullable = false)})
    private UsuarioEntity usuario;
    
    public LineaEntity() {}
            
    public LineaEntity(long id, String nombre, SistemaAmotrizacionEnum sistemaAmortizacion,
            BigDecimal tasaMin, BigDecimal tasaMax, Long cuotasMin, Long cuotasMax,
            BigDecimal capitalMin, BigDecimal capitalMax, LocalDate fechaAlta,
            UsuarioEntity usuario) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.sistemaAmortizacion = sistemaAmortizacion;
        this.tasaMin = tasaMin;
        this.tasaMax = tasaMax;
        this.cuotasMin = cuotasMin;
        this.cuotasMax = cuotasMax;
        this.capitalMin = capitalMin;
        this.capitalMax = capitalMax;
        this.fechaAlta = fechaAlta;
        this.usuario = usuario;
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

    public SistemaAmotrizacionEnum getSistemaAmortizacion() {
        return sistemaAmortizacion;
    }

    public void setSistemaAmortizacion(SistemaAmotrizacionEnum sistemaAmortizacion) {
        this.sistemaAmortizacion = sistemaAmortizacion;
    }

    public BigDecimal getTasaMin() {
        return tasaMin;
    }

    public void setTasaMin(BigDecimal tasaMin) {
        this.tasaMin = tasaMin;
    }

    public BigDecimal getTasaMax() {
        return tasaMax;
    }

    public void setTasaMax(BigDecimal tasaMax) {
        this.tasaMax = tasaMax;
    }

    public Long getCuotasMin() {
        return cuotasMin;
    }

    public void setCuotasMin(Long cuotasMin) {
        this.cuotasMin = cuotasMin;
    }

    public Long getCuotasMax() {
        return cuotasMax;
    }

    public void setCuotasMax(Long cuotasMax) {
        this.cuotasMax = cuotasMax;
    }

    public BigDecimal getCapitalMin() {
        return capitalMin;
    }

    public void setCapitalMin(BigDecimal capitalMin) {
        this.capitalMin = capitalMin;
    }

    public BigDecimal getCapitalMax() {
        return capitalMax;
    }

    public void setCapitalMax(BigDecimal capitalMax) {
        this.capitalMax = capitalMax;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
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
        LineaEntity other = (LineaEntity) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }
    
    
        
}
