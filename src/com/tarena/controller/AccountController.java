package com.tarena.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tarena.dao.AccountDao;
import com.tarena.entity.Account;
import com.tarena.entity.page.AccountPage;


@Controller
@RequestMapping("/account")
@SessionAttributes("accountPage")
public class AccountController extends BaseController {
	@Resource
	private AccountDao accountDao;
	
	@RequestMapping("/findAccount.do")
	public String find(Model model,AccountPage page) {
		int rows=accountDao.rows(page);
		page.setRows(rows);
		List<Account> list=accountDao.findAll(page);
		model.addAttribute("accounts", list);
		model.addAttribute("accountPage", page);
		return "account/account_list";
	}
	
	@RequestMapping("/toAddAccount.do")
	public String toAddAccount() {
		return "account/add_account";
	}
	
	@RequestMapping("/addAccount.do")
	public String addAccount(Account account,String recommender_idcard_no) {
		//ҵ��Ҫ�󣺴��������ã�״̬Ϊ��ͨ;���ش���ʱ��
		account.setStatus("0");
		account.setCreate_date(new Timestamp(System.currentTimeMillis()));
		
		//�����Ƽ������֤��ȡ�Ƽ����˺���
		int recommender_id=accountDao.findByIdcard_no(recommender_idcard_no);
		account.setRecommender_id(recommender_id);
		
		accountDao.addAccount(account);
		return "redirect:findAccount.do";
	}
	
	@RequestMapping("/toModiAccount.do")
	public String toModi(Model model,String account_id){
		Account account=accountDao.findById(Integer.parseInt(account_id));
		model.addAttribute("account", account);
		
		//��ȡ�Ƽ������֤����
		int recommender_id=account.getRecommender_id();
		Account account2=accountDao.findById(recommender_id);
		String recommender_idcard_no=account2.getIdcard_no();
		model.addAttribute("recommender_idcard_no", recommender_idcard_no);
		
		return "account/modi_account";
	}
	
	@RequestMapping("/modiAccount.do")
	public String modi(Account account,String recommender_idcard_no){
		//�����Ƽ������֤��ȡ�Ƽ����˺���
		int recommender_id=accountDao.findByIdcard_no(recommender_idcard_no);
		account.setRecommender_id(recommender_id);
		
		accountDao.update(account);
		return "redirect:findAccount.do";
	}
	
	@RequestMapping("/pauseAccount.do")
	public String pauseAccount(String id){
		accountDao.pauseAccount(id);
		
		//��ͣ�������е�ҵ���˺�,ע�ⲻ�ܰ���ɾ������ͣ״̬�µ�ҵ���˺�
		accountDao.pauseByAccount(id);
		
		return "redirect:findAccount.do";
	}
	
	@RequestMapping("/startAccount.do")
	public String startAccount(String id){
		accountDao.startAccount(id);
		return "redirect:findAccount.do";
	}
	
	@RequestMapping("/deleAccount.do")
	public String deleAccount(String id){
		accountDao.deleAccount(id);
		
		//ɾ���������е�ҵ���˺�,ע�ⲻ����ɾ��״̬�µ�ҵ���˺�
		accountDao.deleByAccount(id);
		
		return "redirect:findAccount.do";
	}
}
