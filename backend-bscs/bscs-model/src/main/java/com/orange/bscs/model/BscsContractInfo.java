package com.orange.bscs.model;

import com.orange.bscs.api.model.BSCSModel;

/**
 * Model used for result of CONTRACT_INFO.READ command and input of
 * CONTRACT_INFO.WRITE
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsContractInfo {

    protected BSCSModel info;

    public BscsContractInfo(BSCSModel info) {
        this.info = info;
    }

    public BscsContractInfo() {
        info = new BSCSModel();
    }

    public BSCSModel getBscsModel() {
        return info;
    }

    public abstract String getText(String number);

    public abstract void setText(String number, String value);

    public abstract void setContractNumericId(Long contractId);

    public abstract Long getContractNumericId();

}
