package com.orange.bscs.model.wrapper;

import com.lhs.ccb.common.soi.ParameterList;
import com.orange.bscs.api.model.ParameterListWrapper;
import com.orange.bscs.api.model.SVLTypeWrapper;

/**
 *
 * Created by deyb6792 on 16/08/2016.
 */
public class ParameterListWrapperIXR4 implements ParameterListWrapper {
    private final ParameterList parameterList;

    public ParameterListWrapperIXR4(ParameterList parameterList) {
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

    public static class ParameterInfoIXR4 implements ParameterInfoWrapper {
        private final ParameterList.ParameterInfo parameterInfo;

        public ParameterInfoIXR4(ParameterList.ParameterInfo parameterInfo) {
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
            return SVLTypeWrapperIXR4.byValue(parameterInfo.getType().value());
        }

        @Override
        public GroupInfoWrapper getGroupInfo() {
            return new GroupInfoIXR4(parameterInfo.getGroupInfo());
        }

        @Override
        public ParameterListWrapper getSubList() {
            return new ParameterListWrapperIXR4(parameterInfo.getSubList());
        }

        @Override
        public boolean isMandatory() {
            return parameterInfo.isMandatory();
        }

        @Override
        public boolean isPrivateKey() {
            return parameterInfo.isPrivateKey();
        }

        @Override
        public boolean isPublicKey() {
            return parameterInfo.isPublicKey();
        }
    }

    public static class GroupInfoIXR4 extends ParameterInfoIXR4 implements GroupInfoWrapper {
        public GroupInfoIXR4(ParameterList.GroupInfo groupInfo) {
            super(groupInfo);
        }

    }
}
