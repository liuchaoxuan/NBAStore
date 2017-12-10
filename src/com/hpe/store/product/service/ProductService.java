/**
 * 
 */
package com.hpe.store.product.service;

import java.util.List;

import com.hpe.store.product.dao.ProductDao;
import com.hpe.store.product.vo.Product;
import com.hpe.store.utils.PageBean;

/**
 * @author kyrie liu
 * @date Nov 12, 2017
 * @time 11:47:58 AM
 * @version 1.0
 */
public class ProductService {

	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	//查询热门商品
	public List<Product> findHot(){
		List<Product> hList = productDao.findHot();
		return hList;
	}
	//查询最新商品
	public List<Product> findNew(){
		return productDao.findNew();
	}
	/**
	 * @param pid
	 * @return
	 */
	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return productDao.findByPid(pid);
	}
	/**
	 * @param cid
	 * @param page
	 * @return
	 */
	public PageBean<Product> findByPageCid(Integer cid, Integer page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页码
		pageBean.setPage(page);
		//设置每页显示个数
		int limit = 8;
		pageBean.setLimit(limit);
		//设置改分类下商品数
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		totalPage = (int) Math.ceil(totalCount / limit);
		pageBean.setTotalPage(totalPage);
		//设置分页显示的商品信息
		int begin = 0;
		begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	/**
	 * @param csid
	 * @param page
	 * @return
	 */
	public PageBean findByPageCsid(Integer csid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页码
		pageBean.setPage(page);
		//设置每页显示个数
		int limit = 8;
		pageBean.setLimit(limit);
		//设置改分类下商品数
		int totalCount = 0;
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		totalPage = (int) Math.ceil(totalCount / limit);
		pageBean.setTotalPage(totalPage);
		//设置分页显示的商品信息
		int begin = 0;
		begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	/**
	 * 后台分页查询商品
	 */
	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = productDao.findCount();
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
		List<Product> list = productDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * @param product
	 */
	public void save(Product product) {
		productDao.save(product);
	}
	/**
	 * @param product
	 */
	public void delete(Product product) {
		// TODO Auto-generated method stub
		productDao.delete(product);
	}
	/**
	 * @param product
	 * 修改商品信息
	 */
	public void update(Product product) {
		// TODO Auto-generated method stub
		productDao.update(product);
	}
}
