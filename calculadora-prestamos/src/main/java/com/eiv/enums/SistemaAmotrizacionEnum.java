package com.eiv.enums;

public enum SistemaAmotrizacionEnum {
    FRANCES("F"),
    AMERICANO("A");
    
    private String sistema;
    
    private SistemaAmotrizacionEnum(String sistema) {
        this.sistema = sistema;
    }   
    
    public String getSistema() {
        return sistema;
    } 
    
    public static SistemaAmotrizacionEnum of(String sistema) {
        if (sistema == null) {
            throw new IllegalArgumentException("El sistema no puede ser nulo");
        } else if (sistema.trim().equalsIgnoreCase("F")) {
            return FRANCES;
        } else if (sistema.trim().equalsIgnoreCase("A")) {
            return AMERICANO;
        } else {
            throw new IllegalArgumentException(
                    String.format("No se reconoce %s como un sistema válido", sistema));
        }
    }
   
}
