/**
 * 
 */
package com.hpe.store.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hpe.store.categorysecond.vo.CategorySecond;
import com.hpe.store.utils.PageHibernateCallback;

/**
 * @author kyrie liu
 * @date Nov 26, 2017
 * @time 11:02:52 PM
 * @version 1.0
 */
public class CategorySecondDao extends HibernateDaoSupport{

	/**
	 * @return 统计二级分类的个数
	 */
	public int findCount() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * @param begin
	 * @param limit
	 * @return 
	 * 二级分类分页查询
	 */
	public List<CategorySecond> findByPage(int begin, int limit) {
		String hql = "from CategorySecond order by csid desc";
		List<CategorySecond> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<CategorySecond>(hql, null, begin,
						limit));
		return list;
	}

	/**
	 * @param categorySecond
	 */
	public void save(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(categorySecond);
	}

	/**
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsId(Integer csid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	/**
	 * @param categorySecond
	 * @return 删除二级分类
	 */
	public void delete(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(categorySecond);
	}

	/**
	 * @param categorySecond
	 */
	public void update(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(categorySecond);
	}

	/**
	 * @return
	 */
	public List<CategorySecond> findAll() {
		// TODO Auto-generated method stub
		String hql = "from CategorySecond";
		return this.getHibernateTemplate().find(hql);
	}

}
