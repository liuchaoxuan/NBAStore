/**
 * 
 */
package com.hpe.store.order.action;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.hpe.store.cart.vo.Cart;
import com.hpe.store.cart.vo.CartItem;
import com.hpe.store.order.service.OrderService;
import com.hpe.store.order.vo.Order;
import com.hpe.store.order.vo.OrderItem;
import com.hpe.store.user.vo.User;
import com.hpe.store.utils.PageBean;
import com.hpe.store.utils.PaymentUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author kyrie liu
 * @date Nov 21, 2017
 * @time 9:07:42 PM
 * @version 1.0
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	private Order order = new Order();
	private OrderService orderService;
	private int page;
	private String pd_FrpId;
	
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public OrderService getOrderService() {
		return orderService;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return this.order;
	}
	public String save() {
		// 1.保存数据到数据库
		// 补全数据
		order.setOrdertime(new Date());
		order.setState(1);
		// 从购物车中获取订单项信息
		Cart cart = (Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			this.addActionError("您的购物车为空，请先去选购！");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setOrder(order);
			order.getOrderItems().add(orderItem);
		}
		//设置订单所属用户
		User existUser = (User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if (existUser == null) {
			this.addActionError("您还没有登录，请先登录！");
			return "login";
		}
		order.setUser(existUser);
		
		orderService.save(order);
		cart.clearCart();//清空购物车
		//因为已经保存到了驱动模型，所以不用再保存到值栈了
		// 2.将订单对象显示到页面
		return "saveSuccess";
	}
	
	// 根据用户id分页显示用户订单
	public String findByUid() {
		User existUser = (User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		// 根据uid和page获取分页订单数据，返回pageBean		
		PageBean<Order> pageBean = orderService.findByPageId(existUser.getUid(),page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}
	
	// 根据订单id查询订单
	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}
	
	// 为订单付款
	public String payOrder() throws IOException {
		// 修改订单里的地址电话等信息
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		// 为订单付款
		// 正式请求地址：https://www.yeepay.com/app-merchant-proxy/node 
		// 准备请求参数
		String p0_Cmd = "Buy"; // 业务类型-固定值Buy
		String p1_MerId = "10001126856"; // 商户编号，这个是测试用，正规的要申请审核
		String p2_Order = order.getOid().toString(); // 订单标号，第三方返回信息时也会带上
		String p3_Amt = "0.01"; // 付款金额，测试用...
		String p4_Cur = "CNY"; //货币类型
		String p5_Pid = ""; // 商品名称，非必须项，可以为空
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述
		String p8_Url = "http://localhost:8080/NBAStore/order_callback.action"; // 支付成功后跳转地址
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		String pd_FrpId = this.pd_FrpId; // 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue); // 生成hmac
		// 向易宝支付发送支付请求
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return NONE;
	}
	
	// 付款成功后跳转回来的页面
	public String callback() {
		// 修改订单状态
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(2); // 订单状态2为已付款
		orderService.update(currOrder);
		this.addActionMessage("支付成功，本次订单号为："+order.getOid()+"付款金额："+order.getTotal());
		return "msg";
	}
	
	// 前台修改订单状态:确认收货
	public String updateState() {
		// 根据订单id查询订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
}
