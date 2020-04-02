package com.orange.mea.apisi.security.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * Class of the mapping the table users
 * 
 * @author ihab.bensouda
 *
 */
@Entity(name = "users")
@NamedQuery(name = "User.findByPrincipal",
		query = "SELECT u FROM users u WHERE u.login = ?1 AND u.password = ?2 AND u.platform = ?3")
@Table(name = "users")
public class User {

	/**
	 * 
	 */
	@Id
	@GeneratedValue
    private Integer userId;

	/**
	 * login
	 */
	private String login;

	/**
	 * password
	 */
	private String password;
	
	/**
	 * platform
	 */
	private String platform;

	/**
	 * isActive
	 */
    @Type(type = "yes_no")
    private boolean isActive;
	
    /**
     * originSender
     */
    private String originSender;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "userId") }, inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "roleId") })
	
	/**
	 * Role
	 */
    private Set<Role> roles;


	/**
	 * @return the userId
	 */
    public Integer getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
    public void setUserId(Integer userId) {
		this.userId = userId;
	}


	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}


	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}


	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}


	/**
	 * @return the authorizedAccessService
	 */
	public boolean isActive() {
		return isActive;
	}


	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}


	/**
	 * @return the role
	 */
    public Set<Role> getRoles() {
        return roles;
	}


	/**
     * @param roles
     *            the role to set
     */
    public void setRole(Set<Role> roles) {
        this.roles = roles;
	}


    /**
     * @return the origin sender
     */
    public String getOriginSender() {
        return originSender;
    }


    /**
     * @param originSender
     *            the origin sender to set
     */
    public void setOriginSender(String originSender) {
        this.originSender = originSender;
    }
}
