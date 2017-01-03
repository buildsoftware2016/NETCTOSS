package com.tarena.dao;

import java.util.List;

import com.tarena.annotation.MybatisDao;
import com.tarena.entity.Module;
import com.tarena.entity.Role;
import com.tarena.entity.RoleModule;
import com.tarena.entity.page.RolePage;

@MybatisDao
public interface RoleDao {
	List<Role> findAll(RolePage page);
	int rows();
	List<Module> finAllModules();
	Role findRoleByName(String name);
	void addRole(Role role);
	void saveRoleModule(RoleModule rm);
	Role findById(Integer role_id);
	void deleModByRoId(Integer id);
	void modiRole(Role role);
	void deleRole(Integer id);
	void deleModByRole(Role role);
}
