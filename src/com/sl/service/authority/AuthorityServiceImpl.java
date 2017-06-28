package com.sl.service.authority;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.authority.AuthorityMapper;
import com.sl.dao.function.FunctionMapper;
import com.sl.pojo.Authority;
import com.sl.pojo.Function;
@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Resource
	private AuthorityMapper authorityMapper;
	@Resource
	private FunctionMapper functionMapper;
	
	
	@Override
	public List<Authority> listAuthority(Authority authority) throws Exception {
		// TODO Auto-generated method stub
		return authorityMapper.listAuthority(authority);
	}

	@Override
	public Authority getAuthorityById(Authority authority) throws Exception {
		// TODO Auto-generated method stub
		return authorityMapper.getAuthorityById(authority);
	}

	@Override
	public int saveAuthority(Authority authority) throws Exception {
		// TODO Auto-generated method stub
		return authorityMapper.saveAuthority(authority);
	}

	@Override
	public int removeAuthority(Authority authority) throws Exception {
		// TODO Auto-generated method stub
		return authorityMapper.removeAuthority(authority);
	}

	@Override
	public int updateAuthority(Authority authority) throws Exception {
		// TODO Auto-generated method stub
		return authorityMapper.updateAuthority(authority);
	}

	@Override
	public boolean tm_saveAuthority(String[] ids, String createdBy) throws Exception {
		Authority authority = new Authority();
		authority.setRoleId(Integer.valueOf(ids[0]));
		//删除角色下的功能
		authorityMapper.removeAuthority(authority);
		//重新赋权  获取到所有的functionId , 拼接成  id in (2,4,7,8);
		String sqlFid = ""; 
		for (String str:ids){
			sqlFid += Integer.parseInt(str)+",";
		}
		if (sqlFid != null && sqlFid.contains(",")){
			sqlFid = sqlFid.substring(0, sqlFid.lastIndexOf(","));
			List<Function> fList = functionMapper.listFunctionByIdIn(sqlFid); 
			if (null != fList && fList.size() > 0){
				for (Function function:fList){
					authority.setFunctionId(function.getId());
					authority.setCreatedBy(createdBy);
					authority.setCreationTime(new Date());
					//增加权限
					authorityMapper.saveAuthority(authority);
				}
			}
		}
		return true;
	}

}
