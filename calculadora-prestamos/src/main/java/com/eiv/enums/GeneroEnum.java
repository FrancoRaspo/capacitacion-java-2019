package com.eiv.enums;

public enum GeneroEnum {
    FEMENINO("F"),
    MASCULINO("M");
    
    private String genero;

    private GeneroEnum(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public GeneroEnum of(String genero) {
        if (genero == null) {
            throw new IllegalArgumentException("El género no puede ser nulo");
        } else if (genero.trim().equalsIgnoreCase("F")) {
            return FEMENINO;
        } else if (genero.trim().equalsIgnoreCase("A")) {
            return MASCULINO;
        } else {
            throw new IllegalArgumentException(
                    String.format("No se reconoce %s como un género válido", genero));
        }
    }
    
}
