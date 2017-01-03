package com.tarena.dao;

import java.util.List;

import com.tarena.annotation.MybatisDao;
import com.tarena.entity.Cost;
import com.tarena.entity.page.CostPage;

@MybatisDao
public interface CostDao {
	List<Cost> findAll(CostPage page);
	void addCost(Cost cost);
	int rows();
	Cost findById(int id);
	void update(Cost cost);
	void delete(int id);
	void startCost(String id);
}
