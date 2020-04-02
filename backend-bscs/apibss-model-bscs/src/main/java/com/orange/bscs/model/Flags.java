package com.orange.bscs.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Permet de stocker une combinaison de valeurs d'énumeration.
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class Flags<E extends Enum<E>> {

    private int mask;
    private Class<E> clazz;

    /**
     * <p>Constructor for Flags.</p>
     *
     * @param clazz the {@link java.lang.Class} of the Enumeration.
     */
    public Flags(Class<E> clazz) {
        this.clazz = clazz;
    }

    /**
     * <p>add.</p>
     *
     * @param value a E value.
     * @return a {@link com.orange.bscs.model.Flags}.
     */
    public Flags<E> add(E value) {
        if (null == value) {
            return null;
        }

        int ordinal = value.ordinal();
        int val = 0;
        if (0 == ordinal) {
            val = 1;
        } else {
            val = (int) Math.pow(2, ordinal);
        }
        mask = mask | val;

        return this;
    }

    /** add value only if Boolean is not null and true*/ 
    public  Flags<E> addIf(E value, Boolean flagToCheck) {
        if(null!=flagToCheck && flagToCheck){
            add(value);
        }
        
        return this;
    }
    
    public  Flags<E> remove(E value) {
        int ordinal = value.ordinal();
        int val = 0;
        if (0 == ordinal) {
            val = 1;
        } else {
            val = (int) Math.pow(2, ordinal);
        }
        
        mask = mask ^ val;
        
        return this;
    }    
    
    /**
     * <p>contains.</p>
     *
     * @param value a E value.
     * @return a boolean.
     */
    public boolean contains(E value) {
        if (null == value) {
            return false;
        }

        int ordinal = value.ordinal();
        int val = 0;
        if (0 == ordinal) {
            val = 1;
        } else {
            val = (int) Math.pow(2, ordinal);
        }
        return val == (mask & val);
    }

    /**
     * <p>isEmpty.</p>
     *
     * @return true if flag contains no value (internal mask=0).
     */
    public boolean isEmpty() {
        return 0 == mask;
    }

    /**
     * <p>values.</p>
     *
     * @return a {@link java.util.Collection} of values contains in this flag.
     */
    public Collection<E> values() {
        List<E> result = new ArrayList<E>();
        if (0 == mask || null == clazz) {
            return result;
        }
        for (E en : clazz.getEnumConstants()) {
            if (contains(en)) {
                result.add(en);
            }
        }
        return result;
    }





}
