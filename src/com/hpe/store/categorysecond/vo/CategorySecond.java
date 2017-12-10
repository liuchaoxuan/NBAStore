/**
 * 
 */
package com.hpe.store.categorysecond.vo;

import java.util.HashSet;
import java.util.Set;

import com.hpe.store.category.vo.Category;
import com.hpe.store.product.vo.Product;

/**
 * @author kyrie liu
 * @date Nov 13, 2017
 * @time 10:38:25 AM
 * @version 1.0
 */
public class CategorySecond {
	/**
	 * 
Create Table

CREATE TABLE `categorysecond` (
  `csid` int(11) NOT NULL AUTO_INCREMENT,
  `csname` varchar(255) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`csid`),
  KEY `FK936FCAF21DB1FD15` (`cid`),
  CONSTRAINT `FK936FCAF21DB1FD15` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8

	 */
	private Integer csid;
	private String csname;
	// 关联其他实体类的时候一定要new一个实体对象，否则初始化的时候为null！以后注入不了属性值！
	private Category category = new Category();
	private Set<Product> products = new HashSet<Product>();
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

}
