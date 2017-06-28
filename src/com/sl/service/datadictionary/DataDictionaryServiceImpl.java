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
	@Override
	public List<DataDictionary> listDataDictionariesNotIn(DataDictionary dataDictionary) throws Exception {
		// TODO Auto-generated method stub
		return dataDictionaryMapper.listDataDictionariesNotIn(dataDictionary);
	}
	@Override
	public List<DataDictionary> listDataDictionariesCategory() throws Exception {
		// TODO Auto-generated method stub
		return dataDictionaryMapper.listDataDictionariesCategory();
	}
	@Override
	public int saveDataDictionary(DataDictionary dataDictionary) throws Exception {
		// TODO Auto-generated method stub
		return dataDictionaryMapper.saveDataDictionary(dataDictionary);
	}
	@Override
	public int updateDataDictionary(DataDictionary dataDictionary) throws Exception {
		// TODO Auto-generated method stub
		return dataDictionaryMapper.updateDataDictionary(dataDictionary);
	}
	@Override
	public int updateDataDictionarys(DataDictionary dataDictionary) throws Exception {
		// TODO Auto-generated method stub
		return dataDictionaryMapper.updateDataDictionarys(dataDictionary);
	}
	@Override
	public int removeDataDictionary(DataDictionary dataDictionary) throws Exception {
		// TODO Auto-generated method stub
		return dataDictionaryMapper.removeDataDictionary(dataDictionary);
	}
	@Override
	public int typeCodeOrValueIdIsExit(DataDictionary dataDictionary) throws Exception {
		// TODO Auto-generated method stub
		return dataDictionaryMapper.typeCodeOrValueIdIsExit(dataDictionary);
	}
	@Override
	public int maxValueId(DataDictionary dataDictionary) throws Exception {
		// TODO Auto-generated method stub
		return dataDictionaryMapper.maxValueId(dataDictionary);
	}

}
