package com.sl.service.user;

import java.util.List;

import com.sl.pojo.User;

public interface UserService {
	/**
	 * 用户登录
	 * @param user
	 * @return
	 * @throws Exception
	 */
	User getLoginUser(User user) throws Exception;
	
	/**
	 * loginCodeIsExit
	 * 登录账号是否存在
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int loginCodeIsExit(User user) throws Exception;
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int saveUser(User user) throws Exception;
	
	/**
	 * 更新用户字段,公用方法
	 * @param user
	 * @return int
	 * @throws Exception
	 */
	int updateUser(User user) throws Exception;
	

	/**
	 * 获取总记录数
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int totalCount(User user) throws Exception;
	
	/**
	 * 用户列表
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<User> listUserByPage(User user) throws Exception;
	
	/**
	 * listUserBySearch
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<User> listUserBySearch(User user) throws Exception;
	
	/**
	 * getUserById
	 * @param user
	 * @return
	 * @throws Exception
	 */
	User getUserById(User user) throws Exception;
	
	/**
	 * 删除用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int removeUser(User user) throws Exception;
	
	/**
	 * 更改用户角色
	 * @param user
	 * @return
	 */
	int updateUserRole(User user) throws Exception;
	
	/**
	 * 删除用户图片
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int delUserPic(User user) throws Exception;
}
