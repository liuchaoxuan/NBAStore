/**
 * 
 */
package com.hpe.store.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hpe.store.order.service.OrderService;
import com.hpe.store.order.vo.Order;
import com.hpe.store.order.vo.OrderItem;
import com.hpe.store.utils.PageHibernateCallback;

/**
 * @author kyrie liu
 * @date Nov 21, 2017
 * @time 9:08:34 PM
 * @version 1.0
 */
public class OrderDao extends HibernateDaoSupport {

	/**
	 * @param order
	 */
	public void save(Order order) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(order);
	}

	/**
	 * @param uid
	 * @return
	 */
	// dao层订单个数查询
	public Integer findByCountUid(Integer uid) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,uid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		} else {
			return 0;
		}
	}

	/**
	 * @param uid
	 * @param begin
	 * @param limit
	 * @return
	 */
	// dao层我的订单分页查询
	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
		// TODO Auto-generated method stub
		String hql = "from Order o where o.user.uid = ? order by ordertime desc";
		// 然后调用分页查询的模板
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql,new Object[] {uid},begin,limit));
		return list;
	}

	/**
	 * @param oid
	 * @return
	 */
	public Order findByOid(Integer oid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	/**
	 * @param currOrder
	 */
	public void update(Order currOrder) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(currOrder);
	}

	/**
	 * @return
	 */
	public int findByCount() {
		// TODO Auto-generated method stub
		String hql = " select count(*) from Order";
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
	 * // 后台分页查询订单的方法
	 */
	public List<Order> findByPage(int begin, int limit) {
		String hql = "from Order order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, null, begin, limit));
		return list;
	}

	/**
	 * @param oid
	 * @return
	 */
	public List<OrderItem> findOrderItem(Integer oid) {
		// TODO Auto-generated method stub
		String hql = "from OrderItem oi where oi.order.oid = ?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql,oid);
		return list;
	}
	


}
