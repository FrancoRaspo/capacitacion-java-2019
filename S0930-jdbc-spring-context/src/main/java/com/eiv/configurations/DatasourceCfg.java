package com.eiv.configurations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasourceCfg {

    @Value("${app.datasource.driver}")
    private String jdbcDriver;
    
    @Value("${app.datasource.url}")
    private String jdbcUrl;
    @Value("${app.datasource.user}")
    private String jdbcUser;
    @Value("${app.datasource.password}")
    private String jdbcPassword;
    
    @Bean
    public Scanner getScanner() {
        return new Scanner(System.in);
    }
    
    @Bean
    public Connection getConnection() throws ClassNotFoundException {
        Class.forName(jdbcDriver);
        
        try {
            return DriverManager.getConnection(jdbcUrl,jdbcUser,jdbcPassword);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        
    }
}
