package com.orange.bscs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.cms.servicelayeradapter.CommonDomainServiceAdapterI;
import com.orange.bscs.model.BscsCountry;
import com.orange.bscs.model.factory.BscsObjectFactory;

public abstract class BscsCommonDomainServiceAdapter {

    @Autowired
    protected CommonDomainServiceAdapterI commonDomainServiceAdapter;

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    /**
     * execute COUNTRIES.READ
     * 
     * @return
     */
    public List<BscsCountry> getCountries() {
        List<BSCSModel> countries = commonDomainServiceAdapter.countriesRead();
        List<BscsCountry> res = new ArrayList<>();
        for (BSCSModel country : countries) {
            res.add(bscsObjectFactory.createBscsCountry(country));
        }
        return res;
    }

}
