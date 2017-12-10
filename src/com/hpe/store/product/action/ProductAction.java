/**
 * 
 */
package com.hpe.store.product.action;

import com.hpe.store.product.service.ProductService;
import com.hpe.store.product.vo.Product;
import com.hpe.store.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author kyrie liu
 * @date Nov 12, 2017
 * @time 6:32:18 PM
 * @version 1.0
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	//模型驱动需要定义并实例化模型并提供get和set方法
	//Action接收这个实例对象进行相关处理并返回处理结果（model自动放入值栈中）
	private Product product = new Product();
	private ProductService productService;
	
	//接受一级分类cid
	private Integer cid;
	//接收当前页数page
	private Integer page;
	
	//接收二级分类csid
	private Integer csid;

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return this.product;
	}
	
	// 查询商品详情
	public String findByPid() {
		//直接使用模型驱动的这个product，就会直接放入值栈了
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	// 根据以及分类的id查询商品
	public String findByCid() {
		// 因为进入首页时已经查询过了以及分类，而且我们配置的是双向关联表关系，所以二级分类也已经查询到了，和以及分类一起放入了session中，此处可以直接返回页面
		// 但要注意的是必须从首页点击一级分类
		
		//action中直接调用service层得到最终要显示的数据并转向即可，service层做具体相关业务逻辑处理
		//传入一级分类和页码，进行分页查询
		PageBean<Product> pageBean = productService.findByPageCid(cid,page);
		// 将pageBean放入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	// 根据二级分类的id查询商品
	public String findByCsid() {
		// 根据二级分类id分页查询商品
		PageBean pageBean = productService.findByPageCsid(csid,page);
		// 将PageBean存入到值栈中:
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
