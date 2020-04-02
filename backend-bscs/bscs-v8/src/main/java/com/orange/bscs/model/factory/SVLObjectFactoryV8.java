package com.orange.bscs.model.factory;

import com.orange.bscs.api.connection.backend.SVLDeserializerV8;
import com.orange.bscs.api.model.*;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.model.wrapper.ParameterListWrapperV8;
import com.orange.bscs.model.wrapper.SVLObjectListWrapperV8;
import com.orange.bscs.model.wrapper.SVLObjectWrapperV8;
import com.orange.bscs.model.wrapper.SVLTypeWrapperV8;
import com.semagroup.targys.framework.common.ExchangeFormatFactory;
import com.semagroup.targys.framework.common.ExtendedSVLObjectFactory;
import com.semagroup.targys.framework.common.SVLType;
import com.semagroup.targys.servicelayer.common.ParameterList;
import com.semagroup.targys.servicelayer.common.ParameterListException;

/**
 * Created by deyb6792 on 17/08/2016.
 */
public class SVLObjectFactoryV8 implements SVLObjectFactory {

    private ExchangeFormatFactory exchangeFormatFactory;

    private int timezoneOffset;

    public SVLObjectFactoryV8(int timezoneOffset) {
        this.exchangeFormatFactory = new ExtendedSVLObjectFactory();
        this.timezoneOffset = timezoneOffset;
    }

    @Override
    public SVLObjectWrapper createSVLObject() {
        return new SVLObjectWrapperV8(exchangeFormatFactory.createSVLObject(), timezoneOffset);
    }

    @Override
    public SVLObjectListWrapper createSVLObjectList() {
        return new SVLObjectListWrapperV8(exchangeFormatFactory.createSVLObjectList(), timezoneOffset);
    }

    @Override
    public String getErrorCode(SOIException exception) {
        String errorCode = "";
        return errorCode;
    }

    @Override
    public Throwable getParameterListException(String message, Object[] details) {
        return new ParameterListException(5, message);
    }

    @Override
    public String getErrorCode(Throwable ce) {
        return "";
    }

    @Override
    public SVLDeserializer getDeserializer() {
        return new SVLDeserializerV8(timezoneOffset);
    }

    @Override
    public Object[] getFaultArguments(Throwable throwable) {
        return new Object[0];
    }

    @Override
    public SVLTypeWrapper getSVLTypeListList() {
        return SVLTypeWrapperV8.byValue(SVLType._SVL_LISTLIST);
    }

    @Override
    public SVLTypeWrapper getSVLTypeList() {
        return SVLTypeWrapperV8.byValue(SVLType._SVL_LIST);
    }

    @Override
    public SVLTypeWrapper getSVLTypeString() {
        return SVLTypeWrapperV8.byValue(SVLType._SVL_STRING);
    }

    @Override
    public SVLTypeWrapper svlTypeByValue(int value) {
        return SVLTypeWrapperV8.byValue(value);
    }

    @Override
    public ParameterListWrapper.ParameterInfoWrapper getParameterListInfo(String name, SVLTypeWrapper type, Integer flags, Short length, ParameterListWrapper sublist) {
        return new ParameterListWrapperV8.ParameterInfoV8(new ParameterList.ParameterInfo(name, ((SVLTypeWrapperV8)type).getType(), flags, length, ((ParameterListWrapperV8)sublist).getList()));
    }
}
