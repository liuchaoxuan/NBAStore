/**
 * 
 */
package com.hpe.store.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hpe.store.adminuser.vo.AdminUser;

/**
 * @author kyrie liu
 * @date Nov 26, 2017
 * @time 10:42:56 AM
 * @version 1.0
 */
public class AdminUserDao extends HibernateDaoSupport{

	/**
	 * @param adminUser
	 * @return
	 */
	public AdminUser login (AdminUser adminUser) {
		// TODO Auto-generated method stub
		String hql = "from AdminUser where username = ? and password = ?";
		List<AdminUser> list = this.getHibernateTemplate().find(hql,adminUser.getUsername(),adminUser.getPassword());
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
}
