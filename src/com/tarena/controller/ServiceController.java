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
		//ע���ж����������˺��Ƿ�ͨ
		String as=serviceDao.getAccountStatus(id);
		if(as.equals("0")){
			//�����˺ſ�ͨ��������ͨҵ���˺�
			serviceDao.start(id);
			result.put("success", true);
			result.put("message", "��ͨ�ɹ�.");
		}else{
			//�����˺�δ��ͨ��������ͨҵ���˺�
			result.put("success", false);
			result.put("message", "���������˺�δ��ͨ�����Բ��ܿ�ͨ��ǰҵ���˺�.");
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
	
	//������֤�����Ƿ��Ѿ�ע���
	@RequestMapping("/searchAccount.do")
	@ResponseBody
	public Map<String,Object> searchAccount(String a){
		Map<String,Object> result=new HashMap<String,Object>();
		Account account=accountDao.findByIdcardNo(a);
		if(account==null){
			//ͨ�������֤����û���ҵ������˺�
			result.put("success", false);
			result.put("msg1","û�д����֤�ţ�������¼��");
			result.put("msg2","û�в鵽���ݣ�������¼��");
		}else{
			result.put("success", true);
			result.put("msg1","�����֤����Ч");
			result.put("msg2","������Ч�����������");
			result.put("account_login_name",account.getLogin_name());
			result.put("account_id",account.getAccount_id() );
		}
		return result;
	}
	
	//�ύ����ҵ���˺ţ�����
	@RequestMapping("/addService.do")
	public String addService(Service service){
		//��������ͨ�����ش���ʱ��
		service.setStatus("0");
		service.setCreate_date(new Timestamp(System.currentTimeMillis()));
		
		serviceDao.add(service);
		return "redirect:findService.do";
	}
	
	//�����޸�ҳ��
	@RequestMapping("/toModiService.do")
	public String toModiService(Model model,String id){
		Service service=serviceDao.findById(id);
		model.addAttribute("service", service);
		
		List<Cost> list=serviceDao.findAllCost();
		model.addAttribute("costs", list);
		
		return "service/modi_service";
	}
	
	/*
	 1���޸ı���ʱ���������������޸�SERVICE�����ǽ��޸ĺ�
  	��COST_ID������ʱ��SERVICE_UPDATE_BAK���С�
  	insert into SERVICE values(
  		service_bak_seq.nextval,
  		#{service_id,jdbcType=INTEGER},
  		#{cost_id,jdbcType=INTEGER}
  	)
  2�����µ�ʱ��PL/SQL����ᰴ�����²���ʹ����ʱ�����ݣ�
  	--�Զ������SERVICE����ÿ���˺ű��·���
  	--����ʱ���м�¼��COST_ID�����µ�SERVICE����ȥ��
  		�п��ܶ���һ��SERVICE_ID�ж��COST_ID����ô
  		�����һ�����µ�SERVICE���С�
  	--ɾ����ȫ������ʱ������
	 */
	//�ύ�޸�����
	@RequestMapping("/modiService.do")
	public String modiService(Service service){
		serviceDao.update(service);
		return "redirect:findService.do";
	}
}
