/**
 * 
 */
package com.hpe.store.cart.action;

import org.apache.struts2.ServletActionContext;

import com.hpe.store.cart.vo.Cart;
import com.hpe.store.cart.vo.CartItem;
import com.hpe.store.product.service.ProductService;
import com.hpe.store.product.vo.Product;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author kyrie liu
 * @date Nov 14, 2017
 * @time 9:32:51 PM
 * @version 1.0
 */
public class CartAction extends ActionSupport{
	private Integer pid;
	private Integer count;
	private ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	// add to cart
	public  String addCart() {
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		Cart cart = getCart();
		cart.addCart(cartItem);
		return "addCart";
	}

	// 清空购物车
	public String clearCart() {
		Cart cart = getCart();
		cart.clearCart();
		return "clearCart";
	}
	//移除购物项
	public String removeCart() {
		Cart cart = getCart();
		cart.removeCart(pid);
		return "removeCart";
	}
	
	public String myCart() {
		return "myCart";
	}

	/**
	 * @return 内部获取购物车的方法
	 */
	private Cart getCart() {
		// TODO Auto-generated method stub
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession()
			.setAttribute("cart", cart);
		}
		return cart;
	}

}
