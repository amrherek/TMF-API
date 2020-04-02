package com.orange.bscs.model.wrapper;

import com.orange.bscs.api.model.SVLObjectListWrapper;
import com.orange.bscs.api.model.SVLObjectWrapper;
import com.semagroup.targys.framework.common.SVLObject;
import com.semagroup.targys.framework.common.SVLObjectList;

/**
 * V8 impl of {@link SVLObjectListWrapper}
 */
public class SVLObjectListWrapperV8 implements SVLObjectListWrapper {

	private int timezoneOffset;

    private SVLObjectList svlObjectList;

	public SVLObjectListWrapperV8(SVLObjectList svlObjectList, int timezoneOffset) {
        this.svlObjectList = svlObjectList;
		this.timezoneOffset = timezoneOffset;
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
		return new SVLObjectWrapperV8(this.svlObjectList.get(i), timezoneOffset);
    }

    @Override
    public void add(SVLObjectWrapper svlObject) {
        this.svlObjectList.add(((SVLObjectWrapperV8) svlObject).getSVLObject());
    }

    @Override
    public void add(int index, SVLObjectWrapper currentListValue) {
        this.svlObjectList.add(index, ((SVLObjectWrapperV8) currentListValue).getSVLObject());

    }

    @Override
    public SVLObjectWrapper[] toArray() {
        SVLObject[] inputs = svlObjectList.toArray();
        SVLObjectWrapper[] svlObjectWrappers = new SVLObjectWrapper[inputs.length];
        int i = 0;
        for (SVLObject input : inputs) {
			svlObjectWrappers[i++] = new SVLObjectWrapperV8(input, timezoneOffset);
        }
        return svlObjectWrappers;
    }

    public SVLObjectList getSVLObjectList() {
        return svlObjectList;
    }
}
