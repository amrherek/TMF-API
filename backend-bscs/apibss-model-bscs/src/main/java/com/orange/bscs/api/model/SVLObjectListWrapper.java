package com.orange.bscs.api.model;

/**
 * Created by deyb6792 on 12/08/2016.
 */
public interface SVLObjectListWrapper {
    boolean isEmpty();

    int size();

    SVLObjectWrapper get(int i);

    void add(SVLObjectWrapper svlObject);

    void add(int index, SVLObjectWrapper currentListValue);

    SVLObjectWrapper[] toArray();
}
