package com.tarena.entity;

import java.util.List;

public class Role {
	
	private Integer role_id;
	private String name;
	//关联属性，用于封装对应的一组模块
	private List<Module> modules;
	
	List<Integer> moduleIds;
	
	public List<Integer> getModuleIds() {
		return moduleIds;
	}
	public void setModuleIds(List<Integer> moduleIds) {
		this.moduleIds = moduleIds;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer roleId) {
		role_id = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
