/**
 * 
 */
package com.hpe.store.categorysecond.adminaction;

import java.util.List;

import com.hpe.store.category.service.CategoryService;
import com.hpe.store.category.vo.Category;
import com.hpe.store.categorysecond.service.CategorySecondService;
import com.hpe.store.categorysecond.vo.CategorySecond;
import com.hpe.store.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author kyrie liu
 * @date Nov 26, 2017
 * @time 11:00:45 PM
 * @version 1.0
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {
	private CategorySecond categorySecond = new CategorySecond();
	private CategorySecondService categorySecondService;
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// 接收页码
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	@Override
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}
	// 带有分页的查询所有二级分类的操作:
	public String findAll() {
		// 调用Service进行查询.
		PageBean<CategorySecond> pageBean = categorySecondService
				.findByPage(page);
		// 将pageBean的数据存入到值栈中.
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	// 添加二级分类
	public String addPage() {
		// 首先要查询所有一级分类，显示在添加按钮后边，供添加的二级分类选择
		List<Category> cList = categoryService.findAll();
		// 存入值栈中，在页面显示
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPageSuccess";
	}
	
	// 保存添加的二级分类
	public String save() {
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	
	// 删除二级分类的方法
	public String delete() {
		// 首先查询二级分类
		// 如果需要删除二级分类下的商品，需要配置级联product；
		categorySecond = categorySecondService.findByCsId(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	
	// 编辑二级分类
	public String edit() {
		// 首先查询到该二级分类
		categorySecond = categorySecondService.findByCsId(categorySecond.getCsid());
		// 查询所有的一级分类。因为可能要修改二级分类所属的一级分类。
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	
	// 更新二级分类
	public String update() {
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
