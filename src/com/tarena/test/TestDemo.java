package com.tarena.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.dao.AccountDao;
import com.tarena.dao.AdminDao;
import com.tarena.dao.CostDao;
import com.tarena.entity.Admin;
import com.tarena.entity.Cost;
import com.tarena.entity.page.AccountPage;
import com.tarena.entity.page.AdminPage;

public class TestDemo {
	//@Test
	public void testConn() throws SQLException{
		String cfg="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(cfg);
		BasicDataSource ds=ac.getBean("ds", BasicDataSource.class);
		Connection conn=ds.getConnection();
		System.out.println(conn);
		conn.close();
	}
	
	//@Test
	public void testFindAll(){
		String cfg="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(cfg);
		//CostDao costDao=ac.getBean(CostDao.class);
		//List<Cost> list=costDao.findAll();
		//int a=costDao.rows();
		//Cost cost=costDao.findById(2);
		AccountDao accountDao=ac.getBean(AccountDao.class);
		//AccountPage page=new AccountPage();
		//page.setBegin(6);
		//page.setEnd(0);
		//System.out.println(accountDao.findAll(page).get(0).getLogin_name());
		int recommender_id=accountDao.findByIdcard_no("321022200010012117");
		System.out.println(recommender_id);
	}
	
	//@Test
		public void test3(){
			String cfg="applicationContext.xml";
			ApplicationContext ac=new ClassPathXmlApplicationContext(cfg);
			AdminDao ad=ac.getBean(AdminDao.class);
			AdminPage page=new AdminPage();
			int rows=ad.rows(page);
			page.setRows(rows);
			List<Admin> admins=ad.findAll(page);
			for(Admin admin:admins){
				System.out.println(admin.getName());
			}

		}
}
