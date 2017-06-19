package com.sl.service.role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.role.RoleMapper;
import com.sl.pojo.role.Role;
@Service
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleMapper roleMapper;

	@Override
	public List<Role> listRole() throws Exception {
		// TODO Auto-generated method stub
		return roleMapper.listRole();
	}

}
