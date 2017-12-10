/**
 * 
 */
package com.hpe.store.adminuser.vo;

/**
 * @author kyrie liu
 * @date Nov 26, 2017
 * @time 10:07:22 AM
 * @version 1.0
 */
public class AdminUser {
	private Integer uid;
	private String username;
	private String password;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
