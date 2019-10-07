package com.eiv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App3  {
    private static final Logger LOG = LogManager.getLogger(App3.class);
    
    public static DataSource dataSource;
    
    static {
        dataSource = new PoolProvider().getDataSource();
    }
    
    public static void main(String[] args) throws Exception {
        App3 app = new App3();
        app.run();
    }
    
       
    public void run() throws Exception {
        
        App3.LOG.info("Iniciando App3...");
        
        try {
            Class.forName(JdbcCfg.CLASS_NAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        
        try (Connection conn = dataSource.getConnection();
                Scanner scanner = new Scanner(System.in);) {
            System.out.println("Ingrese código nacionalidad:");
                
            PreparedStatement paisStmt = conn.prepareStatement(
                    "SELECT * FROM PAIS WHERE nacionalidad like ?");
            String nacionalidad = scanner.next();
            
            paisStmt.setString(1, nacionalidad + "%"); 
            
            ResultSet rsPais = paisStmt.executeQuery(); 
    
            if (!rsPais.next()) {
                App3.LOG.trace("No hay datos "
                        + "SELECT * FROM PAIS WHERE nacionalidad like '{}%'", 
                        nacionalidad);
                System.out.println("No se encontró la nacionalidad buscada.");
            } else {
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT * FROM PERSONAS P WHERE nacionalidad=?"); 
                
                pstmt.setInt(1, rsPais.getInt("pais")); 
                    
                ResultSet rs = pstmt.executeQuery(); 
                    
                //org.h2.tools.Server.main();
                
                if (!rs.next()) {
                    System.out.println("No hay datos.");
                    App3.LOG.trace("No hay datos SELECT * FROM "
                            + "PERSONAS P WHERE nacionalidad={}",
                            rsPais.getInt("pais"));
                } else {
                    do {
                        Long nroDoc = rs.getLong("nro_doc");
                        String nombre = rs.getString("nom_ape");
                        System.out.println(
                                String.format("Persona: [%s] [%s]", nroDoc, nombre));
                    } while (rs.next());
                }
                    
            }
        } catch (SQLException e) {
            App3.LOG.error(e.getMessage());
            throw new RuntimeException(e.getMessage(),e);
        }
        
    }
}
