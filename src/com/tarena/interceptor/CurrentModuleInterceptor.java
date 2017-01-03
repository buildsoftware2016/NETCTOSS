package com.tarena.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CurrentModuleInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	//��ȡ��ǰҪ�����ĸ�ģ��
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2) throws Exception {
		//��ȡ��ǰURL
		String url=req.getRequestURL().toString();
		
		//����URL���ó���ǰ���ʵ�ģ��
		int currentModule = 0; //Ĭ��Ϊϵͳ��ҳ
		//��Ҫ����ģ���module_id��ֵ��currentModule
		if(url.contains("role")) {
			currentModule = 1;
		} else if (url.contains("admin")) {
			currentModule = 2;
		} else if (url.contains("cost")) {
			currentModule = 3;
		} else if (url.contains("account")) {
			currentModule = 4;
		} else if (url.contains("service")) {
			currentModule = 5;
		}
		
		HttpSession session=req.getSession();
		session.setAttribute("currentModule", currentModule);
		
		return true;
	}

}
