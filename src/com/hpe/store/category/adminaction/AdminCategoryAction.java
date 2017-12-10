/**
 * 
 */
package com.hpe.store.category.adminaction;

import java.util.List;

import com.hpe.store.category.service.CategoryService;
import com.hpe.store.category.vo.Category;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author kyrie liu
 * @date Nov 26, 2017
 * @time 4:06:09 PM
 * @version 1.0
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {

	private Category category = new Category();

	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return this.category;
	}
	
	// 查询一级分类
	public String findAll() {
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	
	// 添加一级分类
	public String save() {
		categoryService.save(category);
		return "saveSuccess";
	}
	
	// 删除一级分类
	public String delete() {
		// 首先要根据cid查询一级分类
		category = categoryService.findByCid(category.getCid());
		// 查询出之后删除
		categoryService.delete(category);
		return "deleteSuccess";
	}
	
	// 编辑一级分类
	public String edit() {
		category = categoryService.findByCid(category.getCid());
		return "editSuccess";
	}
	
	// 修改以及分类
	public String update() {
		categoryService.update(category);
		return "updateSuccess";
	}
}
