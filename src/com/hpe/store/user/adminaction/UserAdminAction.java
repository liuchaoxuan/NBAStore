/**
 * 
 */
package com.hpe.store.user.adminaction;

import com.hpe.store.user.adminservice.UserAdminService;
import com.hpe.store.user.vo.User;
import com.hpe.store.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author kyrie liu
 * @date Dec 5, 2017
 * @time 11:02:33 PM
 * @version 1.0
 */
public class UserAdminAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();
	private UserAdminService userAdminService;
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setUserAdminService(UserAdminService userAdminService) {
		this.userAdminService = userAdminService;
	}
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	// 查询所有用户
	public String findAll() {
		PageBean<User> pageBean = userAdminService.findByPage(page);
		// 放入值栈传递到页面
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
}
