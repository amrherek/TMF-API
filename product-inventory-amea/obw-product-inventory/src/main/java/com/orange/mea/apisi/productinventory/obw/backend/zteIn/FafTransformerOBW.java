package com.orange.mea.apisi.productinventory.obw.backend.zteIn;

import org.springframework.stereotype.Component;

import com.orange.apibss.productInventory.model.Product;
import com.orange.apibss.productInventory.model.ProductCharacteristic;
import com.orange.mea.apisi.obw.webservice.TArrayOfFellowISDNDto;
import com.orange.mea.apisi.obw.webservice.TFellowISDNDto;
import com.orange.mea.apisi.productinventory.constants.ProductInventoryConstants;

@Component
public class FafTransformerOBW {

    /**
     * add faf numbers to faf product from BSCS
     * 
     * @param fafProduct
     * @param fafNumbers
     */
    public void transform(Product fafProduct, TArrayOfFellowISDNDto fafNumbers) {
        if (fafNumbers != null && fafNumbers.getFellowISDNDto() != null && !fafNumbers.getFellowISDNDto().isEmpty()) {
            // change product spec id
            fafProduct.getProductSpecification().setId(ProductInventoryConstants.FAF);

            for (TFellowISDNDto faf : fafNumbers.getFellowISDNDto()) {
                // fill faf numbers
                ProductCharacteristic characteristic = new ProductCharacteristic();
                characteristic.setName("faf");
                characteristic.setValue(faf.getFellowISDN());
                fafProduct.addProductCharacteristicItem(characteristic);
            }
        }
    }

}
