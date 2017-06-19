package com.sl.dao.role;

import java.util.List;

import com.sl.pojo.role.Role;

public interface RoleMapper {
	/**
	 * 查询 rolelist
	 * @return
	 * @throws Exception
	 */
	List<Role> listRole() throws Exception;
}
