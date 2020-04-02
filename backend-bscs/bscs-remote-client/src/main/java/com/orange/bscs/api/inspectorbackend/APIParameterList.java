package com.orange.bscs.api.inspectorbackend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.api.model.ParameterListWrapper;
import com.orange.bscs.api.model.SVLTypeWrapper;


public class APIParameterList implements ParameterListWrapper {

    private static final String NEW_LINE = System.getProperty("line.separator", "\n");

    private Map<String, ParameterInfoWrapper> mapParams = new HashMap<>();

    private SVLTypeWrapper listType;

    public APIParameterList() {
        this(false);
    }

    public APIParameterList(boolean isSubList) {
        listType = isSubList ? AbstractSVLObjectFactory.getSVLTypeListList() : AbstractSVLObjectFactory.getSVLTypeList();
    }

    public void add(ParameterInfoWrapper pi) {
        mapParams.put(pi.getName(), pi);
    }

    public SVLTypeWrapper getDataType(String paramName) {
        ParameterInfoWrapper pi = getParameterInfo(paramName);
        return (null == pi) ? null : pi.getType();
    }

    public int getFlags(String paramName) {
        ParameterInfoWrapper pi = getParameterInfo(paramName);
        return (null == pi) ? 0 : pi.getFlags();
    }

    public List<GroupInfoWrapper> getGroups() {
        List<GroupInfoWrapper> groups = new ArrayList<GroupInfoWrapper>();
        for (ParameterInfoWrapper pi : mapParams.values()) {
            GroupInfoWrapper group = pi.getGroupInfo();
            if (null != group && !groups.contains(group)) {
                groups.add(group);
            }
        }
        return groups;
    }

    public int getLength(String paramName) {
        return mapParams.get(paramName).getLength();
    }

    public SVLTypeWrapper getListType() {
        return listType;
    }

    public ParameterInfoWrapper getParameterInfo(String paramName) {
        return mapParams.get(paramName);
    }

    public String[] getParameterNames() {
        return mapParams.keySet().toArray(new String[]{});
    }

    public ParameterListWrapper getSublist(String paramName) {
        ParameterInfoWrapper pi = getParameterInfo(paramName);
        return null == pi ? null : pi.getSubList();
    }

    public boolean isMandatory(String paramName) {
        ParameterInfoWrapper pi = getParameterInfo(paramName);
        return null != pi && pi.isMandatory();
    }

    public boolean isPrivateKey(String paramName) {
        ParameterInfoWrapper pi = getParameterInfo(paramName);
        return null != pi && pi.isPrivateKey();
    }

    public boolean isPublicKey(String paramName) {
        ParameterInfoWrapper pi = getParameterInfo(paramName);
        return null != pi && pi.isPublicKey();
    }

    public void setGroups(@SuppressWarnings("rawtypes") List arg0) {
        // IXR2 Only @Override
    }

    @Override
    public int size() {
        return mapParams.size();
    }


    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer(100);
        String[] names = getParameterNames();
        for (int i = 0; i < names.length; i++)
            try {
                String name = names[i];
                SVLTypeWrapper localSVLType = getDataType(name);
                buffer.append(name);
                buffer.append(": ");
                buffer.append(localSVLType.toString());
                buffer.append(" flags=");
                buffer.append(getFlags(name));
                buffer.append(" mand=");
                buffer.append(isMandatory(name));
                if (localSVLType == AbstractSVLObjectFactory.getSVLTypeString()) {
                    buffer.append(" len=");
                    buffer.append(getLength(name));
                }
                buffer.append(NEW_LINE);
                if ((localSVLType == AbstractSVLObjectFactory.getSVLTypeList()) ||
                        (localSVLType == AbstractSVLObjectFactory.getSVLTypeListList())) {
                    String toStringList = getSublist(name).toString();
                    try {
                        BufferedReader localBufferedReader = new BufferedReader(new StringReader(toStringList));
                        while (true) {
                            String subLine = localBufferedReader.readLine();
                            if (subLine == null)
                                break;
                            buffer.append("> ");
                            buffer.append(subLine);
                            buffer.append(NEW_LINE);
                        }
                    } catch (IOException ioe) {
                        buffer.append("> sorry, problems interpreting sub list");
                        buffer.append(NEW_LINE);
                    }
                }
            } catch (Exception localSignatureMismatchException) {
                buffer.append("> signature mismatch detected: " + localSignatureMismatchException.getMessage());
                buffer.append(NEW_LINE);
            }
        return buffer.toString();
    }

    public void setListType(SVLTypeWrapper byValue) {
        this.listType = byValue;
    }


    public int getFlags() {
        // TODO Auto-generated method stub
        return 0;
    }

    public List<ParameterInfoWrapper> getSublistParameterInfos() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isMandatory() {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public <T> T getParameterList() {
        return null;
    }
}
