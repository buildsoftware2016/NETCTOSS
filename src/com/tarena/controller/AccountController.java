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
		//业务要求：创建即启用，状态为开通;记载创建时间
		account.setStatus("0");
		account.setCreate_date(new Timestamp(System.currentTimeMillis()));
		
		//根据推荐人身份证获取推荐人账号名
		int recommender_id=accountDao.findByIdcard_no(recommender_idcard_no);
		account.setRecommender_id(recommender_id);
		
		accountDao.addAccount(account);
		return "redirect:findAccount.do";
	}
	
	@RequestMapping("/toModiAccount.do")
	public String toModi(Model model,String account_id){
		Account account=accountDao.findById(Integer.parseInt(account_id));
		model.addAttribute("account", account);
		
		//获取推荐人身份证号码
		int recommender_id=account.getRecommender_id();
		Account account2=accountDao.findById(recommender_id);
		String recommender_idcard_no=account2.getIdcard_no();
		model.addAttribute("recommender_idcard_no", recommender_idcard_no);
		
		return "account/modi_account";
	}
	
	@RequestMapping("/modiAccount.do")
	public String modi(Account account,String recommender_idcard_no){
		//根据推荐人身份证获取推荐人账号名
		int recommender_id=accountDao.findByIdcard_no(recommender_idcard_no);
		account.setRecommender_id(recommender_id);
		
		accountDao.update(account);
		return "redirect:findAccount.do";
	}
	
	@RequestMapping("/pauseAccount.do")
	public String pauseAccount(String id){
		accountDao.pauseAccount(id);
		
		//暂停下属所有的业务账号,注意不能包含删除和暂停状态下的业务账号
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
		
		//删除下属所有的业务账号,注意不包含删除状态下的业务账号
		accountDao.deleByAccount(id);
		
		return "redirect:findAccount.do";
	}
}
