package com.orange.mea.apisi.security.service;

import org.springframework.security.core.GrantedAuthority;


/**
 * Custom Role class for manage the authorities
 * 
 * @author ihab.bensouda
 * 
 */
public class CustomRole implements GrantedAuthority {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Definition of attribute role
	 */
	private String role = null;

	/**
	 * redefinition for the method  getAuthority()
	 * return role
	 */
	@Override
	public String getAuthority() {
		return role;
	}

	/**
	 *set of the value roleName in role
	 * @param roleName
	 */
	public void setAuthority(String roleName) {
		this.role = roleName;
	}

}

