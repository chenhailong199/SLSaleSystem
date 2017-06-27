package com.sl.service.authority;

import java.util.List;

import com.sl.pojo.Authority;

public interface AuthorityService {
	
	/**
	 * listAuthority
	 * @param authority
	 * @return
	 * @throws Exception
	 */
	List<Authority> listAuthority(Authority authority) throws Exception;
	
	/**
	 * getAuthority
	 * @param authority
	 * @return
	 * @throws Exception
	 */
	Authority getAuthorityById(Authority authority) throws Exception;
	
	/**
	 * saveAuthority
	 * @param authority
	 * @return
	 * @throws Exception
	 */
	int saveAuthority(Authority authority) throws Exception;
	
	/**
	 * removeAuthority
	 * @param authority
	 * @return
	 * @throws Exception
	 */
	int removeAuthority(Authority authority) throws Exception;
	
	/**
	 * updateAuthority
	 * @param authority
	 * @return
	 * @throws Exception
	 */
	int updateAuthority(Authority authority) throws Exception;
	
	/**
	 * 权限更新操作
	 * @param authority
	 * @return
	 * @throws Exception
	 */
	boolean tm_saveAuthority(String[] ids, String createdBy) throws Exception;
}
