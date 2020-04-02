package com.orange.bscs.model.wrapper;

import com.orange.bscs.api.model.SVLTypeWrapper;
import com.semagroup.targys.framework.common.SVLType;

import java.util.HashMap;
import java.util.Map;

/**
 * Date : 19/07/2017.
 *
 * @author Denis Borscia (deyb6792)
 */
public class SVLTypeWrapperV8 implements SVLTypeWrapper {
    private final SVLType svlType;

    private static Map<Integer, SVLTypeWrapperV8> types = new HashMap<>();

    public SVLTypeWrapperV8(SVLType svlType) {
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
        SVLTypeWrapperV8 type = types.get(value);
        if (null == type) {
            type = new SVLTypeWrapperV8(SVLType.byValue(value));
            types.put(value, type);
        }

        return type;
    }
}
