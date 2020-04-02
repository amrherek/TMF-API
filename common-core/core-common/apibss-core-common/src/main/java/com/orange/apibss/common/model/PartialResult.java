package com.orange.apibss.common.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

public class PartialResult<T> {

    @JsonIgnore
    private int totalResultCount;

    private List<T> partialResultList;

    public PartialResult(List<T> partialResultList, int totalResultCount) {
        this.partialResultList = partialResultList;
        this.totalResultCount = totalResultCount;
    }

    public PartialResult() {
        this.partialResultList = new ArrayList<>();
        this.totalResultCount = 0;
    }

    public int getTotalResultCount() {
        return totalResultCount;
    }

    public void setTotalResultCount(int totalResultCount) {
        this.totalResultCount = totalResultCount;
    }

    @JsonValue
    public List<T> getPartialResultList() {
        return partialResultList;
    }

    public void setPartialResultList(List<T> partialResultList) {
        this.partialResultList = partialResultList;
    }

}
