package com.sl.service.role;

import java.util.List;

import com.sl.pojo.Role;

public interface RoleService {
	/**
	 * 查询 rolelist
	 * @return
	 * @throws Exception
	 */
	List<Role> listRole() throws Exception;
}
