/**
 * 
 */
package com.hpe.store.category.vo;

import java.util.HashSet;
import java.util.Set;

import com.hpe.store.categorysecond.vo.CategorySecond;

/**
 * @author kyrie liu
 * @date Nov 11, 2017
 * @time 9:35:47 PM
 * @version 1.0
 */
public class Category {
	private Integer cid;
	private String cname;
	// 配置二级分类集合
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
}
