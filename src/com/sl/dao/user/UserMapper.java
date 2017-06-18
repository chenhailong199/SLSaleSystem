package com.sl.dao.user;

import com.sl.pojo.user.User;

public interface UserMapper {
	
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
}
