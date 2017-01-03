package com.tarena.util;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *	日期处理器，可以将表单中的日期字符串转换为
 *	java.sql.Date
 */
public class DateEditor 
	extends PropertyEditorSupport {
	
	//默认的日期格式
	private String pattern = "yyyy-MM-dd";
	
	/**
	 * 默认构造器
	 */
	public DateEditor() {
		
	}
	
	/**
	 * 有参构造器，参数为其他的日期格式
	 */
	public DateEditor(String pattern) {
		this.pattern = pattern;
	}

	/* 
	 * 将表单传入的日期字符串，转换成java.sql.Date，
	 * 并注入给参数。
	 */
	@Override
	public void setAsText(String text) 
			throws IllegalArgumentException {
		if(text == null || text.length() == 0) {
			//如果传入日期为null，则直接将null注入给参数
			//此处有别于默认的日期处理器，默认的处理器
			//发现参数为null时会报错。
			setValue(null);
		} else {
			//将日期字符串格式化为具有指定格式的java.sql.Date
			//此处与默认的处理器一样
			SimpleDateFormat sf = 
				new SimpleDateFormat(this.pattern);
			String dateStr = 
				sf.format(Date.valueOf(text));
			setValue(Date.valueOf(dateStr));
		}
	}

}
