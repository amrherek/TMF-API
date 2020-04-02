package com.orange.apibss.common.utils;

import java.beans.PropertyEditorSupport;

/**
 * Converter for case insensitive enum deserialization on restControllers
 * 
 * @author Thibaullt Duperron
 *
 * @param <T>
 *            target enum type
 */
public class EnumCaseInsensitiveConverter<T extends Enum<T>> extends PropertyEditorSupport {

    private final Class<T> typeParameterClass;

    public EnumCaseInsensitiveConverter(Class<T> typeParameterClass) {
        super();
        this.typeParameterClass = typeParameterClass;
    }

    @Override
    public void setAsText(final String text) throws IllegalArgumentException {
        String upper = text.toUpperCase(); // or something more robust
        T value = T.valueOf(typeParameterClass, upper);
        setValue(value);
    }
}
