package com.eiv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
public class ListaPersonas {
    
    private static final Logger LOG = LogManager.getLogger(ListaPersonas.class);
    public static final ApplicationContext CTX;
    public static final String SQL;
    
    @Autowired private Connection conn;
    @Autowired private Scanner scanner;
    
    static {
        CTX = new ClassPathXmlApplicationContext("app-config.xml");
        SQL = "SELECT * FROM PERSONAS";
    }
    
     
    public static void main(String[] args) {
      
        ListaPersonas listaPersonas = CTX.getBean(ListaPersonas.class);;
        listaPersonas.run();
    }
    
    public void run() {
        
        LOG.info("Iniciando App...");
        
        try (
                PreparedStatement stmt = conn.prepareStatement(SQL);
                ResultSet rs = stmt.executeQuery();) {
      
            if (!rs.next()) {
                System.out.println("No hay datos.");
                LOG.trace("No hay datos " + SQL);
            } else {
                LOG.info("Mostrando datos...");
                do {
                    Long nroDoc = rs.getLong("nro_doc");
                    String nombre = rs.getString("nom_ape");
                    System.out.println(
                            String.format("Persona: [%s] [%s]", nroDoc, nombre));
                } while (rs.next());
            }         
            
            scanner.nextInt();
            
            
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e.getMessage(),e);
        }
        
    }
}
