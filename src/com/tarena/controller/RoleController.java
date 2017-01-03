package com.tarena.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tarena.dao.RoleDao;
import com.tarena.entity.Module;
import com.tarena.entity.Role;
import com.tarena.entity.RoleModule;
import com.tarena.entity.page.RolePage;

@Controller
@RequestMapping("/role")
@SessionAttributes("rolePage")
public class RoleController {
	@Resource
	RoleDao roleDao;
	
	@RequestMapping("/findRole.do")
	public String findRole(Model model,RolePage page){
		int rows=roleDao.rows();
		page.setRows(rows);
		List<Role> list=roleDao.findAll(page);
		model.addAttribute("roles", list);
		model.addAttribute("rolePage", page);
		return "role/role_list";
	}
	
	@RequestMapping("/toAddRole.do")
	public String toAddRole(Model model){
		List<Module> list=roleDao.finAllModules();
		model.addAttribute("modules", list);
		return "role/add_role";
		
	}
	
	@RequestMapping("/checkRoleName.do")
	@ResponseBody
	//检查角色名是否重复
	public boolean checkRoleName(String name){
		Role role=roleDao.findRoleByName(name);
		if(role==null){
			//没查到，说明没重复，校验通过
			return true;
		}
		return false;
	}
	
	@RequestMapping("/addRole.do")
	public String addRole(Role role){
		//修改role_info表
		roleDao.addRole(role);
		//修改role_info表
		Integer role_id=role.getRole_id();
		List<Integer> moduleIds=role.getModuleIds();
		for(Integer module_id:moduleIds){
			RoleModule rm=new RoleModule();
			rm.setRole_id(role_id);
			rm.setModule_id(module_id);
			roleDao.saveRoleModule(rm);
		}
		
		return "redirect:findRole.do";
	}
	
	@RequestMapping("/toModiRole.do")
	public String toModiRole(Model model,Integer id){
		//用于生成checkbox选项
		List<Module> list=roleDao.finAllModules();
		model.addAttribute("modules", list);
		//查询角色信息
		Role role=roleDao.findById(id);
		model.addAttribute("role",role);
		List<Module> r_modules=role.getModules();
		model.addAttribute("r_modules", r_modules);
		return "role/modi_role";	
	}
	
	@RequestMapping("/checkRoleName2.do")
	@ResponseBody
	//检查角色名是否重复
	//重写这个方法是因为，角色名不改变的情况下点击提交，会提示角色重复
	public boolean checkRoleName2(String name,Integer role_id){
		Role role1=roleDao.findRoleByName(name);
		Role role2=roleDao.findById(role_id);
		//改动之前name2，改动之后name
		String name2=role2.getName();
		
		if(!name.equals(name2) && role1!=null){
			//说明有重复
			return false;
		}
		return true;
	}
	
	@RequestMapping("/modiRole.do")
	public String modiRole(Role role){
		//首先删除中间表的有关信息		
		roleDao.deleModByRole(role);
		
		//更新role_info表
		roleDao.modiRole(role);
		//修改role_info表
		List<Integer> moduleIds=role.getModuleIds();
		for(Integer module_id:moduleIds){
			RoleModule rm=new RoleModule();
			rm.setRole_id(role.getRole_id());
			rm.setModule_id(module_id);
			roleDao.saveRoleModule(rm);
		}
		
		return "redirect:findRole.do";
	}
	
	//delete role
	@RequestMapping("/deleRole.do")
	public String deleRole(Integer id){
		//首先删除中间表的有关信息		
		roleDao.deleModByRoId(id);
		//删除role_info表有关信息
		roleDao.deleRole(id);
		
		return "redirect:findRole.do";
	}
}








