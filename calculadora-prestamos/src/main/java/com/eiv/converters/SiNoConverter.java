package com.eiv.converters;

import javax.persistence.AttributeConverter;

import com.eiv.enums.SiNoEnum;

public class SiNoConverter  implements AttributeConverter<SiNoEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SiNoEnum attribute) {
        
        return attribute.getSiNo();
    }

    @Override
    public SiNoEnum convertToEntityAttribute(Integer dbData) {

        return SiNoEnum.of(dbData);
    }

}
