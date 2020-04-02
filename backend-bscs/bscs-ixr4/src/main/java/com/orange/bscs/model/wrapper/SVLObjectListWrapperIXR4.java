package com.orange.bscs.model.wrapper;

import com.lhs.ccb.common.soi.SVLObject;
import com.lhs.ccb.common.soi.SVLObjectList;
import com.orange.bscs.api.model.SVLObjectListWrapper;
import com.orange.bscs.api.model.SVLObjectWrapper;

/**
 * Created by deyb6792 on 12/08/2016.
 */
public class SVLObjectListWrapperIXR4 implements SVLObjectListWrapper {

    private final SVLObjectList svlObjectList;

    public SVLObjectListWrapperIXR4(SVLObjectList svlObjectList) {
        this.svlObjectList = svlObjectList;
    }

    @Override
    public boolean isEmpty() {
        return this.svlObjectList.isEmpty();
    }

    @Override
    public int size() {
        return this.svlObjectList.size();
    }

    @Override
    public SVLObjectWrapper get(int i) {
        return new SVLObjectWrapperIXR4(this.svlObjectList.get(i));
    }

    @Override
    public void add(SVLObjectWrapper svlObject) {
        this.svlObjectList.add(((SVLObjectWrapperIXR4) svlObject).getSVLObject());
    }

    @Override
    public void add(int index, SVLObjectWrapper svlObject) {
        this.svlObjectList.add(index, ((SVLObjectWrapperIXR4) svlObject).getSVLObject());
    }

    @Override
    public SVLObjectWrapper[] toArray() {
        SVLObject[] inputs = svlObjectList.toArray();
        SVLObjectWrapper[] svlObjectWrappers = new SVLObjectWrapper[inputs.length];
        int i = 0;
        for (SVLObject input : inputs) {
            svlObjectWrappers[i++] = new SVLObjectWrapperIXR4(input);
        }
        return svlObjectWrappers;
    }

    public SVLObjectList getSVLObjectList() {
        return svlObjectList;
    }

    @Override
    public String toString() {
        return this.svlObjectList.toString();
    }
}
