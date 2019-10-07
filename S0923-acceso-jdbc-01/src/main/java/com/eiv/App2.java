package com.eiv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App2  {
    
    private static final Logger LOG = LogManager.getLogger(App2.class);
    
    public static void main(String[] args) {
        App2 app = new App2();
        app.run();
    }
    
    public void run() {
        
        App2.LOG.info("Iniciando App2...");
        
        try {
            Class.forName(JdbcCfg.CLASS_NAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        
        try (Connection conn = DriverManager.getConnection(JdbcCfg.URL, JdbcCfg.USER,JdbcCfg.PASS);
                Statement stmt = conn.createStatement();
                
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT * FROM PERSONAS P WHERE P.nom_ape like ?"); 
                
                Scanner scanner = new Scanner(System.in);) {
                
            System.out.println("Ingrese un nombre de persona:");
            String nombreParam = scanner.next();
            
            pstmt.setString(1, "%" + nombreParam + "%"); 
                
            ResultSet rs = pstmt.executeQuery(); 
                
            if (!rs.next()) {
                App2.LOG.trace("No hay datos SELECT * FROM PERSONAS P "
                        + "WHERE P.nom_ape like '%{}%'", nombreParam);
                System.out.println("No hay datos.");
            } else {
                App2.LOG.info("Mostrando datos...");
                do {
                    Long nroDoc = rs.getLong("nro_doc");
                    String nombre = rs.getString("nom_ape");
                    System.out.println(
                            String.format("Persona: [%s] [%s]", nroDoc, nombre));
                } while (rs.next());
            }
            
        } catch (SQLException e) {
            App2.LOG.error(e.getMessage());
            throw new RuntimeException(e.getMessage(),e);
        }
        
    }
}
