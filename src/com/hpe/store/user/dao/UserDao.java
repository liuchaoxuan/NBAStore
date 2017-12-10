/**
 * 
 */
package com.hpe.store.user.dao;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hpe.store.user.vo.User;

/**
 * @author kyrie liu
 * @date Nov 5, 2017
 * @time 11:23:37 PM
 * @version 1.0
 */
public class UserDao extends HibernateDaoSupport{
	public User findByUserName(String username) {
		String hql = "from User where username = ?";
		List<User> results = this.getHibernateTemplate().find(hql,username);
		if (results!=null && results.size()>0) {
			return results.get(0);
		} else {
			return null;
		}		
	}

	//注册用户存入数据库的代码实现
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	/**
	 * @param code
	 * @return
	 */
	public User findByCode(String code) {
		// TODO Auto-generated method stub
		//note:User对象而不是user表！
		String hql = "from User where code = ?";
		List<User> results = this.getHibernateTemplate().find(hql,code);
		if (results!=null && results.size()>0) {
			return results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @param user
	 */
	public void update(User user) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(user);
	}

	/**
	 * @param user
	 * @return
	 */
	public User login(User user) {
		// TODO Auto-generated method stub
		String hql = "from User where username = ? and password = ? and state = ?";
		List<User> results = this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),1);
		if (results != null && results.size() > 0) {
			return results.get(0);
		} else {
			return null;
		}
	}

}
