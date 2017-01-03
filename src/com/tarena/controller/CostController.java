package com.tarena.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tarena.dao.CostDao;
import com.tarena.entity.Cost;
import com.tarena.entity.page.CostPage;

@Controller
@RequestMapping("/cost")
@SessionAttributes("costPage")//将指定名称的对象用session来管理及传值
public class CostController {
	@Resource
	private CostDao costDao;
	
	@RequestMapping("/findCost.do")
	public String find(Model model,CostPage page) {
		int rows=costDao.rows();
		page.setRows(rows);
		List<Cost> list=costDao.findAll(page);
		model.addAttribute("costs", list);
		model.addAttribute("costPage", page);
		return "cost/cost_list";
	}
	
	@RequestMapping("/toAddCost.do")
	public String toAddCost() {
		return "cost/add_cost";
	}
	
	@RequestMapping("/addCost.do")
	public void add(HttpServletResponse res,Cost cost) throws Exception {
		//业务要求，新增时默认状态为1(表示停用)，创建时间为当前时间
		cost.setStatus("1");
		cost.setCreatime(new Timestamp(System.currentTimeMillis()));
		
		costDao.addCost(cost);
		res.sendRedirect("findCost.do");
	}
	
	@RequestMapping("/toModiCost.do")
	public String toModi(Model model,String cost_id){
		Cost cost=costDao.findById(Integer.parseInt(cost_id));
		model.addAttribute("cost", cost);
		return "cost/modi_cost";
	}
	
	@RequestMapping("/modiCost.do")
	public String modi(Cost cost){
		costDao.update(cost);
		return "redirect:findCost.do";
	}
	
	@RequestMapping("/deleCost.do")
	public String dele(String cost_id){
		costDao.delete(Integer.parseInt(cost_id));
		return "redirect:findCost.do";
	}
	
	@RequestMapping("/startCost.do")
	public String startCost(String id){
		costDao.startCost(id);
		return "redirect:findCost.do";
	}
}
