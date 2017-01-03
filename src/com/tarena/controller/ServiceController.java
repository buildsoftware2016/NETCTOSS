package com.tarena.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tarena.dao.AccountDao;
import com.tarena.dao.ServiceDao;
import com.tarena.entity.Account;
import com.tarena.entity.Cost;
import com.tarena.entity.Service;
import com.tarena.entity.page.ServicePage;

@Controller
@RequestMapping("/service")
@SessionAttributes("servicePage")
public class ServiceController extends BaseController {
	@Resource
	private ServiceDao serviceDao;
	
	@Resource
	private AccountDao accountDao;
	
	@RequestMapping("/findService.do")
	public String findService(Model model,ServicePage page){
		int rows=serviceDao.rows();
		page.setRows(rows);
		List<Map<String,Object>> list=serviceDao.findAll(page);
		model.addAttribute("servicePage",page);
		model.addAttribute("services", list);
		return "service/service_list";
	}
	
	@RequestMapping("/startService.do")
	@ResponseBody
	public Map<String,Object> startService(String id){
		Map<String,Object> result=new HashMap<String,Object>();
		//注意判断所属账务账号是否开通
		String as=serviceDao.getAccountStatus(id);
		if(as.equals("0")){
			//账务账号开通，则允许开通业务账号
			serviceDao.start(id);
			result.put("success", true);
			result.put("message", "开通成功.");
		}else{
			//账务账号未开通，不允许开通业务账号
			result.put("success", false);
			result.put("message", "由于账务账号未开通，所以不能开通当前业务账号.");
		}
		return result;
	}
	
	@RequestMapping("/pauseService.do")
	@ResponseBody
	public void pauseService(String id){
		serviceDao.pause(id);	
	}
	
	@RequestMapping("/deleteService.do")
	@ResponseBody
	public void deleteService(String id){
		serviceDao.delete(id);	
	}
	
	@RequestMapping("/toAddService.do")
	public String toAddService(Model model){
		List<Cost> list=serviceDao.findAllCost();
		model.addAttribute("costs", list);
		return "service/add_service";
	}
	
	//检查身份证号码是否已经注册过
	@RequestMapping("/searchAccount.do")
	@ResponseBody
	public Map<String,Object> searchAccount(String a){
		Map<String,Object> result=new HashMap<String,Object>();
		Account account=accountDao.findByIdcardNo(a);
		if(account==null){
			//通过该身份证号码没有找到账务账号
			result.put("success", false);
			result.put("msg1","没有此身份证号，请重新录入");
			result.put("msg2","没有查到数据，请重新录入");
		}else{
			result.put("success", true);
			result.put("msg1","此身份证号有效");
			result.put("msg2","数据有效，请继续操作");
			result.put("account_login_name",account.getLogin_name());
			result.put("account_id",account.getAccount_id() );
		}
		return result;
	}
	
	//提交新增业务账号，保存
	@RequestMapping("/addService.do")
	public String addService(Service service){
		//创建即开通，记载创建时间
		service.setStatus("0");
		service.setCreate_date(new Timestamp(System.currentTimeMillis()));
		
		serviceDao.add(service);
		return "redirect:findService.do";
	}
	
	//访问修改页面
	@RequestMapping("/toModiService.do")
	public String toModiService(Model model,String id){
		Service service=serviceDao.findById(id);
		model.addAttribute("service", service);
		
		List<Cost> list=serviceDao.findAllCost();
		model.addAttribute("costs", list);
		
		return "service/modi_service";
	}
	
	/*
	 1）修改保存时，并不是真正的修改SERVICE表，而是将修改后
  	的COST_ID存入临时表SERVICE_UPDATE_BAK表中。
  	insert into SERVICE values(
  		service_bak_seq.nextval,
  		#{service_id,jdbcType=INTEGER},
  		#{cost_id,jdbcType=INTEGER}
  	)
  2）在月底时，PL/SQL程序会按照如下步骤使用临时表数据：
  	--自动计算出SERVICE表中每个账号本月费用
  	--将临时表中记录的COST_ID，更新到SERVICE表中去，
  		有可能对于一个SERVICE_ID有多个COST_ID，那么
  		将最后一个更新到SERVICE表中。
  	--删除掉全部的临时表数据
	 */
	//提交修改数据
	@RequestMapping("/modiService.do")
	public String modiService(Service service){
		serviceDao.update(service);
		return "redirect:findService.do";
	}
}
