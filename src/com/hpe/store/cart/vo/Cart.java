/**
 * 
 */
package com.hpe.store.cart.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author kyrie liu
 * @date Nov 14, 2017
 * @time 9:32:59 PM
 * @version 1.0
 * 因为cart包只与前台打交道，所以不用创建service和dao层
 */
public class Cart {
	// cart里需要有cartitm 的信息和价格总计，功能要有清空，移除，添加商品等方法
	// 使用map是因为做增删等操作比较方便
	private Map<Integer,CartItem> map = new LinkedHashMap<Integer,CartItem>();
	private double total;
	
	// 因为map适合做增删操作，但不适合做页面的遍历显示，所以我们把map中的值转换为集合，返回给前台
	
	// 说明cart中有一个catItems的属性～～～～
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	// 购物车的功能:
	// 1.将购物项添加到购物车
	public void addCart(CartItem cartItem) {
		// 判断购物车中是否已经存在该购物项:
		/*
		 *  * 如果存在:
		 *  	* 数量增加
		 *  	* 总计 = 总计 + 购物项小计
		 *  * 如果不存在:
		 *  	* 向map中添加购物项
		 *  	* 总计 = 总计 + 购物项小计
		 */
		// 获得商品id.
		Integer pid = cartItem.getProduct().getPid();
		// 判断购物车中是否已经存在该购物项:
		if(map.containsKey(pid)){
			// 存在
			CartItem _cartItem = map.get(pid);// 获得购物车中原来的购物项
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else{
			// 不存在
			map.put(pid, cartItem);
		}
		// 设置总计的值
		total += cartItem.getSubtotal();
	}

	// 2.从购物车移除购物项
	public void removeCart(Integer pid) {
		// 将购物项移除购物车:
		CartItem cartItem = map.remove(pid);
		// 总计 = 总计 -移除的购物项小计:
		total -= cartItem.getSubtotal();
	}

	// 3.清空购物车
	public void clearCart() {
		// 将所有购物项清空
		map.clear();
		// 将总计设置为0
		total = 0;
	}
}

