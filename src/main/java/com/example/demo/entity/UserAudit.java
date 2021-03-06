package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Rajashekhar badad
 * @version 1.0
 * @since 16-06-2020
 */
@Entity
@Table(name = "USER_AUDIT")
public class UserAudit implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	private String action;
	private Date actionPerfomedOn;
	private String userId;
	public UserAudit() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public Date getActionPerfomedOn() {
		return actionPerfomedOn;
	}
	public void setActionPerfomedOn(Date actionPerfomedOn) {
		this.actionPerfomedOn = actionPerfomedOn;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "UserAudit [id=" + id + ", action=" + action + ", actionPerfomedDate=" + actionPerfomedOn + ", userId="
				+ userId + "]";
	}
}
