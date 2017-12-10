package com.hpe.store.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hpe.store.user.service.UserService;
import com.hpe.store.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	//注入userservvice
	private UserService userService;
	//模型驱动使用的对象
	private User user = new User();
	//自己建验证码的模型
	private String checkCode;
	
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public User getModel() {
		return user;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setUser(User user) {
		this.user = user;
	}
	//跳转到注册页面执行方法
	public String registPage() {
		return "registPage";
	}
	/**
	 * AJAX进行异步校验用户名的方法
	 */
	public String findByName() throws IOException{
		//调用service进行查询 ：
		User existUser = userService.findByName(user.getUsername());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		//否则输出中文乱码
		if (existUser !=null) {
			response.getWriter().print("<font color=red>用户名已经存在！</font>");
		} else {
			response.getWriter().print("<font color=green>用户名可以使用</font>");
		}
		return NONE;
	}
	
	public String regist() {
		// 判断验证码是否正确
		String originalCheckCode = (String) ServletActionContext.getRequest().getSession().getAttribute(checkCode);
		if (! (originalCheckCode == checkCode)) {
			this.addActionError("验证码错误，请重新输入！");
			return "checkFailed";
		}
		userService.save(user);
		this.addActionMessage("注册成功，请去邮箱激活账号！");
		return "msg";
	}
	//激活用户
	public String active() {
		User exsistUser = userService.findByCode(user.getCode());
		if (exsistUser != null) {
			exsistUser.setState(1);
			exsistUser.setCode(null);
			userService.update(exsistUser);
			this.addActionMessage("账户激活成功，请去登录！");
		} else {
			this.addActionError("激活失败，请重新注册！");
		}
		return "msg";
	}
	
	/**
	 * 跳转到登录页面
	 */
	
	public String loginPage() {
		return "login";
	}
	
	/**
	 * 用户登录处理方法
	 */
	public String login() {
		User existUser = userService.login(user);
		if (existUser == null) {
			//登录失败
			this.addActionError("登录失败，用户名或密码错误，请重新登录！");
			return LOGIN;
		} else {
			//登录成功
			//将用户信息存入session中
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			//页面挑战
			return "loginSuccess";
		}
	}
	
	/**
	 * 退出登录，销毁session，转向首页
	 */
	public String quit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
