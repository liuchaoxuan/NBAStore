/**
 * 
 */
package com.hpe.store.order.adminaction;

import java.util.List;

import com.hpe.store.order.service.OrderService;
import com.hpe.store.order.vo.Order;
import com.hpe.store.order.vo.OrderItem;
import com.hpe.store.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author kyrie liu
 * @date Nov 30, 2017
 * @time 9:37:25 PM
 * @version 1.0
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {
	private Order order = new Order();
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	// 接受page参数
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}
	// 带分页的查询订单
	public String findAll() {
		// 分页查询
		PageBean<Order> pageBean = orderService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		//页面跳转
		return "findAll";
	}
	
	// 根据订单id查询订单项
	public String findOrderItem() {
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderIem";
	}
	
	// 后台修改订单状态的方法
	public String updateState() {
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(3);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
}
