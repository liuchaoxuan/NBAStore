/**
 * 
 */
package com.hpe.store.cart.vo;

import com.hpe.store.product.vo.Product;

/**
 * @author kyrie liu
 * @date Nov 14, 2017
 * @time 9:33:18 PM
 * @version 1.0
 */
public class CartItem {
	//  商品
	private Product product;
	// 商品数量
	private Integer count;
	// 商品价格小计
	private Double subtotal;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return product.getShop_price() * count;
	}
	// 因为sub 总价是后台计算出来的，需要set；
//	public void setSubCount(Double subCount) {
//		this.subCount = subCount;
//	}
//	
}
