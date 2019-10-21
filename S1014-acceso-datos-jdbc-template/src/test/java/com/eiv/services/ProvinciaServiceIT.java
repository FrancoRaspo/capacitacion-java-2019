package com.eiv.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.eiv.dtos.ProvinciaFrm;
import com.eiv.entities.ProvinciaEntity;
import com.eiv.repositories.ProvinciaRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProvinciaServiceIT {

    @InjectMocks private ProvinciaService provinciaService;

    @Mock private DataSource dataSource;
    
    @Mock private ProvinciaRepository provinciaRepository;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void givenNewPronvincia_thenSaved() {
        
       
        Mockito.when(provinciaRepository.maxId())
             .thenReturn(Optional.of(0L));
        
        ProvinciaFrm provinciaFrm = new ProvinciaFrm(1L,"TESTPROV");
       
        ProvinciaEntity provinciaEntity =  provinciaService.nueva(provinciaFrm);
        
        assertThat(provinciaEntity.getId()).isEqualTo(1L);
        assertThat(provinciaEntity.getNombre()).isEqualTo("TESTPROV");
            
        
    }
    
    @Test
    public void givenProvincia_whenUpdate_thenUpdated() {
        
        ProvinciaEntity provinciaAnteriorEntity = new ProvinciaEntity(1L, "TESTPROV");
        
        Mockito.when(provinciaRepository.findById(Mockito.anyLong()))
            .thenReturn(Optional.of(provinciaAnteriorEntity));
        
        
        ProvinciaFrm provinciaFrm = new ProvinciaFrm(2L, "TESTPROVNUEVA");                  
        
        ProvinciaEntity provinciaActualizadaEntity =  provinciaService.actualizar(provinciaFrm);
        
        
        assertThat(provinciaActualizadaEntity.getId()).isEqualTo(2L);
        assertThat(provinciaActualizadaEntity.getNombre()).isEqualTo("TESTPROVNUEVA");
        
    }
    
    @Test
    public void givenProvincia_whenDelete_thenDeleted() {
        
        ProvinciaEntity provinciaEntity = new ProvinciaEntity(1L, "TESTPROV");
        
        Mockito.when(provinciaRepository.findById(Mockito.anyLong()))
            .thenReturn(Optional.of(provinciaEntity));
        
        provinciaService.eliminar(provinciaEntity.getId()); 
        
        Mockito.verify(provinciaRepository).findById(Mockito.eq(provinciaEntity.getId()));
        
    }
    
}
