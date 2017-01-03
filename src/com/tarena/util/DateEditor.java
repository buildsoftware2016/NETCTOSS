package com.tarena.util;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *	���ڴ����������Խ����е������ַ���ת��Ϊ
 *	java.sql.Date
 */
public class DateEditor 
	extends PropertyEditorSupport {
	
	//Ĭ�ϵ����ڸ�ʽ
	private String pattern = "yyyy-MM-dd";
	
	/**
	 * Ĭ�Ϲ�����
	 */
	public DateEditor() {
		
	}
	
	/**
	 * �вι�����������Ϊ���������ڸ�ʽ
	 */
	public DateEditor(String pattern) {
		this.pattern = pattern;
	}

	/* 
	 * ��������������ַ�����ת����java.sql.Date��
	 * ��ע���������
	 */
	@Override
	public void setAsText(String text) 
			throws IllegalArgumentException {
		if(text == null || text.length() == 0) {
			//�����������Ϊnull����ֱ�ӽ�nullע�������
			//�˴��б���Ĭ�ϵ����ڴ�������Ĭ�ϵĴ�����
			//���ֲ���Ϊnullʱ�ᱨ��
			setValue(null);
		} else {
			//�������ַ�����ʽ��Ϊ����ָ����ʽ��java.sql.Date
			//�˴���Ĭ�ϵĴ�����һ��
			SimpleDateFormat sf = 
				new SimpleDateFormat(this.pattern);
			String dateStr = 
				sf.format(Date.valueOf(text));
			setValue(Date.valueOf(dateStr));
		}
	}

}
