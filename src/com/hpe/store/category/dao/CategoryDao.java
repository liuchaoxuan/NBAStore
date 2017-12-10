/**
 * 
 */
package com.hpe.store.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hpe.store.category.vo.Category;

/**
 * @author kyrie liu
 * @date Nov 11, 2017
 * @time 9:43:25 PM
 * @version 1.0
 */
public class CategoryDao extends HibernateDaoSupport{

	/**
	 * @return
	 * 查询一级分类dao层方法
	 */
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		String hql = "from Category";
		List<Category> cList = this.getHibernateTemplate().find(hql);
		return cList;
	}

	/**
	 * @param category
	 */
	public void save(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(category);
	}

	/**
	 * @param cid
	 * 根据cid查询出一级分类
	 */
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	/**
	 * @param category
	 */
	public void delete(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(category);
	}

	/**
	 * @param category
	 */
	public void update(Category category) {
		this.getHibernateTemplate().update(category);		
	}

}
