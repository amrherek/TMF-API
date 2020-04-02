package com.orange.bscs.api.model;

/**
 *
 * Created by deyb6792 on 16/08/2016.
 */
public interface ParameterListWrapper {

    <T> T getParameterList();

    int size();

    interface ParameterInfoWrapper {
        String getName();
        int getFlags();
        short getLength();
        SVLTypeWrapper getType();

        GroupInfoWrapper getGroupInfo();

        ParameterListWrapper getSubList();

        boolean isMandatory();
        boolean isPrivateKey();
        boolean isPublicKey();
    }

    interface GroupInfoWrapper extends ParameterInfoWrapper {

    }
}
