package com.eiv.enums;

public enum SiNoEnum {
    SI(1),
    NO(0);
    
    private Integer siNo;
    
    private SiNoEnum(Integer siNo) {
        this.siNo = siNo;
    }   
    
    public int getSiNo() {
        return siNo;
    } 
    
    public static SiNoEnum of(Integer siNo) {
        if (siNo == null) {
            throw new IllegalArgumentException("El tipo Si/No no puede ser nulo");
        } else if (siNo.equals(1)) {
            return SI;
        } else if (siNo.equals(0)) {
            return NO;
        } else {
            throw new IllegalArgumentException(
                    String.format("No se reconoce %s como Si/No válido", siNo));
        }
    }
}
