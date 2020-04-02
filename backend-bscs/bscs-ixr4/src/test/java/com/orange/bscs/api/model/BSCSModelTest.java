package com.orange.bscs.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

import com.orange.bscs.model.factory.SVLObjectFactoryIXR4;

public class BSCSModelTest {

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        AbstractSVLObjectFactory.init(new SVLObjectFactoryIXR4());

        BSCSModel origine = new BSCSModel();
        SVLObjectWrapper origSVL = origine.getSVLObject();
        origine.setStringValue("VAL", "value");

        BSCSModel clone = origine.cloneModel();
        SVLObjectWrapper cloneSVL = clone.getSVLObject();

        assertNotSame("Orgine BSCSModel and Clone BSCSModel", origine, clone);
        assertNotSame("Origine SVLObject and Clone SVLObject", origSVL, cloneSVL);
        assertEquals("Expected value=", "value", clone.getStringValue("VAL"));
    }

}
