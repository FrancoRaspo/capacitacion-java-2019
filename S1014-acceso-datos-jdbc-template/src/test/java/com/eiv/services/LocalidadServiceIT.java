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
import org.springframework.transaction.annotation.Transactional;

import com.eiv.dtos.LocalidadFrm;
import com.eiv.entities.LocalidadEntity;
import com.eiv.entities.ProvinciaEntity;
import com.eiv.repositories.LocalidadRepository;
import com.eiv.repositories.ProvinciaRepository;

@RunWith(MockitoJUnitRunner.class)
public class LocalidadServiceIT {
    
    @InjectMocks private LocalidadService localidadService;

    @Mock private DataSource dataSource;
    
    @Mock private ProvinciaRepository provinciaRepository;
    @Mock private LocalidadRepository localidadRepository;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    @Transactional
    public void givenNewLocalidad_thenSaved() {
        
        Mockito.when(localidadRepository.maxId())
            .thenReturn(Optional.of(0L));

        Mockito.when(provinciaRepository.findById(Mockito.anyLong()))
            .thenReturn(Optional.of(new ProvinciaEntity(1L, "TESTPROV")));

        LocalidadFrm localidadFrm = new LocalidadFrm("TESTLOCALIDAD",1L, 1L);
        
        LocalidadEntity localidadEntity = localidadService.nueva(localidadFrm);
        
        assertThat(localidadEntity.getId()).isEqualTo(1L);
        assertThat(localidadEntity.getNombre()).isEqualTo("TESTLOCALIDAD");
        assertThat(localidadEntity.getProvincia().getId()).isEqualTo(1L);
        assertThat(localidadEntity.getProvincia().getNombre()).isEqualTo("TESTPROV");
        assertThat(localidadEntity.getProvincia()).isNotNull();
        
        Mockito.verify(provinciaRepository).findById(Mockito.eq(1L));
        Mockito.verify(localidadRepository).save(Mockito.eq(localidadEntity));
        
    }

    @Test
    public void givenLocalidad_whenUpdate_thenUpdated() {
        
        ProvinciaEntity provinciaEntity = new ProvinciaEntity(1L, "TESTPROV");
         
        LocalidadEntity localidadEntity = 
                new LocalidadEntity(1L, "TESTLOCALIDAD", provinciaEntity);
        
        Mockito.when(provinciaRepository
                .findById(Mockito.anyLong()))
                .thenReturn(Optional.of(provinciaEntity));

        ProvinciaEntity provinciaNuevaEntity = new ProvinciaEntity(2L, "TESTPROVNUEVA");
         
        Mockito.when(provinciaRepository
                .findById(Mockito.anyLong()))
                .thenReturn(Optional.of(provinciaNuevaEntity));
         
        Mockito.when(localidadRepository
                .findById(Mockito.anyLong()))
                .thenReturn(Optional.of(localidadEntity));
        
        LocalidadFrm localidadFrm = new LocalidadFrm("NOMBRENUEVO",1L, 2L);
        
        LocalidadEntity localidadActualizada = localidadService.actualizar(localidadFrm);
                
        assertThat(localidadActualizada.getId()).isEqualTo(1L);
        assertThat(localidadActualizada.getNombre()).isEqualTo("NOMBRENUEVO");
        assertThat(localidadActualizada.getProvincia()).isEqualTo(provinciaNuevaEntity);
        
        Mockito.verify(localidadRepository).save(Mockito.eq(localidadEntity));
        
    }
    
    @Test
    public void givenLocalidad_thenDeleted() {

        final ProvinciaEntity provinciaEntity = new ProvinciaEntity(1L, "TESTPROV");
        final LocalidadEntity localidadEntity = 
                new LocalidadEntity(1L, "TESTLOCAL", provinciaEntity);

        Mockito.when(localidadRepository
                .findById(Mockito.anyLong()))
                .thenReturn(Optional.of(localidadEntity));
        
        localidadService.eliminar(1L);
        
        Mockito.verify(localidadRepository).findById(Mockito.eq(1L));
        Mockito.verify(localidadRepository).delete(Mockito.eq(1L));
    }
    
    
}
