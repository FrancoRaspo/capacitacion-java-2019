package com.eiv.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eiv.entities.ProvinciaEntity;

@Repository
public class ProvinciaRepository implements CrudRepository<ProvinciaEntity, Long> {

    
    @Autowired private EntityManagerFactory emf;
    EntityManager em = emf.createEntityManager();
    
    
    @Override
    public Optional<Long> maxId() {
        
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        Root<ProvinciaEntity> root = cq.from(ProvinciaEntity.class);
        cq.select(qb.max(root.get("id")));
        Long maxId = em.createQuery(cq).getSingleResult().longValue();
              
        return Optional.of(maxId);
    }
    
    @Override
    public Optional<ProvinciaEntity> findById(Long id) {
                
        try {
            ProvinciaEntity provinciaEntity = em.find(ProvinciaEntity.class, id);
            return Optional.of(provinciaEntity);
            
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<ProvinciaEntity> findAll() {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<ProvinciaEntity> cq = qb.createQuery(ProvinciaEntity.class);
        Root<ProvinciaEntity> root = cq.from(ProvinciaEntity.class);
        cq.select(root);

        return em.createQuery(cq).getResultList();
    }

    @Override
    public void save(ProvinciaEntity provinciaEntity) {
        
        Optional<ProvinciaEntity> optional = findById(provinciaEntity.getId());
        
        if (optional.isPresent()) {
            
            ProvinciaEntity entity = optional.get();
            entity.setNombre(provinciaEntity.getNombre());
            em.persist(provinciaEntity);
            
        } else {
            
            long id = provinciaEntity.getId() == -1 
                    ? maxId().orElse(0L) + 1L : provinciaEntity.getId();
            String nombre = provinciaEntity.getNombre();
            
            provinciaEntity.setId(id);
            provinciaEntity.setNombre(nombre);
            
            em.persist(provinciaEntity);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<ProvinciaEntity> optional = findById(id);
        if (optional.isPresent()) {
            em.remove(optional.get());
        }
    }
}
