package com.sl.dao.datadictionary;

import java.util.List;

import com.sl.pojo.DataDictionary;

public interface DataDictionaryMapper {
	/**
	 * listDataDictionary
	 * @return
	 */
	List<DataDictionary> listDataDictionary(DataDictionary dataDictionary);
	
	/**
	 * listDataDictionariesNotIn
	 * sql:SELECT * FROM data_dictionary WHERE  typeCode = 'CARD_TYPE' 
	 *         AND typeCode NOT IN (SELECT typecode FROM data_dictionary 
	 *            WHERE typecode = 'INFO_TYPE')
	 * @param dataDictionary
	 * @return
	 * @throws Exception
	 */
	List<DataDictionary> listDataDictionariesNotIn(DataDictionary dataDictionary) throws Exception;
	/**
	 * listDataDictionariesCategory
	 * @param dataDictionary
	 * @return
	 * @throws Exception
	 */
	List<DataDictionary> listDataDictionariesCategory() throws Exception;
	
	
	/**
	 * saveDataDictionary
	 * @param dataDictionary
	 * @return int
	 */
	int saveDataDictionary(DataDictionary dataDictionary) throws Exception;
	
	/**
	 * updateDataDictionary
	 * @param dataDictionary
	 * @return int
	 */
	int updateDataDictionary(DataDictionary dataDictionary) throws Exception;
	/**
	 * updateDataDictionarys
	 * @param dataDictionary
	 * @return
	 * @throws Exception
	 */
	int updateDataDictionarys(DataDictionary dataDictionary) throws Exception;
	/**
	 * deleteDataDictionary
	 * @param dataDictionary
	 * @return
	 */
	int removeDataDictionary(DataDictionary dataDictionary) throws Exception;
	
	/**
	 * typeCodeOrValueIdIsExit
	 * @param dataDictionary
	 * @return
	 * @throws Exception
	 */
	int typeCodeOrValueIdIsExit(DataDictionary dataDictionary) throws Exception;
	/**
	 * maxValueId
	 * @param dataDictionary
	 * @return
	 * @throws Exception
	 */
	int maxValueId(DataDictionary dataDictionary) throws Exception;
	
}
