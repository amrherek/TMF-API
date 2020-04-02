/*
 * $$RCSfile: ContractReasonStatus.java,v $$
 * Copyright (c) 2006 Alcatel
 * All rights reserved.
 *
 * @author Jean-Baptiste Binet
 * @version $$Revision: 1.4 $$
 * $$Date: 2012/06/04 07:53:29 $$
 * Last modified by $$Author: livernea $$
 * $$Name:  $$
 * $$Source: /home/BSCS_CVS/standard/bscs/ccl/src/com/alcatel/ccl/common/ContractReasonStatus.java,v $$
 */

package com.orange.bscs.model.contract;

import java.io.Serializable;

/**
 * This class contains constants representing reason for contract status.
 * To be coherent and usable by a Web Service, it needs:
 *  - Empty constructor
 *  - Getter
 *  - Setter
 */
public final class ContractReasonStatus implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8888330646936196464L;

	/** Custom reason status. */
	public final static ContractReasonStatus CUSTOM = new ContractReasonStatus(-1);

	/** Activating. */
	public final static ContractReasonStatus ACTIVATING = new ContractReasonStatus(1);

	/** Suspending. */
	public final static ContractReasonStatus SUSPENDING = new ContractReasonStatus(2);

	/** Deactivating. */
	public final static ContractReasonStatus DEACTIVATING = new ContractReasonStatus(3);

	/** Reactivating. */
	public final static ContractReasonStatus REACTIVATING = new ContractReasonStatus(4);

	/** Replacement. */
	public final static ContractReasonStatus REPLACEMENT = new ContractReasonStatus(5);

	/** On hold. */
	public final static ContractReasonStatus ON_HOLD = new ContractReasonStatus(6);

	/** Interested. */
	public final static ContractReasonStatus INTERESTED = new ContractReasonStatus(7);

	/** Contract reason status value. */
	private Long _value = null;

	public ContractReasonStatus(long value)
	{
		_value = new Long(value);
	}
	
	public ContractReasonStatus(){}

	public Long getValue()
	{
		return _value;
	}
	
	

	@Override
    public String toString()
	{
		return _value.toString();
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_value == null) ? 0 : _value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContractReasonStatus other = (ContractReasonStatus) obj;
        if (_value == null) {
            if (other._value != null)
                return false;
        } else if (!_value.equals(other._value))
            return false;
        return true;
    }

}