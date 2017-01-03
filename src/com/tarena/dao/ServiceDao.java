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
	void start(String id);//ע���ж����������˺��Ƿ�ͨ
	void pause(String id);
	String getAccountStatus(String id);//��ȡ���������˺�״̬
	void delete(String id);//�������ɾ��������״̬Ϊ2
	List<Cost> findAllCost();
	void add(Service service);
	Service findById(String id);//����service_id���ҵ�service����
	void update(Service service);//��service_id,cost_id������ʱ��SERVICE_UPDATE_BAK����
}
