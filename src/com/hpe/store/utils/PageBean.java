/**
 * 
 */
package com.hpe.store.utils;

import java.util.List;

/**
 * @author kyrie liu
 * @date Nov 13, 2017
 * @time 3:51:16 PM
 * @version 1.0
 */
// 设置为泛型类，因为不仅需要分页显示商品，也可能需要显示订单等
public class PageBean<T> {
	private int page;//当前页数
	private int totalCount;//总记录数
	private int limit;//每页显示个数
	private int totalPage;//总页数
	private List<T> list;//每页显示数据的集合
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	} 

}
