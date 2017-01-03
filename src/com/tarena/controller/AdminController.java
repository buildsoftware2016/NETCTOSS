package com.tarena.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tarena.dao.AdminDao;
import com.tarena.dao.RoleDao;
import com.tarena.entity.Admin;
import com.tarena.entity.Module;
import com.tarena.entity.Role;
import com.tarena.entity.page.AdminPage;

@Controller
@RequestMapping("/admin")
@SessionAttributes("adminPage")
public class AdminController extends BaseController {
	//默认重置密码
	private final String REST_PWD="123abc";
	
	@Resource
	AdminDao adminDao;
	
	@Resource
	RoleDao roleDao;
	
	@RequestMapping("/findAdmin.do")
	public String findAdmin(Model model,AdminPage page){
		List<Module> list=roleDao.finAllModules();
		model.addAttribute("modules", list);
		
		int rows=adminDao.rows(page);
		page.setRows(rows);
		model.addAttribute("adminPage", page);
		List<Admin> admins=adminDao.findAll(page);
		model.addAttribute("admins", admins);
		
		return "admin/admin_list";
	}
	
	@RequestMapping("/resetPwd.do")
	@ResponseBody
	public boolean resetPwd(String ids){
		List<String> idList=buildIdList(ids);
		
		for(String admin_id:idList){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("id", admin_id);
			map.put("pwd",REST_PWD);
			adminDao.resetPwdById(map);
		}
		return true;
	}
	
	private List<String> buildIdList(String ids) {
		String[] idArray = ids.split(",");
		List<String> idList = new ArrayList<String>();
		for(String id : idArray) {
			idList.add(id);
		}
		return idList;
	}
	
	@RequestMapping("/toAddAdmin.do")
	public String toAddAdmin(Model model){
		List<Role> roles=adminDao.findAllRoles();
		model.addAttribute("roles", roles);
		return "admin/add_admin";
	}
	
	//检查管理员账号是否重复
	@RequestMapping("/checkCode.do")
	@ResponseBody
	public boolean checkCode(String code){
		Admin admin=adminDao.findByCode(code);
		if(admin==null){
			return true;
		}
		return false;
	}
	
	@RequestMapping("/addAdmin.do")
	public String addAdmin(Admin admin,Integer[] roleIds){
		Timestamp enrolldate=new Timestamp(System.currentTimeMillis());
		admin.setEnrolldate(enrolldate);
		//先增加admin_info表的内容，以便获得admin_id
		adminDao.addAdmin(admin);
		
		//接着增加admin_role表的内容
		Integer admin_id=admin.getAdmin_id();
		for(Integer role_id:roleIds){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("admin_id", admin_id);
			map.put("role_id", role_id);
			adminDao.addAdminRole(map);
		}		
		return "redirect:findAdmin.do";
	}
	
	@RequestMapping("/toModiAdmin.do")
	public String toModiAdmin(Model model,String admin_code){
		//角色选项
		List<Role> roles=adminDao.findAllRoles();
		model.addAttribute("roles", roles);
		
		Admin admin=adminDao.findByCode(admin_code);
		model.addAttribute("admin", admin);
		
		Integer admin_id=admin.getAdmin_id();
		List<Map<String,Integer>> list=adminDao.getRoleIdsByAdminId(admin_id);
		model.addAttribute("roleIds", list);
		
		return "admin/modi_admin";
	}
	
	@RequestMapping("/modiAdmin.do")
	public String modiAdmin(Admin admin,Integer[] roleIds){
		//修改admin_info表的内容
		adminDao.modiAdmin(admin);
		
		//先删除admin_role表的相关内容
		Integer admin_id=admin.getAdmin_id();
		adminDao.deleAdminRoleById(admin_id);
		//接着增加admin_role表的内容
		for(Integer role_id:roleIds){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("admin_id", admin_id);
			map.put("role_id", role_id);
			adminDao.addAdminRole(map);
		}		
		return "redirect:findAdmin.do";
	}
	
	@RequestMapping("/deleAdmin.do")
	public String deleAdmin(Integer id){
		//删除admin_role表的内容
		adminDao.deleAdminRoleById(id);
		//删除admin_info表的内容
		adminDao.deleAdminById(id);
		
		return "redirect:findAdmin.do";
	}
}
