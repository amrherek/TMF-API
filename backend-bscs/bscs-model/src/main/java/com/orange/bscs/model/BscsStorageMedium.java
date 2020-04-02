package com.orange.bscs.model;

import com.orange.bscs.model.resource.BSCSStorageMedium;

/**
 * Model used for result of STORAGE_MEDIUM.SEARCH command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsStorageMedium {

    protected BSCSStorageMedium storageMedium;

    public BscsStorageMedium(BSCSStorageMedium storageMedium) {
        this.storageMedium = storageMedium;
    }

    public abstract Long getId();

}
