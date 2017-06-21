package com.sl.service.datadictionary;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.datadictionary.DataDictionaryMapper;
import com.sl.pojo.DataDictionary;
@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {
	@Resource
	private DataDictionaryMapper dataDictionaryMapper;
	@Override
	public List<DataDictionary> listDataDictionary(DataDictionary dataDictionary) {
		// TODO Auto-generated method stub
		return dataDictionaryMapper.listDataDictionary(dataDictionary);
	}

}
