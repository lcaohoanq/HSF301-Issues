package com.se181513.pe.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Accounts")
public class Account {
	@Id
	@Column(name = "Email",length = 50,nullable =false)
	private String email;

	@Column(name = "Password",length = 50,nullable =false)
	private String password;

	@Column(name = "RoleID",nullable =false)
	private int roleId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Account(String email, String password, int roleId) {
		super();
		this.email = email;
		this.password = password;
		this.roleId = roleId;
	}

	public Account() {
		super();
	}
	
	
}
