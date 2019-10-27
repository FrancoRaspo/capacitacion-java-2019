package com.eiv;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.eiv.entities.LocalidadEntity;
import com.eiv.entities.ProvinciaEntity;

public class App {
  
    private EntityManagerFactory emf;
        
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
    
    public App() {
        this.emf = Persistence.createEntityManagerFactory("data-access-hibernate");
        
        try {
            org.h2.tools.Server.main();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    @SuppressWarnings("unchecked")
    public void run() {
        
        ProvinciaEntity provinciaEntity = new ProvinciaEntity(21L, "SANTA FE");
        LocalidadEntity localidadEntity = new LocalidadEntity(2000L, "ROSARIO", provinciaEntity);
        
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(provinciaEntity);
        em.persist(localidadEntity);
        em.getTransaction().commit();
                
        List<ProvinciaEntity> provinciaEntities = (List<ProvinciaEntity>) em
                .createQuery("FROM ProvinciaEntity").getResultList();
        
        provinciaEntities.forEach(e -> System.out.println(e));
        
        
        List<LocalidadEntity> localidadEntities = 
                (List<LocalidadEntity>) em.createQuery("FROM LocalidadEntity").getResultList();
        
        localidadEntities.forEach(e -> System.out.println(e));
        
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Apriete Enter para salir.");
             
            scanner.nextLine();
             
        }
        
        em.close();
    }
        
}
