package com.orange.bscs.model.wrapper;

import java.util.HashMap;
import java.util.Map;

import com.lhs.ccb.common.soi.SVLType;
import com.orange.bscs.api.model.SVLTypeWrapper;

/**
 * Date : 19/07/2017.
 *
 * @author Denis Borscia (deyb6792)
 */
public class SVLTypeWrapperIXR4 implements SVLTypeWrapper {

    private SVLType svlType;
    private static Map<Integer, SVLTypeWrapperIXR4> types = new HashMap<>();

    public SVLTypeWrapperIXR4(SVLType svlType) {
        this.svlType = svlType;
    }

    public SVLType getType() {
        return this.svlType;
    }


    @Override
    public String toString() {
        return svlType.toString();
    }

    public static SVLTypeWrapper byValue(int value) {
        SVLTypeWrapperIXR4 type = types.get(value);
        if (null == type) {
            type = new SVLTypeWrapperIXR4(SVLType.byValue(value));
            types.put(value, type);
        }
        return type;
    }
}
