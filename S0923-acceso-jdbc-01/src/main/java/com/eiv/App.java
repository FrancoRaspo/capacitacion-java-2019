package com.eiv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    
    private static final Logger LOG = LogManager.getLogger(App.class);
     
    public static void main(String[] args) {
      
        App app = new App();
        app.run();
    }
    
    public void run() {
        
        App.LOG.info("Iniciando App...");
        try {
            Class.forName(JdbcCfg.CLASS_NAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        
        
        try (Connection conn = DriverManager.getConnection(JdbcCfg.URL, JdbcCfg.USER, JdbcCfg.PASS);
                Statement stmt = conn.createStatement();

                ResultSet rs = stmt.executeQuery("SELECT * FROM PERSONAS");
                 
                Scanner scanner = new Scanner(System.in);) {
            
            //org.h2.tools.Server.main();
            
            if (!rs.next()) {
                System.out.println("No hay datos.");
                App.LOG.trace("No hay datos SELECT * FROM PERSONAS");
            } else {
                App.LOG.info("Mostrando datos...");
                do {
                    Long nroDoc = rs.getLong("nro_doc");
                    String nombre = rs.getString("nom_ape");
                    System.out.println(
                            String.format("Persona: [%s] [%s]", nroDoc, nombre));
                } while (rs.next());
            }         
            
            
            scanner.nextInt();
            
            
        } catch (SQLException e) {
            App.LOG.error(e.getMessage());
            throw new RuntimeException(e.getMessage(),e);
        }
        
    }
}
