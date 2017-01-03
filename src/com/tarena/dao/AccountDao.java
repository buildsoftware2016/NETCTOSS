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
	Integer findByIdcard_no(String a);//根据身份证号码查找account_id
	void update(Account account);
	void delete(int id);
	void pauseAccount(String id);//暂停后，记载暂停时间
	void startAccount(String id);//重新开通后，删除暂停时间
	void deleAccount(String id);//只是标示为删除，并没有正真删掉数据库中数据;删除后，记载删除时间
	Account findByIdcardNo(String a);
	void pauseByAccount(String id);//暂停下属所有的业务账号
	void deleByAccount(String id);//删除下属所有的业务账号
}
