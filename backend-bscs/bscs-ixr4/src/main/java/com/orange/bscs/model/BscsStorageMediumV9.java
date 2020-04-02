package com.orange.bscs.model;

import com.orange.bscs.model.resource.BSCSStorageMedium;

public class BscsStorageMediumV9 extends BscsStorageMedium {

    public BscsStorageMediumV9(BSCSStorageMedium storageMedium) {
        super(storageMedium);
    }

    @Override
    public Long getId() {
        // SM_ID
        return storageMedium.getId();
    }

}
