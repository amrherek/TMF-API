package com.orange.bscs.model;

/**
 * List of Caches used in this project. ehcache should declare a cache for each
 * entry.
 *
 * Declare each Cache in :
 * <ul>
 * <li>api-config / ehcache.xml</li>
 * <li>api-config / spring/apibscs-cache.xml</li>
 * </ul>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public final class Caches {

    /** hide default constructor for utility classes */
    private Caches() {
    }

    /**
     * All cms commands in ReferentialDAOImpl without parameters are store in
     * this cache
     */
    public static final String REFERENTIAL = "REFERENTIAL";

    /** Cache BSCSService return by SERVICES.READ(SNCODE,SNCODE_PUB) */
    public static final String REF_SERVICES_BY_IDS = "REF_SERVICES_BY_IDS";

    /**
     * Cache BSCSService return by SERVICES.READ(SPCODE, SPCODE_PUB,
     * SNCODE,SNCODE_PUB, RPCODE, RPCODE_PUB, RP_VSCODE)
     */
    public static final String REF_SERVICES = "REF_SERVICES";

    /**
     * Cache List<BSCSParameter> return by SERVICE_PARAMETERS.READ (SCCODEPUB,
     * SNCODEPUB)
     */
    public static final String REF_SERVICEPARAMS_BY_SERVICE = "REF_SERVICEPARAMS_BY_SERVICE";

    /** ReferentialsService.getFreeUnitPackage */
    public static final String FREEUNIT_PACKAGE = "SVC_FREEUNIT_PACKAGE";

    /** ReferentialsService.getFreeUnitPackageElements */
    public static final String FREEUNIT_PACKAGE_ELEMENTS = "SVC_FREEUNIT_PACKAGE_ELEMENTS";

    public static final String REF_ALLOWEDSERVICES = "REF_ALLOWEDSERVICES";

}
