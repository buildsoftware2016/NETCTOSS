package com.tarena.entity;

import java.sql.Timestamp;
import java.util.List;

public class Admin {
	private Integer admin_id;
	private String admin_code;
	private String password;
	private String name;
	private String telephone;
	private String email;
	private Timestamp enrolldate;
	//每个admin对应的所有角色
	private List<Role> roles;
	//admin_list页面中选中的checkbox
	List<Integer> adminIds;
	
	public List<Integer> getAdminIds() {
		return adminIds;
	}
	public void setAdminIds(List<Integer> adminIds) {
		this.adminIds = adminIds;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_code() {
		return admin_code;
	}
	public void setAdmin_code(String admin_code) {
		this.admin_code = admin_code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getEnrolldate() {
		return enrolldate;
	}
	public void setEnrolldate(Timestamp enrolldate) {
		this.enrolldate = enrolldate;
	}
	
}
