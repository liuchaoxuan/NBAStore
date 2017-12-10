/**
 * 
 */
package com.hpe.store.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hpe.store.category.dao.CategoryDao;
import com.hpe.store.category.vo.Category;

/**
 * @author kyrie liu
 * @date Nov 11, 2017
 * @time 9:44:23 PM
 * @version 1.0
 */
@Transactional
public class CategoryService {
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * @return 
	 * 查询以及分类业务方法
	 */
	public List<Category> findAll() {
		List<Category> cList = categoryDao.findAll();
		return cList;
	}

	/**
	 * 后台管理添加以及分类
	 */
	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryDao.save(category);
	}

	/**
	 * @param cid
	 * @return
	 */
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return categoryDao.findByCid(cid);
	}

	/**
	 * @param category
	 * 删除一级分类
	 */
	public void delete(Category category) {
		// TODO Auto-generated method stub
		categoryDao.delete(category);
	}

	/**
	 * @param category
	 * 更新以及分类
	 */
	public void update(Category category) {
		// TODO Auto-generated method stub
		categoryDao.update(category);
	}
}
