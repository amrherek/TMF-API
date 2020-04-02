package com.orange.bscs.api.model;

import com.orange.bscs.api.model.exception.SOIException;

import java.util.Date;

/**
 * Created by deyb6792 on 16/08/2016.
 */
public class AbstractSVLObjectFactory {


    private static AbstractSVLObjectFactory instance;
    private final SVLObjectFactory factory;

    private AbstractSVLObjectFactory(SVLObjectFactory factory) {
        this.factory = factory;
    }

    public static AbstractSVLObjectFactory init(SVLObjectFactory factory) {
        instance = new AbstractSVLObjectFactory(factory);
        return instance;
    }

    public static SVLObjectWrapper createSVLObject() {
        return instance.factory.createSVLObject();
    }

    public static SVLObjectListWrapper createSVLObjectList() {
        return instance.factory.createSVLObjectList();
    }

    public static String getErrorCode(SOIException exception) {
        return instance.factory.getErrorCode(exception);
    }

    public static Object[] getFaultArguments(Throwable throwable) {
        return instance.factory.getFaultArguments(throwable);
    }

    public static Throwable getParameterListException(String message, Object[] details) {
        return instance.factory.getParameterListException(message, details);
    }

    public static String getErrorCode(Throwable ce) {
        return instance.factory.getErrorCode(ce);
    }

    public static SVLDeserializer getDeserializer() {
        return instance.factory.getDeserializer();
    }

    public static SVLTypeWrapper getSVLTypeListList() {
        return instance.factory.getSVLTypeListList();
    }

    public static SVLTypeWrapper getSVLTypeList() {
        return instance.factory.getSVLTypeList();
    }

    public static SVLTypeWrapper getSVLTypeString() {
        return instance.factory.getSVLTypeString();
    }

    public static SVLTypeWrapper svlTypeByValue(int value) {
        return instance.factory.svlTypeByValue(value);
    }

    public static ParameterListWrapper.ParameterInfoWrapper getParameterInfo(String name, SVLTypeWrapper type, Integer flags, Short length, ParameterListWrapper sublist) {
        return instance.factory.getParameterListInfo(name, type, flags, length, sublist);

    }

}
