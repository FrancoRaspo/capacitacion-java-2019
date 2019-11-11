package com.eiv.configurations;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasourceCfg {
     
    @Bean
    public EntityManagerFactory getEntityManagerFactory()  {
        
        return  Persistence.createEntityManagerFactory("data-access-hibernate");
        
    }
        
    
}
