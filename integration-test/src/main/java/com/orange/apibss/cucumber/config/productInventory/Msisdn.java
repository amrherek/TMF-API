package com.orange.apibss.cucumber.config.productInventory;

import com.orange.apibss.productInventory.model.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Properties for MSISDN
 * 
 * @author Thibault Duperron
 *
 */
@ToString
public class Msisdn {
	/**
	 * Msisdn with contracts
	 */
	@Getter
	@Setter
	public String withContracts;

	/**
	 * Msisdn with no contracts
	 */
	@Getter
	@Setter
	public String withoutContracts;

	/**
	 * Msisdn not well formated
	 */
	@Getter
	@Setter
	public String invalid;

	/**
	 * Msisdn of a contract with tutors
	 */
	@Getter
	@Setter
	public String withTutor;
	
	/**
     * Msisdn of a contract with friends and family numbers
     */
    @Getter
    @Setter
    public String withFaf;
    
    /**
     * Msisdn of a contract without friends and family numbers
     */
    @Getter
    @Setter
    public String withoutFaf;
    
    /**
     * Msisdn of a contract with transfer data
     */
    @Getter
    @Setter
    public String withTransferData;
    
    /**
     * Msisdn of a contract without transfer data
     */
    @Getter
    @Setter
    public String withoutTransferData;
    
    /**
     * Msisdn of a contract with transfer credit
     */
    @Getter
    @Setter
    public String withTransferCredit;
    
    /**
     * Msisdn of a contract without transfer credit
     */
    @Getter
    @Setter
    public String withoutTransferCredit;
    
    /**
     * Msisdn of a contract with data bundle
     */
    @Getter
    @Setter
    public String withDataBundle;
    
    /**
     * Msisdn of a contract without data bundle
     */
    @Getter
    @Setter
    public String withoutDataBundle;
}