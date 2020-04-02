package com.orange.bscs.api.model;

import java.security.NoSuchAlgorithmException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orange.bscs.model.factory.SVLObjectFactoryIXR4;


public class BSCSModelLoadTestInstanciation {

    private static final Logger LOG = LoggerFactory.getLogger(BSCSModelLoadTestInstanciation.class);


    @BeforeClass
    public static void initExchange() throws NoSuchAlgorithmException {
        AbstractSVLObjectFactory.init(new SVLObjectFactoryIXR4());
    }

    @Test
    public void newInstance() {

        Long start = System.currentTimeMillis();
        for (int i = 1; i <= 100000; i++) {
            BSCSModel ref = new BSCSModel();
            SVLObjectWrapper svlo = ref.getSVLObject();

            BSCSModel.newInstance(BSCSClass1.class, svlo);

            BSCSModel.newInstance(BSCSClass2.class, svlo);
        }

        long end = System.currentTimeMillis();
        long dureeMs = end - start;
        LOG.info("new BSCSModel(svlo) proceed in {} ms ", dureeMs);


        start = System.currentTimeMillis();
        for (int i = 1; i <= 100000; i++) {
            BSCSModel ref = new BSCSModel();
            SVLObjectWrapper svlo = ref.getSVLObject();

            BSCSModel.newInstance(BSCSClass3.class, svlo);

            BSCSModel.newInstance(BSCSClass4.class, svlo);
        }

        end = System.currentTimeMillis();
        dureeMs = end - start;
        LOG.info("new BSCSModel() proceed in {} ms ", dureeMs);

    }
}

class BSCSClass1 extends BSCSModel {

    public BSCSClass1() {
    }

    public BSCSClass1(SVLObjectWrapper svlo) {
        this.setSVLObject(svlo);
    }
}

class BSCSClass2 extends BSCSModel {
    public BSCSClass2() {
    }

    public BSCSClass2(SVLObjectWrapper svlo) {
        this.setSVLObject(svlo);
    }
}

class BSCSClass3 extends BSCSModel {

}

class BSCSClass4 extends BSCSModel {

}

class BSCSClass5 extends BSCSModel {

}


