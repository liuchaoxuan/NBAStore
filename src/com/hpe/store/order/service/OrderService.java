/**
 * 
 */
package com.hpe.store.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hpe.store.order.dao.OrderDao;
import com.hpe.store.order.vo.Order;
import com.hpe.store.order.vo.OrderItem;
import com.hpe.store.utils.PageBean;

/**
 * @author kyrie liu
 * @date Nov 21, 2017
 * @time 9:08:09 PM
 * @version 1.0
 */
@Transactional
public class OrderService {
	private OrderDao orderDao;

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	/**
	 * @param order
	 */
	public void save(Order order) {
		// TODO Auto-generated method stub
		orderDao.save(order);
	}

	/**
	 * @param uid
	 * @param page
	 * @return
	 */
	public PageBean<Order> findByPageId(Integer uid, int page) {
		// TODO Auto-generated method stub
		PageBean<Order> pageBean = new PageBean<Order>();
		// 设置当前页
		pageBean.setPage(page);
		// 设置每页显示个数
		Integer limit = 5;
		pageBean.setLimit(limit);
		// 首先获取总的订单个数
		Integer totalCount = null;
		totalCount = orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		Integer totalPage = null;
		if (totalCount % limit == 0) {
				totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示集合
		Integer begin = (page-1)*limit;
		List<Order> list = orderDao.findByPageUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * @param oid
	 * @return
	 */
	public Order findByOid(Integer oid) {
		// TODO Auto-generated method stub
		return orderDao.findByOid(oid);
	}

	/**
	 * @param currOrder
	 */
	public void update(Order currOrder) {
		// TODO Auto-generated method stub
		orderDao.update(currOrder);
	}

	/**
	 * @param page
	 * @return
	 * 后台分页查询订单的业务层方法
	 */
	public PageBean<Order> findByPage(Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPage(page);
		int limit = 10;
		pageBean.setLimit(limit);
		int totalCount = orderDao.findByCount();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置开始页
		int begin = (page - 1) * limit;
		// 每页显示的集合
		List<Order> list = orderDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * @param oid
	 * @return
	 */
	public List<OrderItem> findOrderItem(Integer oid) {
		// TODO Auto-generated method stub
		return orderDao.findOrderItem(oid);
	}

}
