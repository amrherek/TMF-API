package com.orange.bscs.service.exception;

/**
 * BSCS exception when an id is unknown
 * 
 * @author jwck2987
 *
 */
public class BscsInvalidIdException extends Exception {


    /**
     * 
     */
    private static final long serialVersionUID = 6254490051180652940L;

    private String id;
    private BscsFieldExceptionEnum name;

    public BscsInvalidIdException(BscsFieldExceptionEnum name, String id, String message) {
        super(message);
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public BscsFieldExceptionEnum getName() {
        return name;
    }

}
