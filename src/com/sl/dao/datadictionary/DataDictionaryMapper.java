package com.sl.dao.datadictionary;

import java.util.List;

import com.sl.pojo.DataDictionary;

public interface DataDictionaryMapper {
	/**
	 * listDataDictionary
	 * @return
	 */
	List<DataDictionary> listDataDictionary(DataDictionary dataDictionary);
}
