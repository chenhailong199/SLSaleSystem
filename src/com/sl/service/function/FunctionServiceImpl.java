package com.sl.service.function;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.function.FunctionMapper;
import com.sl.pojo.Authority;
import com.sl.pojo.Function;
@Service
public class FunctionServiceImpl implements FunctionService {
	@Resource
	private FunctionMapper functionMapper;
	@Override
	public List<Function> listMainFunction(Authority authority) throws Exception {
		// TODO Auto-generated method stub
		return functionMapper.listMainFunction(authority);
	}

	@Override
	public List<Function> listSubFunction(Function function) throws Exception {
		// TODO Auto-generated method stub
		return functionMapper.listSubFunction(function);
	}

}
