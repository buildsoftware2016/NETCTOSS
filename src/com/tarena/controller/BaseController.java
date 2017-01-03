package com.tarena.controller;

import java.sql.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.tarena.util.DateEditor;

/**
 *	Controller�ĸ��࣬����������װһЩ������ҵ����롣
 */
public class BaseController {
	
	/**
	 * @InitBinderע�����Spring�÷�����������ʼʱִ�С�
	 */
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		//ע���Զ���Ĵ�����
		//��java.sql.Date���͵Ĵ�������ΪDateEditor
		binder.registerCustomEditor(
				Date.class, new DateEditor());
    }
	
}
