package com.sl.dao.function;

import java.util.List;

import com.sl.pojo.Authority;
import com.sl.pojo.Function;

public interface FunctionMapper {
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
}
