/**
 * 
 */
package com.hpe.store.user.admindao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hpe.store.product.vo.Product;
import com.hpe.store.user.vo.User;
import com.hpe.store.utils.PageHibernateCallback;

/**
 * @author kyrie liu
 * @date Dec 5, 2017
 * @time 11:09:55 PM
 * @version 1.0
 */
public class UserAdminDao extends HibernateDaoSupport{

	/**
	 * @return
	 */
	public int findCount() {
		String hql = "select count(*) from User";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<User> findByPage(int begin, int limit) {
		String hql = "from User order by uid";
		List<User> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<User>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
}
