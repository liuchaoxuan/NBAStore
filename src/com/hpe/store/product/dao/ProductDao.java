/**
 * 
 */
package com.hpe.store.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hpe.store.product.vo.Product;
import com.hpe.store.utils.PageHibernateCallback;

/**
 * @author kyrie liu
 * @date Nov 12, 2017
 * @time 11:50:38 AM
 * @version 1.0
 */
public class ProductDao extends HibernateDaoSupport{

	/**
	 * @return
	 * 查询热门商品
	 */
	public List<Product> findHot() {
		// 按照离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 查询热门商品，条件就是is_Hot=1
		criteria.add(Restrictions.eq("is_hot", 1));
		// 时间按照倒序排列
		criteria.addOrder(Order.desc("pdate"));
		List<Product> hList = this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return hList;
	}

	/**
	 * 查询最新商品
	 */
	public List<Product> findNew() {
		//使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//按照时间倒序排列
		criteria.addOrder(Order.desc("pdate"));
		List<Product> nList = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return nList;
	}

	/**
	 * @param pid
	 * @return
	 */
	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	/**
	 * @param cid
	 * @return
	 */
	public int findCountCid(Integer cid) {
		// note:Hql里是对象和对象的属性的属性
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,cid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		} else {
			return 0;
		}
	}

	/**
	 * @param cid
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		// 如果使用sql查询：
		// select p.* from category c,categorysecond cs,product p where c.cid = cs.cid and cs.csid = p.csid and c.cid = 2
		// select p from Category c,CategorySecond cs,Product p where c.cid = cs.category.cid and cs.csid = p.categorySecond.csid and c.cid = ?
		// 使用hql的join查询，会自动关联外键表：
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,new Object[] {cid},begin,limit));
		return list;
	}

	/**
	 * @param csid
	 * @return
	 */
	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,csid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		} else {
			return 0;
		}
	}

	/**
	 * @param csid
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,new Object[] {csid},begin,limit));
		return list;
	}

	/**
	 * 后台查询总的商品数目
	 */
	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * @param begin
	 * @param limit
	 * @return 带分页的商品查询
	 */
	public List<Product> findByPage(int begin, int limit) {
		String hql = "from Product order by pdate desc";
		List<Product> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/**
	 * @param save product
	 */
	public void save(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(product);
	}

	/**
	 * @param product
	 */
	public void delete(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(product);
	}

	/**
	 * @param product
	 */
	public void update(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(product);
	}

}
