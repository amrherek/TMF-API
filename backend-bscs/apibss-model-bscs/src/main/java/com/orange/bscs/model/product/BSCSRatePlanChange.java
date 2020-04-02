package com.orange.bscs.model.product;

import java.util.ArrayList;
import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.model.contract.BSCSContractService;

/**
 * Simulation of Rate plan change (result of command )
 * or
 * Result of rate plan change
 * <p>
 * see <pre>{@code
 * PRODUCT_CHANGE_SERVICES.READ
 * PRODUCT.CHANGE
 * }</pre>
 *
 * @author IT&Labs
 */
public class BSCSRatePlanChange extends BSCSModel {


    public List<BSCSRatePlanChangeElement> getListOfDroppedServices() {
        if (null != getSVLObject().getValue(EnumList.LIST_1.toString())) {
            return buildList(EnumList.LIST_1);
        } else {
            return buildList(EnumList.SERVICES_DROPPED);
        }
    }

    public List<BSCSRatePlanChangeElement> getListOfMovedServices() {
        return buildList(EnumList.LIST_2);
    }

    public List<BSCSRatePlanChangeElement> getListOfNewServices() {
        if (null != getSVLObject().getValue(EnumList.LIST_3.toString())) {
            return buildList(EnumList.LIST_3);
        } else {
            return buildList(EnumList.SERVICES_DROPPED);
        }
    }


    private List<BSCSRatePlanChangeElement> buildList(EnumList list) {
        List<BSCSRatePlanChangeElement> result = new ArrayList<BSCSRatePlanChange.BSCSRatePlanChangeElement>();
        List<BSCSModel> cmsList = getListOfBSCSModelValue(list.toString());
        if (!cmsList.isEmpty()) {
            switch (list) {
                case LIST_1:
                case LIST_3:
                    // SP then SN
                    for (BSCSModel sp : cmsList) {
                        Long spCode = sp.getLongValue(Constants.P_SPCODE);
                        String spCodePub = sp.getStringValue(Constants.P_SPCODE_PUB);
                        List<BSCSModel> cmsSubList = sp.getListOfBSCSModelValue(Constants.P_LIST_SN_CODE);
                        for (BSCSModel sv : cmsSubList) {
                            BSCSRatePlanChangeElement elt = new BSCSRatePlanChangeElement();
                            elt.setServicePackageCode(spCode);
                            elt.setServicePackageCodePub(spCodePub);

                            elt.setServiceCode(sv.getLongValue(Constants.P_SNCODE));
                            elt.setServiceCodePub(sv.getStringValue(Constants.P_SNCODE_PUB));

                            result.add(elt);
                        }
                    }

                    break;
                case LIST_2:
                    // SN then SP
                    for (BSCSModel sn : cmsList) {
                        Long snCode = sn.getLongValue(Constants.P_SNCODE);
                        String snCodePub = sn.getStringValue(Constants.P_SNCODE_PUB);
                        List<BSCSModel> cmsSubList = sn.getListOfBSCSModelValue(Constants.P_LIST_SP_CODE);
                        for (BSCSModel sp : cmsSubList) {
                            BSCSRatePlanChangeElement elt = new BSCSRatePlanChangeElement();
                            elt.setServiceCode(snCode);
                            elt.setServiceCodePub(snCodePub);

                            elt.setServicePackageCode(sp.getLongValue(Constants.P_SPCODE));
                            elt.setServicePackageCodePub(sp.getStringValue(Constants.P_SPCODE_PUB));

                            result.add(elt);
                        }
                    }
                    break;
                case SERVICES_ADDED:
                case SERVICES_DROPPED:
                    for (BSCSModel model : cmsList) {
                        BSCSRatePlanChangeElement elt = new BSCSRatePlanChangeElement();
                        BSCSContractService svc = model.as(BSCSContractService.class);

                        elt.setServiceCode(svc.getServiceCode());
                        elt.setServiceCodePub(svc.getServiceCodePub());

                        elt.setServicePackageCode(svc.getServicePackageCode());
                        elt.setServicePackageCodePub(svc.getServicePackagePublicCode());

                        result.add(elt);
                    }
            }

        }
        return result;
    }


    public static class BSCSRatePlanChangeElement {
        private Long servicePackageCode;
        private String servicePackageCodePub;
        private Long serviceCode;
        private String serviceCodePub;

        // Only on List3
        private boolean isCode;

        public Long getServicePackageCode() {
            return this.servicePackageCode;
        }

        public void setServicePackageCode(Long servicePackageCode) {
            this.servicePackageCode = servicePackageCode;
        }

        public String getServicePackageCodePub() {
            return this.servicePackageCodePub;
        }

        public void setServicePackageCodePub(String servicePackageCodePub) {
            this.servicePackageCodePub = servicePackageCodePub;
        }

        public Long getServiceCode() {
            return this.serviceCode;
        }

        public void setServiceCode(Long serviceCode) {
            this.serviceCode = serviceCode;
        }

        public String getServiceCodePub() {
            return this.serviceCodePub;
        }

        public void setServiceCodePub(String serviceCodePub) {
            this.serviceCodePub = serviceCodePub;
        }

        public boolean isCode() {
            return this.isCode;
        }

        public void setCode(boolean isCode) {
            this.isCode = isCode;
        }

        /**
         * return servicePackageCodePub+"/"+serviceCodePub;
         */
        public String getPublicKey() {
            return servicePackageCodePub + "/" + serviceCodePub;
        }
    }

    private enum EnumList {
        LIST_1,
        LIST_2,
        LIST_3,
        SERVICES_ADDED("Services added"),
        SERVICES_DROPPED("Services dropped");

        private String label;

        private EnumList() {
        }

        private EnumList(String lb) {
            label = lb;
        }

        @Override
        public String toString() {
            return null == label ? name() : label;
        }
    }
}
