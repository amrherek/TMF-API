package com.orange.bscs.model.factory;

import com.lhs.ccb.common.soi.ExchangeFormatFactory;
import com.lhs.ccb.common.soi.ParameterList;
import com.lhs.ccb.common.soi.ParameterListException;
import com.lhs.ccb.common.soi.SVLType;
import com.lhs.ccb.common.soiimpl.DefaultSVLObjectFactory;
import com.lhs.ccb.func.ect.ComponentException;
import com.lhs.ccb.func.ect.SystemException;
import com.orange.bscs.api.connection.backend.SVLDeserializerIXR4;
import com.orange.bscs.api.model.*;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.model.wrapper.ParameterListWrapperIXR4;
import com.orange.bscs.model.wrapper.SVLObjectListWrapperIXR4;
import com.orange.bscs.model.wrapper.SVLObjectWrapperIXR4;
import com.orange.bscs.model.wrapper.SVLTypeWrapperIXR4;


/**
 * Created by deyb6792 on 16/08/2016.
 */
public class SVLObjectFactoryIXR4 implements SVLObjectFactory {

    static {
        try {
            ExchangeFormatFactory.instance();
        } catch (SystemException se) {
            ExchangeFormatFactory.init(new DefaultSVLObjectFactory());
        }
    }

    @Override
    public SVLObjectWrapper createSVLObject() {
        return new SVLObjectWrapperIXR4(ExchangeFormatFactory.instance().createSVLObject());
    }

    @Override
    public SVLObjectListWrapper createSVLObjectList() {
        return new SVLObjectListWrapperIXR4(ExchangeFormatFactory.instance().createSVLObjectList());
    }

    @Override
    public String getErrorCode(SOIException exception) {
        String errorCode = null;
        if (exception.getCause() instanceof ComponentException) {
            errorCode = ((ComponentException) exception.getCause()).getErrorCode();
        }
        return errorCode;
    }

    @Override
    public Throwable getParameterListException(String message, Object[] details) {
        return new ParameterListException(message, details);
    }

    @Override
    public String getErrorCode(Throwable ce) {
        if (ce instanceof ComponentException) {
            return ((ComponentException)ce).getErrorCode();
        }
        return null;
    }

    @Override
    public SVLDeserializer getDeserializer() {
        return new SVLDeserializerIXR4();
    }

    @Override
    public Object[] getFaultArguments(Throwable throwable) {
        if (throwable instanceof ComponentException) {
            return ((ComponentException)throwable).getArguments();
        }
        return new Object[0];
    }

    @Override
    public SVLTypeWrapper getSVLTypeListList() {
        return SVLTypeWrapperIXR4.byValue(SVLType._SVL_LISTLIST);
    }

    @Override
    public SVLTypeWrapper getSVLTypeList() {
        return SVLTypeWrapperIXR4.byValue(SVLType._SVL_LIST);
    }

    @Override
    public SVLTypeWrapper getSVLTypeString() {
        return SVLTypeWrapperIXR4.byValue(SVLType._SVL_STRING);
    }

    @Override
    public SVLTypeWrapper svlTypeByValue(int value) {
        return SVLTypeWrapperIXR4.byValue(value);
    }

    @Override
    public ParameterListWrapper.ParameterInfoWrapper getParameterListInfo(String name, SVLTypeWrapper type, Integer flags, Short length, ParameterListWrapper sublist) {
        return new ParameterListWrapperIXR4.ParameterInfoIXR4(new ParameterList.ParameterInfo(name, ((SVLTypeWrapperIXR4)type).getType(), flags, length, ((ParameterListWrapperIXR4)sublist).getList()));
    }
}
