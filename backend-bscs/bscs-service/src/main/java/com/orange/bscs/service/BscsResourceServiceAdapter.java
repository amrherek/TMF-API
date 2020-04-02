package com.orange.bscs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orange.bscs.cms.servicelayeradapter.ResourceServiceAdapterI;
import com.orange.bscs.model.BscsStorageMedium;
import com.orange.bscs.model.criteria.BscsStorageMediumSearchCriteria;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.model.resource.BSCSStorageMedium;

public class BscsResourceServiceAdapter {

    @Autowired
    private ResourceServiceAdapterI resourceServiceAdapter;

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    /**
     * Execute STORAGE_MEDIUM.SEARCH
     * 
     * @param criteria
     * @return
     */
    public List<BscsStorageMedium> findStorageMedium(BscsStorageMediumSearchCriteria criteria) {
        List<BSCSStorageMedium> storageMediums = resourceServiceAdapter.storageMediumSearch(criteria.getBscsModel());
        List<BscsStorageMedium> res = new ArrayList<>();
        for (BSCSStorageMedium storageMedium : storageMediums) {
            res.add(bscsObjectFactory.createBscsStorageMedium(storageMedium));
        }
        return res;
    }

}
