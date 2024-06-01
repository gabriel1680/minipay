package com.minipay.infrastructure.persistence;

import com.minipay.domain.UserType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserTypeConverter implements AttributeConverter<UserType, String> {
    @Override
    public String convertToDatabaseColumn(final UserType userType) {
        if (userType == null) return null;
        return userType.getType();
    }

    @Override
    public UserType convertToEntityAttribute(final String s) {
        if (s == null) return null;
        return UserType.of(s).orElse(null);
    }
}
