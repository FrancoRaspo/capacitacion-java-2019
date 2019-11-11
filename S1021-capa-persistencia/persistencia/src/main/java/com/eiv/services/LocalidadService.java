package com.eiv.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eiv.dtos.LocalidadDto;
import com.eiv.entities.LocalidadEntity;
import com.eiv.entities.ProvinciaEntity;
import com.eiv.repositories.LocalidadRepository;
import com.eiv.repositories.ProvinciaRepository;

@Service
public class LocalidadService {
    
    @Autowired LocalidadRepository localidadRepository;
    @Autowired ProvinciaRepository provinciaRepository;
    
    @Transactional
    public LocalidadEntity nueva(LocalidadDto localidadDto) {
        Long id = localidadRepository.maxId().orElse(0L) + 1;
        String nombre =  localidadDto.getNombre();
        ProvinciaEntity provinciaEntity  = provinciaRepository
                .findById(localidadDto.getProvinciaId())
                .orElseThrow(() -> new RuntimeException(
                    String.format("El ID de Pronvincia %s no existe!",
                            localidadDto.getProvinciaId())));
            
        LocalidadEntity localidadEntity = new LocalidadEntity(id, 
                nombre, provinciaEntity);
            
        localidadRepository.save(localidadEntity);
            
        return localidadEntity;
            
    }
    
    @Transactional
    public LocalidadEntity actualizar(LocalidadDto localidadDto) {
        
        try {
            LocalidadEntity localidadEntity = localidadRepository
                    .findById(localidadDto.getId())
                    .orElseThrow(() -> new RuntimeException(
                            String.format(
                                    "El Id %s de Localidad es incorrecto", localidadDto.getId())));
        
            localidadEntity.setId(localidadDto.getId());
            localidadEntity.setNombre(localidadDto.getNombre());
        
            ProvinciaEntity provinciaEntity  = provinciaRepository
                    .findById(localidadDto.getProvinciaId())
                    .orElseThrow(() -> new RuntimeException(
                            String.format("El ID de Pronvincia %s no existe!",
                                    localidadDto.getProvinciaId())));
              
            localidadEntity.setProvincia(provinciaEntity);
              
            localidadRepository.save(localidadEntity);
            return localidadEntity;
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format(
                            "Ocurrió un error al actualizar la Localidad con Id %s" 
                                    + e.getMessage(), localidadDto.getId()), e);
        }
        
    }
    
    @Transactional()
    public Optional<LocalidadEntity> buscarPorId(Long localidadId) {
        return localidadRepository.findById(localidadId);
    }
    
    @Transactional
    public void eliminar(Long localidadId) {
        
        try {
            LocalidadEntity localidadEntity = localidadRepository
                    .findById(localidadId)
                    .orElseThrow(() -> new RuntimeException(
                        String.format("El Id %s de Localidad es incorrecto", localidadId)));
        
        
            localidadRepository.delete(localidadEntity.getId());
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format(
                            "Ocurrió un error al eliminar la Localidad con Id %s", localidadId),e);
        }
    }
    
}
