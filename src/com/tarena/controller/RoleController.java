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
	//����ɫ���Ƿ��ظ�
	public boolean checkRoleName(String name){
		Role role=roleDao.findRoleByName(name);
		if(role==null){
			//û�鵽��˵��û�ظ���У��ͨ��
			return true;
		}
		return false;
	}
	
	@RequestMapping("/addRole.do")
	public String addRole(Role role){
		//�޸�role_info��
		roleDao.addRole(role);
		//�޸�role_info��
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
		//��������checkboxѡ��
		List<Module> list=roleDao.finAllModules();
		model.addAttribute("modules", list);
		//��ѯ��ɫ��Ϣ
		Role role=roleDao.findById(id);
		model.addAttribute("role",role);
		List<Module> r_modules=role.getModules();
		model.addAttribute("r_modules", r_modules);
		return "role/modi_role";	
	}
	
	@RequestMapping("/checkRoleName2.do")
	@ResponseBody
	//����ɫ���Ƿ��ظ�
	//��д�����������Ϊ����ɫ�����ı������µ���ύ������ʾ��ɫ�ظ�
	public boolean checkRoleName2(String name,Integer role_id){
		Role role1=roleDao.findRoleByName(name);
		Role role2=roleDao.findById(role_id);
		//�Ķ�֮ǰname2���Ķ�֮��name
		String name2=role2.getName();
		
		if(!name.equals(name2) && role1!=null){
			//˵�����ظ�
			return false;
		}
		return true;
	}
	
	@RequestMapping("/modiRole.do")
	public String modiRole(Role role){
		//����ɾ���м����й���Ϣ		
		roleDao.deleModByRole(role);
		
		//����role_info��
		roleDao.modiRole(role);
		//�޸�role_info��
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
		//����ɾ���м����й���Ϣ		
		roleDao.deleModByRoId(id);
		//ɾ��role_info���й���Ϣ
		roleDao.deleRole(id);
		
		return "redirect:findRole.do";
	}
}








