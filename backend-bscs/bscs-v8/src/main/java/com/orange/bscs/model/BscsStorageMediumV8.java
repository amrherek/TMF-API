package com.orange.bscs.model;

import com.orange.bscs.model.resource.BSCSStorageMedium;

public class BscsStorageMediumV8 extends BscsStorageMedium {

    public BscsStorageMediumV8(BSCSStorageMedium storageMedium) {
        super(storageMedium);
    }

    @Override
    public Long getId() {
        // SM_ID
        return storageMedium.getId();
    }

}
