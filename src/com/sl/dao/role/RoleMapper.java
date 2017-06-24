package com.sl.dao.role;

import java.util.List;

import com.sl.pojo.Role;

public interface RoleMapper {
	/**
	 * listRole
	 * @return
	 * @throws Exception
	 */
	List<Role> listRole() throws Exception;
	
	/**
	 * listRoleIdAndName
	 * @return
	 * @throws Exception
	 */
	List<Role> listRoleIdAndName() throws Exception;
	
	/**
	 * getRole
	 * @param role
	 * @return
	 * @throws Exception
	 */
	Role getRole(Role role) throws Exception;
	
	
	/**
	 * getRoleR
	 * @param role
	 * @return
	 * @throws Exception
	 */
	Role getRoleR(Role role) throws Exception;
	/**
	 * saveRole
	 * @param role
	 * @return
	 * @throws Exception
	 */
	int saveRole(Role role) throws Exception;
	
	/**
	 * updateRole
	 * @param role
	 * @return
	 * @throws Exception
	 */
	int updateRole(Role role) throws Exception;
	
	/**
	 * removeRole
	 * @param role
	 * @return
	 * @throws Exception
	 */
	int removeRole(Role role) throws Exception;
	
	
}
