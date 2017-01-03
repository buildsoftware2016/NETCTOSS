package com.tarena.entity.page;
/**
 * 所有分页组件的父类
 * @author Administrator
 *
 */
public class Page {
	//上一页的最后一行行数end
	private int end;
	//下一页的第一行行数begin
	private int begin;
	//每页显示5行
	private int pageSize=5;
	//默认显示第一页
	private int currentPage=1;
	//总的数据行数
	private int rows;
	//总页数
	private int totalPages;
	
	//SQL中的表达式要访问类中的属性，一定是通过get方法访问的，
	//所以可以在get方法中将begin、end计算出来。
	public int getEnd() {
		end=(currentPage-1)*pageSize;
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getBegin() {
		begin=currentPage*pageSize+1;
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getTotalPages() {
		if(rows%pageSize==0){
			totalPages=rows/pageSize;
		}else{
			totalPages=rows/pageSize+1;
		}
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	
	
}
