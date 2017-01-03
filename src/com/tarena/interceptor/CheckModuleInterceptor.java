package com.tarena.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tarena.entity.Module;

public class CheckModuleInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	//验证是否有权限访问
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2) throws Exception {
		HttpSession session=req.getSession();
		List<Module> modules=(List<Module>) session.getAttribute("userModules");
		int currentModule=(Integer) session.getAttribute("currentModule");
		for(Module m:modules){
			if(m.getModule_id()==currentModule){
				return true;
			}						
		}
		res.sendRedirect(req.getContextPath()+"/login/nopower.do");
		return false;
	}

}
