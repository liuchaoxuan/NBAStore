/**
 * 
 */
package com.hpe.store.user.adminservice;

import java.util.List;

import com.hpe.store.product.vo.Product;
import com.hpe.store.user.admindao.UserAdminDao;
import com.hpe.store.user.vo.User;
import com.hpe.store.utils.PageBean;

/**
 * @author kyrie liu
 * @date Dec 5, 2017
 * @time 11:08:56 PM
 * @version 1.0
 */
public class UserAdminService {
	private UserAdminDao userAdminDao;
	public void setUserAdminDao(UserAdminDao userAdminDao) {
		this.userAdminDao = userAdminDao;
	}
	/**
	 * @param page
	 * @return
	 */
	public PageBean<User> findByPage(Integer page) {
		PageBean<User> pageBean = new PageBean<User>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 20;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = userAdminDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<User> list = userAdminDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
}
