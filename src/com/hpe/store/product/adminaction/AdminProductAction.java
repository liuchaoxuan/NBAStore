/**
 * 
 */
package com.hpe.store.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.hpe.store.categorysecond.service.CategorySecondService;
import com.hpe.store.categorysecond.vo.CategorySecond;
import com.hpe.store.product.service.ProductService;
import com.hpe.store.product.vo.Product;
import com.hpe.store.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author kyrie liu
 * @date Nov 28, 2017
 * @time 5:36:51 AM
 * @version 1.0
 */
public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product = new Product();
	// 接收page参数，进行分页显示用
	private Integer page;
	private ProductService productService;
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}

	// 文件上传需要的参数；名字不能乱取...
	private File upload; // 上传的文件，名字必须与表单中的name一致
	private String uploadFileName; //接收上传文件的文件名
	private String uploadContextType; // 接收文件上传的MIME的类型，也就是文件类型

	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}
	// 带分页的查询商品的方法
	public String findAll() {
		PageBean<Product> pageBean = productService.findByPage(page);
		// 放入值栈传递到页面
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllSuccess";
	}

	// 跳转到添加商品页面
	public String addPage() {
		// 首先要查询所有的二级分类
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		// 然后跳转到添加页面
		return "addPageSuccess";
	}

	// 保存添加的商品的方法
	public String save() throws IOException {
		// 完善添加的商品信息
		product.setPdate(new Date());
		// 保存商品图片到应用目录下
		if (upload != null) {
			// 获得文件上传的磁盘绝对路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			// 创建目标文件：
			File diskFile = new File(realPath+"//"+uploadFileName);
			// 文件上传
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/"+uploadFileName);
		}
		// 将数据保存到数据库
		productService.save(product);
		return "saveSuccess";
	}
	
	// 删除商品的方法：
	public String delete() {
		// 先查询后删除
		product = productService.findByPid(product.getPid());
		// 删除商品的图片
		String path = product.getImage();
		if (path != null) {
			String realPath = ServletActionContext.getServletContext().getRealPath("/"+path);
			File file = new File(realPath); //指向商品图片
			file.delete(); //删除图片
		}
		// 删除商品
		productService.delete(product);
		return "deleteSuccess";
	}
	
	// 编辑商品的方法
	public String edit() {
		// 根据商品的id查询商品信息
		product = productService.findByPid(product.getPid());
		// 查询所有的二级分类
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}
	
	// 修改商品信息
	public String update() throws IOException {
		product.setPdate(new Date());
		// 保存商品图片到应用目录下
		if (upload != null) {
			// 删除原来的商品的图片
			String path = product.getImage();
			if (path != null) {
				String originPath = ServletActionContext.getServletContext().getRealPath("/"+path);
				File file = new File(originPath); //指向商品图片
				file.delete(); //删除图片
			}
			// 获得文件上传的磁盘绝对路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			// 创建目标文件：
			File diskFile = new File(realPath+"//"+uploadFileName);
			// 文件上传
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/"+uploadFileName);
		}
		// 修改商品信息到数据库
		productService.update(product);
		return "updateSuccess";
	}
}
