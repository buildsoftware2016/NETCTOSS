package com.tarena.dao;

import java.util.List;

import com.tarena.annotation.MybatisDao;
import com.tarena.entity.Account;
import com.tarena.entity.page.AccountPage;

@MybatisDao
public interface AccountDao {
	List<Account> findAll(AccountPage page);
	void addAccount(Account account);
	int rows(AccountPage page);
	Account findById(int id);
	Integer findByIdcard_no(String a);//�������֤�������account_id
	void update(Account account);
	void delete(int id);
	void pauseAccount(String id);//��ͣ�󣬼�����ͣʱ��
	void startAccount(String id);//���¿�ͨ��ɾ����ͣʱ��
	void deleAccount(String id);//ֻ�Ǳ�ʾΪɾ������û������ɾ�����ݿ�������;ɾ���󣬼���ɾ��ʱ��
	Account findByIdcardNo(String a);
	void pauseByAccount(String id);//��ͣ�������е�ҵ���˺�
	void deleByAccount(String id);//ɾ���������е�ҵ���˺�
}
