package com.orange.bscs.model.resource;

/**
 *
 * @author IT&Labs
 * @version $Id: $
 */
public enum EnumDirectoryNumberStatus {

    // Import/Reserve
    UNDEFINED(null, 'i', null, null),

    // No manual action possible
    ACTIVATED('a'),

    // Recyclage/Reserve
    AVAILABLE('f', 'r', null, null),

    // No manual action possible (unless Spec : 'l' => Failed, test :
    // 'f'=>Avalaible)
    RESERVED('r', null, null, 'f'),

    // Export/Release number
    SUSPENDED('d', null, 'o', null),

    // Re-Import/Reserve
    PORTED_OUT('o', 'f', null, null),

    // Restitute/Release or Cancel
    PORTED_IN('i', null, 't', 's'),

    // Import/Reserve number
    SNAPPED_BACK_OUT('s', 'i', null, null),

    // Import/Reserve
    QUARANTINE('t', 'i', null, null),

    // No manual action
    PORTED_IN_AND_ACTIVATED('c'),

    // Detected in codeV1 Re-Import/Reserve
    FAILED('l', 'i', null, null);
    // FAILED('l','r',null,null); in specif 'r' possible

    private Character status;
    private Character validNextReleaseStatus;
    private Character validNextReserveStatus;
    private Character validNextCancelStatus;

    private EnumDirectoryNumberStatus(Character c) {
        status = c;
    }

    private EnumDirectoryNumberStatus(Character actualStatus, Character nextReserveStatus, Character nextReleaseStatus,
            Character nextCancelStatus) {
        status = actualStatus;
        validNextReserveStatus = nextReserveStatus;
        validNextReleaseStatus = nextReleaseStatus;
        validNextCancelStatus = nextCancelStatus;
    }

    /**
     * Check if DirectoryNumber can by Release depending on is actual status.
     *
     * @param actualStatus a {@link com.orange.bscs.model.resource.EnumDirectoryNumberStatus} object.
     * @return true if PORTED_IN or SUSPEND
     */
    public static boolean isAcceptableToReleaseStatus(EnumDirectoryNumberStatus actualStatus) {
        return null != actualStatus.validNextReleaseStatus;
    }

    /**
     * Check if DirectoryNumber can by Reserve depending on is actual status.
     *
     * @param actualStatus a {@link com.orange.bscs.model.resource.EnumDirectoryNumberStatus} object.
     * @return true if actualStatus is AVAILABLE, UNDEFINED, QUARANTINE,
     *         PORTED_OUT or SNAPPED_BACK_OUT
     */
    public static boolean isAcceptableToReserveAction(EnumDirectoryNumberStatus actualStatus) {
        return null != actualStatus.validNextReserveStatus;
    }

    /**
     * Check if DirectoryNumber can by Cancelled depending on is actual status.
     *
     * @param actualStatus a {@link com.orange.bscs.model.resource.EnumDirectoryNumberStatus} object.
     * @return true if actual status is PORTED_IN
     */
    public static boolean isAcceptableToCancelAction(EnumDirectoryNumberStatus actualStatus) {
        return null != actualStatus.validNextCancelStatus;
    }

    public boolean isAcceptableToChange(EnumDirectoryNumberStatus futurStatus){
        if(null!=futurStatus){
            if(null!=validNextReserveStatus && validNextReserveStatus.equals(futurStatus.status)){
                return true;
            }
            if(null!=validNextReleaseStatus && validNextReleaseStatus.equals(futurStatus.status)){
                return true;
            }
            if(null!=validNextCancelStatus && validNextCancelStatus.equals(futurStatus.status)){
                return true;
            }
        }
        return false;
    }

    public static boolean isAcceptableToChange(EnumDirectoryNumberStatus actualStatus, EnumDirectoryNumberStatus futurStatus){
        if(null!=actualStatus){
            return actualStatus.isAcceptableToChange(futurStatus);
        }
        return false;
    }
    

    /**
     * <p>parseChar.</p>
     *
     * @param code a {@link java.lang.Character} object.
     * @return a {@link com.orange.bscs.model.resource.EnumDirectoryNumberStatus} object.
     */
    public static EnumDirectoryNumberStatus parseChar(Character code) {
        EnumDirectoryNumberStatus result = UNDEFINED;
        if (null != code) {
            for (EnumDirectoryNumberStatus status : EnumDirectoryNumberStatus.values()) {
                if (code.equals(status.status)) {
                    result = status;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * <p>getCode.</p>
     *
     * @return a {@link java.lang.Character} object.
     */
    public Character getCode() {
        return status;
    }

    /**
     * <p>nextReservedStatus.</p>
     *
     * @return a {@link com.orange.bscs.model.resource.EnumDirectoryNumberStatus} object.
     */
    public EnumDirectoryNumberStatus nextReservedStatus() {
        return parseChar(validNextReserveStatus);
    }

    /**
     * <p>nextCancelledStatus.</p>
     *
     * @return a {@link com.orange.bscs.model.resource.EnumDirectoryNumberStatus} object.
     */
    public EnumDirectoryNumberStatus nextCancelledStatus() {
        return parseChar(validNextCancelStatus);
    }

    /**
     * <p>nextReleasedStatus.</p>
     *
     * @return a {@link com.orange.bscs.model.resource.EnumDirectoryNumberStatus} object.
     */
    public EnumDirectoryNumberStatus nextReleasedStatus() {
        return parseChar(validNextReleaseStatus);
    }

}
