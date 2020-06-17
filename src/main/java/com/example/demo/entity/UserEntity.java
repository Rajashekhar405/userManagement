package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

import org.hibernate.annotations.DynamicUpdate;

/**
 * @author Rajashekhar Badad
 * @version 1.0
 * @since 16-06-2020
 */
@Entity
@Table(name="USER_MANAGEMENT")
@DynamicUpdate(value = true)
public class UserEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer id;
	@Column(name = "USER_ROLE")
	private String userRole;
	@Column(name = "ROLE_ID")
	private String roleId;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "EMAIL")
	@Email
	private String email;
	@Column(name = "MOBILE_NO")
	@Min(value = 10)
	private String mobileNo;
	@Column(name = "ADDRESS")
	private String address;
	
	public UserEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserEntity [userID=" + id + ", userRole=" + userRole + ", roleId=" + roleId + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", mobileNo=" + mobileNo + ", address="
				+ address + "]";
	}
}
