package com.orange.bscs.api.model;

import com.orange.bscs.api.model.exception.SOIException;

import java.util.Date;

/**
 * Created by deyb6792 on 16/08/2016.
 */
public interface SVLObjectFactory {
    SVLObjectWrapper createSVLObject();

    SVLObjectListWrapper createSVLObjectList();

    String getErrorCode(SOIException exception);

    Throwable getParameterListException(String message, Object[] details);

    String getErrorCode(Throwable ce);

    SVLDeserializer getDeserializer();

    Object[] getFaultArguments(Throwable throwable);

    SVLTypeWrapper getSVLTypeListList();

    SVLTypeWrapper getSVLTypeList();

    SVLTypeWrapper getSVLTypeString();

    SVLTypeWrapper svlTypeByValue(int value);

    ParameterListWrapper.ParameterInfoWrapper getParameterListInfo(String name, SVLTypeWrapper type, Integer flags, Short length, ParameterListWrapper sublist);
}
