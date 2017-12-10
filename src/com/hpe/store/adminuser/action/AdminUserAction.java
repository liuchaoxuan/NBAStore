/**
 * 
 */
package com.hpe.store.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.hpe.store.adminuser.service.AdminUserService;
import com.hpe.store.adminuser.vo.AdminUser;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author kyrie liu
 * @date Nov 26, 2017
 * @time 10:40:35 AM
 * @version 1.0
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {
	private AdminUser adminUser = new AdminUser();
	private AdminUserService adminUserService;

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	@Override
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return this.adminUser;
	}

	// 后台登录的方法
	public String login() {
		AdminUser existAdminUser = adminUserService.login(adminUser);
		if (existAdminUser == null) {
			// 登录失败
			this.addActionError("亲，您的用户名或密码错误，请重新登录！");
			return "loginFailed";
		} else {
			// 登录成功
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
	}
}
