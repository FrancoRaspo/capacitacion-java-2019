package com.eiv.converters;

import javax.persistence.AttributeConverter;

import com.eiv.enums.SistemaAmotrizacionEnum;

public class SistemaAmortizacionConverter
        implements AttributeConverter<SistemaAmotrizacionEnum, String> {

    @Override
    public String convertToDatabaseColumn(SistemaAmotrizacionEnum attribute) {

        return  attribute.getSistema();
    }

    @Override
    public SistemaAmotrizacionEnum convertToEntityAttribute(String dbData) {
        
        return SistemaAmotrizacionEnum.of(dbData);
    }

}
