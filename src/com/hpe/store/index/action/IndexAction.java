package com.hpe.store.index.action;

import java.util.List;

import com.hpe.store.category.service.CategoryService;
import com.hpe.store.category.vo.Category;
import com.hpe.store.product.service.ProductService;
import com.hpe.store.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	
	private CategoryService categoryService;
	private ProductService productService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * 执行的访问首页的方法
	 */
	public String execute() {
		//查询一级分类列表，因为menu.jsp在每个页面都显示，所以放入session中
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getSession().put("cList", cList);
		//查询热门商品
		List<Product> hList = productService.findHot();
		//存入值栈，因为只在首页中展示，不需要放入session
		//用值栈的set而不是push方法，用的时候比较好取
		ActionContext.getContext().getValueStack().set("hList", hList);
		//查询最新商品
		List<Product> nList = productService.findNew();
		ActionContext.getContext().getValueStack().setValue("nList", nList);
		return "index";
	}
}
