package com.tarena.dao;

import java.util.List;
import java.util.Map;

import com.tarena.annotation.MybatisDao;
import com.tarena.entity.Admin;
import com.tarena.entity.Module;
import com.tarena.entity.Role;
import com.tarena.entity.page.AdminPage;

@MybatisDao
public interface AdminDao {

	List<Admin> findAll(AdminPage page);

	int rows(AdminPage page);

	void resetPwdById(Map map);

	List<Role> findAllRoles();

	Admin findByCode(String code);

	void addAdmin(Admin admin);

	void addAdminRole(Map<String, Object> map);

	List<Map<String,Integer>> getRoleIdsByAdminId(Integer admin_id);

	void deleAdminRoleById(Integer admin_id);

	void modiAdmin(Admin admin);

	void deleAdminById(Integer id);

	List<Module> findModuleById(Integer admin_id);

}
