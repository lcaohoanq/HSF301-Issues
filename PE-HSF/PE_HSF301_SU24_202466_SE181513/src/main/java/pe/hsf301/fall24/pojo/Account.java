package pe.hsf301.fall24.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Accounts")
public class Account {

	@Id
	@Column(name="Email")
	private String email;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="RoleID")
	private int roleId;
	
	
	
	public Account() {
		// TODO Auto-generated constructor stub
	}



	public String getEmail() {
		return email;
	}



	public String getPassword() {
		return password;
	}



	public Account(String email, String password, int roleId) {
		super();
		this.email = email;
		this.password = password;
		this.roleId = roleId;
	}



	public int getRoleId() {
		return roleId;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}



	@Override
	public String toString() {
		return "Student [email=" + email + ", password=" + password + ", roleId=" + roleId + "]";
	}

}
