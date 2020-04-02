package com.orange.bscs.model.wrapper;

import com.orange.bscs.api.model.ParameterListWrapper;
import com.orange.bscs.api.model.SVLTypeWrapper;
import com.semagroup.targys.servicelayer.common.ParameterList;

/**
 * V8 impl for {@link ParameterListWrapper}
 */
public class ParameterListWrapperV8 implements ParameterListWrapper {

    private final ParameterList parameterList;

    public ParameterListWrapperV8(ParameterList parameterList) {
        this.parameterList = parameterList;
    }

    @Override
    public ParameterList getParameterList() {
        return parameterList;
    }

    public ParameterList getList() {
        return parameterList;
    }

    @Override
    public int size() {
        return parameterList.size();
    }

    public static class ParameterInfoV8 implements ParameterInfoWrapper {

        private final ParameterList.ParameterInfo parameterInfo;

        public ParameterInfoV8(ParameterList.ParameterInfo parameterInfo) {
            this.parameterInfo = parameterInfo;
        }

        @Override
        public String getName() {
            return parameterInfo.getName();
        }

        @Override
        public int getFlags() {
            return parameterInfo.getFlags();
        }

        @Override
        public short getLength() {
            return parameterInfo.getLength();
        }

        @Override
        public SVLTypeWrapper getType() {
            return SVLTypeWrapperV8.byValue(parameterInfo.getType().value());
        }

        @Override
        public GroupInfoWrapper getGroupInfo() {
            // no group info in v8
            return null;
        }

        @Override
        public ParameterListWrapper getSubList() {
            return new ParameterListWrapperV8(parameterInfo.getSubList());
        }

        @Override
        public boolean isMandatory() {
            return (parameterInfo.getFlags() & 1) != 0;
        }

        @Override
        public boolean isPrivateKey() {
            return false;
        }

        @Override
        public boolean isPublicKey() {
            return true;
        }
    }
}
