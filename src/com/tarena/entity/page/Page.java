package com.tarena.entity.page;
/**
 * ���з�ҳ����ĸ���
 * @author Administrator
 *
 */
public class Page {
	//��һҳ�����һ������end
	private int end;
	//��һҳ�ĵ�һ������begin
	private int begin;
	//ÿҳ��ʾ5��
	private int pageSize=5;
	//Ĭ����ʾ��һҳ
	private int currentPage=1;
	//�ܵ���������
	private int rows;
	//��ҳ��
	private int totalPages;
	
	//SQL�еı��ʽҪ�������е����ԣ�һ����ͨ��get�������ʵģ�
	//���Կ�����get�����н�begin��end���������
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
