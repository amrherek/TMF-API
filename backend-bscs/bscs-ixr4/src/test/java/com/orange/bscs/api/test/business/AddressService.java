package com.orange.bscs.api.test.business;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.test.model.BSCSAddressTst;

public class AddressService {


    public String findNomPrenom(long customerId) throws Exception {

        BSCSAddressTst input = new BSCSAddressTst();
        input.setCustomerId(customerId);

        BSCSAddressTst output = ConnectionHolder.getCurrentConnection().execute("ADDRESS.READ", input, BSCSAddressTst.class);
        String nomPrenom = null;
        if (null != output) {
            nomPrenom = output.getFirstName() + " " + output.getLastName().toUpperCase();
        }
        return nomPrenom;
    }
}
