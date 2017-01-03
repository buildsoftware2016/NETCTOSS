package com.tarena.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tarena.entity.Admin;

public class CheckLoginInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	//在Controller业务方法执行前调用
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2) throws Exception {
		HttpSession session=req.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		if(admin==null){
			//req.getRequestDispatcher("/login/toLogin.do").forward(req, res);
			res.sendRedirect(req.getContextPath()+"/login/toLogin.do");
			return false;
		}		
		return true;
	}

}
