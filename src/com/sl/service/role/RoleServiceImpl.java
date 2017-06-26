package com.sl.service.role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.role.RoleMapper;
import com.sl.dao.user.UserMapper;
import com.sl.pojo.Role;
import com.sl.pojo.User;
@Service
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleMapper roleMapper;
	@Resource
	private UserMapper userMapper;

	@Override
	public List<Role> listRole() throws Exception {
		// TODO Auto-generated method stub
		return roleMapper.listRole();
	}

	@Override
	public List<Role> listRoleIdAndName() throws Exception {
		// TODO Auto-generated method stub
		return roleMapper.listRoleIdAndName();
	}

	@Override
	public Role getRole(Role role) throws Exception {
		// TODO Auto-generated method stub
		return roleMapper.getRole(role);
	}

	@Override
	public Role getRoleR(Role role) throws Exception {
		// TODO Auto-generated method stub
		return roleMapper.getRoleR(role);
	}

	@Override
	public int saveRole(Role role) throws Exception {
		// TODO Auto-generated method stub
		return roleMapper.saveRole(role);
	}

	@Override
	public int updateRole(Role role) throws Exception {
		// TODO Auto-generated method stub
		return roleMapper.updateRole(role);
	}

	@Override
	public int removeRole(Role role) throws Exception {
		// TODO Auto-generated method stub
		return roleMapper.removeRole(role);
	}

	@Override
	public boolean tm_modifyRole(Role role) throws Exception {
		roleMapper.updateRole(role);
		int roleId = role.getId();
		String roleName = role.getRoleName();
		User user = new User();
		user.setRoleId(roleId);
		user.setRoleName(roleName);
		if(null != roleName && !"".equals(roleName)){
			userMapper.updateUserRole(user);
		}
		return true;
	}

}
