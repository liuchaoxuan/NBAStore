/**
 * 
 */
package com.hpe.store.adminuser.service;

import com.hpe.store.adminuser.dao.AdminUserDao;
import com.hpe.store.adminuser.vo.AdminUser;

/**
 * @author kyrie liu
 * @date Nov 26, 2017
 * @time 10:42:29 AM
 * @version 1.0
 */
public class AdminUserService {
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	/**
	 * @param adminUser
	 * @return
	 */
	public AdminUser login(AdminUser adminUser) {
		// TODO Auto-generated method stub
		return adminUserDao.login(adminUser);
	}

}
