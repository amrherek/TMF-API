package com.orange.mea.apisi.security.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Class of the mapping the table roles
 * 
 * @author ihab.bensouda
 *
 */

@Entity(name = "roles")
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue
    private Integer roleId;

	private String roleName;

	@OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "roleId") }, inverseJoinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "userId") })
	private Set<User> userRoles;

	/**
	 * 
	 * @return
	 */
	public Set<User> getUserRoles() {
		return userRoles;
	}

	/**
	 * 
	 * @param userRoles
	 */
	public void setUserRoles(Set<User> userRoles) {
		this.userRoles = userRoles;
	}

	/**
	 * @return the roleId
	 */
    public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
    public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
