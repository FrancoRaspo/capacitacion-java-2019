package com.eiv.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.eiv.dtos.ProvinciaDto;
import com.eiv.entities.ProvinciaEntity;
import com.eiv.repositories.ProvinciaRepository;

@Service
public class ProvinciaService {
    
    @Autowired ProvinciaRepository provinciaRepository;
    
    @Transactional
    public ProvinciaEntity nueva(ProvinciaDto provinciaDto) {
        Long id = provinciaRepository.maxId().orElse(0L) + 1;
        String nombre =  provinciaDto.getNombre();
        
        ProvinciaEntity provinciaEntity = new ProvinciaEntity(id, 
                nombre);
        
        provinciaRepository.save(provinciaEntity);
        
        return provinciaEntity;

    }
    
    @Transactional
    public ProvinciaEntity actualizar(ProvinciaDto provinciaDto) {
        
        try {
            ProvinciaEntity provinciaEntity = provinciaRepository
                    .findById(provinciaDto.getId())
                    .orElseThrow(() -> new RuntimeException(
                      String.format("El Id %s de Provincia es incorrecto", provinciaDto.getId())));
            
            provinciaEntity.setId(provinciaDto.getId());
            provinciaEntity.setNombre(provinciaDto.getNombre());
            
              
            provinciaRepository.save(provinciaEntity);
            return provinciaEntity;
        
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format(
                            "Ocurrió un error al actualizar la Localidad con Id %s",
                            provinciaDto.getId()),e);
        }
        
    }
    
    @Transactional(readOnly = true)
    public Optional<ProvinciaEntity> buscarPorId(Long provinciaId) {
        return provinciaRepository.findById(provinciaId);
    }
    
    @Transactional
    public void eliminar(Long provinciaId) {
        
        try {
            ProvinciaEntity provinciaEntity = provinciaRepository
                    .findById(provinciaId)
                    .orElseThrow(() -> new RuntimeException(
                        String.format("El Id %s de Provincia es incorrecto", provinciaId)));
        
            provinciaRepository.delete(provinciaEntity.getId());
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format(
                            "Ocurrió un error al eliminar la Localidad con Id %s", provinciaId),e);
        }
    }
    
}
