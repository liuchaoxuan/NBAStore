/**
 * 
 */
package com.hpe.store.user.service;

import org.springframework.transaction.annotation.Transactional;

import com.hpe.store.user.dao.UserDao;
import com.hpe.store.user.vo.User;
import com.hpe.store.utils.MailUtils;
import com.hpe.store.utils.UUIDUtils;

/**
 * @author kyrie liu
 * @date Nov 5, 2017
 * @time 11:23:23 PM
 * @version 1.0
 */
@Transactional
public class UserService {
	//注入UserDao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public User findByName(String username) {
		return userDao.findByUserName(username);
	}

	//业务层完成用户注册代码
	public void save(User user) {
		//设置状态信息，0表示未激活，1表示已激活
		user.setState(0);
		//使用uuid工具类生成激活码
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//发送激活邮件
		MailUtils.sendMail(user.getEmail(), code);
	}
	/**
	 * @param code
	 * @return 
	 */
	public User findByCode(String code) {
		// TODO Auto-generated method stub
		return  userDao.findByCode(code);
	}
	/**
	 * @param user
	 */
	//更新用户激活状态
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}
	/**
	 * @param user
	 * @return
	 */
	public User login(User user) {
		// TODO Auto-generated method stub
		User existUser = userDao.login(user);
		return existUser;
	}
}
