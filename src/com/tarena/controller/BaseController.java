package com.tarena.controller;

import java.sql.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.tarena.util.DateEditor;

/**
 *	Controller的父类，可以用来封装一些公共的业务代码。
 */
public class BaseController {
	
	/**
	 * @InitBinder注解告诉Spring该方法是在请求开始时执行。
	 */
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		//注册自定义的处理器
		//将java.sql.Date类型的处理器改为DateEditor
		binder.registerCustomEditor(
				Date.class, new DateEditor());
    }
	
}
