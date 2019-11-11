package com.eiv.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eiv.entities.LocalidadEntity;
import com.eiv.entities.ProvinciaEntity;

@Repository
public class LocalidadRepository implements CrudRepository<LocalidadEntity, Long> {
  
    
    @Autowired private EntityManagerFactory emf;
    EntityManager em = emf.createEntityManager();
    private ProvinciaRepository provinciaRepository;
    
       
    @Autowired
    public void setProvinciaRepository(ProvinciaRepository provinciaRepository) {
        this.provinciaRepository = provinciaRepository;
    }

    @Override
    public Optional<LocalidadEntity> findById(Long id) {
        
        try {
            LocalidadEntity localidadEntity = em.find(LocalidadEntity.class, id);
            return Optional.of(localidadEntity);
            
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Long> maxId() {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        Root<LocalidadEntity> root = cq.from(LocalidadEntity.class);
        cq.select(qb.max(root.get("id")));
        Long maxId = em.createQuery(cq).getSingleResult().longValue();
              
        return Optional.of(maxId);
       
    }

    @Override
    public List<LocalidadEntity> findAll() {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<LocalidadEntity> cq = qb.createQuery(LocalidadEntity.class);
        Root<LocalidadEntity> root = cq.from(LocalidadEntity.class);
        cq.select(root);

        return em.createQuery(cq).getResultList();
       
    }

    @Override
    public void save(LocalidadEntity localidadEntity) {

        Optional<LocalidadEntity> optional = findById(localidadEntity.getId());

        
        if (optional.isPresent()) {
            
            LocalidadEntity entity = optional.get();
            
            entity.setNombre(localidadEntity.getNombre());
            
            if (localidadEntity.getProvincia().getId() != entity.getProvincia().getId()) {
                provinciaRepository
                        .findById(localidadEntity.getProvincia().getId())
                        .ifPresent(p -> {
                            entity.setProvincia(p);
                        });
            }
            em.persist(localidadEntity);
            
        } else {
            
            long id = localidadEntity.getId() == -1 
                    ? maxId().orElse(0L) + 1L : localidadEntity.getId();
            
            String nombre = localidadEntity.getNombre();
            ProvinciaEntity provinciaEntity = provinciaRepository
                    .findById(localidadEntity.getProvincia().getId())
                    .get();
            
            localidadEntity.setId(id);
            localidadEntity.setNombre(nombre);
            localidadEntity.setProvincia(provinciaEntity);
            
            em.persist(localidadEntity);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<LocalidadEntity> optional = findById(id);
        if (optional.isPresent()) {
            em.remove(optional.get());
        }
    }
}
