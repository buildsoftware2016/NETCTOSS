package com.tarena.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.dao.AdminDao;
import com.tarena.entity.Admin;
import com.tarena.entity.Module;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Resource
	AdminDao adminDao;
	
	@RequestMapping("/toLogin.do")
	public String toLogin(){		
		return "main/login";
	}
	
	@RequestMapping("/checkCodePwd.do")
	@ResponseBody
	public Map<String, Object> checkCodePwd(String code,String pwd,
			HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		Admin admin=adminDao.findByCode(code);
		if(admin==null){
			map.put("success", false);
			map.put("msg", "账号不存在");
			return map;
		}
		
		String password=admin.getPassword();			
		if(!pwd.equals(password)){
			map.put("success", false);
			map.put("msg", "密码错误");
			return map;
		}
		
		session.setAttribute("admin", admin);
		//查询当前用户可以访问的模块，放入session
		List<Module> modules=adminDao.findModuleById(admin.getAdmin_id());
		session.setAttribute("userModules", modules);
		
		map.put("success", true);
		return map;
	}
	
	@RequestMapping("/index.do")
	public String index(){		
		return "main/index";
	}
	
	@RequestMapping("/nopower.do")
	public String nopower(){		
		return "main/nopower";
	}
}
