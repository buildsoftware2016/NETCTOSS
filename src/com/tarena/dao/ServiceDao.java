package com.tarena.dao;

import java.util.List;
import java.util.Map;

import com.tarena.annotation.MybatisDao;
import com.tarena.entity.Cost;
import com.tarena.entity.Service;
import com.tarena.entity.page.ServicePage;

@MybatisDao
public interface ServiceDao {
	List<Map<String,Object>> findAll(ServicePage page);
	int rows();
	void start(String id);//注意判断所属账务账号是否开通
	void pause(String id);
	String getAccountStatus(String id);//获取所属账务账号状态
	void delete(String id);//不是真的删除，设置状态为2
	List<Cost> findAllCost();
	void add(Service service);
	Service findById(String id);//根据service_id查找到service对象
	void update(Service service);//将service_id,cost_id存入临时表SERVICE_UPDATE_BAK表中
}
