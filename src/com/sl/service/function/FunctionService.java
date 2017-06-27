package com.sl.service.function;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sl.pojo.Authority;
import com.sl.pojo.Function;

public interface FunctionService {
	/**
	 * 根据roleId 得到用户的权限主菜单列表
	 * @param authority
	 * @return
	 * @throws Exception
	 */
	List<Function> listMainFunction(Authority authority) throws Exception;
	
	/**
	 * 获得二级子菜单
	 * @param function
	 * @return
	 * @throws Exception
	 */
	List<Function> listSubFunction(Function function) throws Exception;
	/**
	 * listFunctionByRoId
	 * @param authority
	 * @return
	 * @throws Exception
	 */
	List<Function> listFunctionByRoId(Authority authority) throws Exception;

	/**
	 * 获得父id为0的所有子菜单
	 * @param function
	 * @return
	 * @throws Exception
	 */
	List<Function> listSubFunctionForAuthority(Function function) throws Exception;
	
	/**
	 * listFunctionByFidIn
	 * @param fid
	 * @return
	 * @throws Exception
	 */
	List<Function> listFunctionByIdIn(@Param(value="fid") String fid) throws Exception;
	
	/**
	 * listFunctionByRoleId
	 * @param authority
	 * @return
	 * @throws Exception
	 */
	List<Function> listFunctionByRoleId(Authority authority) throws Exception;
}
