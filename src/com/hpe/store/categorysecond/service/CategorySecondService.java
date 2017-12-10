/**
 * 
 */
package com.hpe.store.categorysecond.service;

import java.util.List;

import com.hpe.store.categorysecond.dao.CategorySecondDao;
import com.hpe.store.categorysecond.vo.CategorySecond;
import com.hpe.store.utils.PageBean;

/**
 * @author kyrie liu
 * @date Nov 26, 2017
 * @time 11:03:37 PM
 * @version 1.0
 * 二级分类服务类
 */
public class CategorySecondService {
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	/**
	 * @param page
	 * @return
	 *  二级分类带有分页的查询操作:
	 */
	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();

		// 设置参数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置页面显示数据的集合:
		int begin = (page - 1) * limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * @param categorySecond
	 */
	public void save(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.save(categorySecond);
	}

	/**
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsId(Integer csid) {
		// TODO Auto-generated method stub
		return categorySecondDao.findByCsId(csid);
	}

	/**
	 * @param categorySecond
	 */
	public void delete(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.delete(categorySecond);
	}

	/**
	 * @param categorySecond
	 */
	public void update(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.update(categorySecond);
	}

	/**
	 * @return
	 */
	public List<CategorySecond> findAll() {
		// TODO Auto-generated method stub
		return categorySecondDao.findAll();
	}


}
