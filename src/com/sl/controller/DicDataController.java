package com.sl.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sl.common.SLConstants;
import com.sl.pojo.DataDictionary;
import com.sl.service.datadictionary.DataDictionaryService;

import net.sf.json.JSONObject;


@Controller
public class DicDataController extends BaseController {
	@Resource
	private DataDictionaryService dataDictionaryService;
	
	/**
	 * 转到数据字典主页
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/backend/dicmanage.html")
	public ModelAndView dataDic(HttpSession session,Model model){
		@SuppressWarnings("unchecked")
		Map<String,Object> baseModel= (Map<String,Object>)session.getAttribute(SLConstants.SESSION_BASE_MODEL);
		if (baseModel == null){
			return new ModelAndView("redirect:/");
		} else {
			List<DataDictionary> dataList = null;
			try {
				dataList = dataDictionaryService.listDataDictionariesCategory();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dataList = null;
			}
			baseModel.put("dataList", dataList);
			model.addAllAttributes(baseModel);
			return new ModelAndView("/backend/dicmanage");
		}
	}
	
	/**
	 * 数据字典类型代码唯一性验证
	 * @param typeCode
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/backend/typecodeisexit.html", produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public String typeCodeIsExit(@RequestParam(value="typeCode",required=false) String typeCode,
								  @RequestParam(value="id",required=false) String id){
		String result = "failed";
		DataDictionary dataDictionary = new DataDictionary();
		dataDictionary.setTypeCode(typeCode);
		if(!id.equals("-1"))
			dataDictionary.setId(Integer.valueOf(id));
		try {
			if(dataDictionaryService.typeCodeOrValueIdIsExit(dataDictionary) == 0)
				result = "only";
			else 
				result = "repeat";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}
		return result;
	}
	
	/**
	 * 获得当前数据字典信息
	 * @param tcode
	 * @return
	 */
	@RequestMapping(value = "/backend/getJsonDic.html", produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public String getJsonDic(@RequestParam String tcode){
		if(null == tcode || "".equals(tcode)){
			return "nodata";
		}else{
			try {
				List<DataDictionary> ddList = null;
				DataDictionary dataDictionary = new DataDictionary();
				dataDictionary.setTypeCode(tcode);
				ddList = dataDictionaryService.listDataDictionary(dataDictionary);
				if(null != ddList){
					return JSON.toJSONString(ddList);
				}else{
					return "nodata";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "failed";
			}
		}
	}
	
	/**
	 * 修改当前选中数据字典 类型的数值名称
	 * @param session
	 * @param olddic
	 * @param newdic
	 * @return
	 */
	@RequestMapping(value = "/backend/modifyDic.html", produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public Object modifyDic(@RequestParam String dicJson){
		if(null == dicJson || "".equals(dicJson)){
			return "nodata";
		}else{
			try {
				JSONObject dicObject = JSONObject.fromObject(dicJson);
				DataDictionary dataDictionary =  (DataDictionary)JSONObject.toBean(dicObject, DataDictionary.class);
				if(dataDictionaryService.typeCodeOrValueIdIsExit(dataDictionary) > 0){//valueId重名
			    	return "rename";
			    }else
			    	dataDictionaryService.updateDataDictionary(dataDictionary);
				return "success";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "failed";
			}
		}
	}
	
	/**
	 * 删除选中的类型信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/backend/delDic.html", produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public String delDic(@RequestParam Integer id){
		if(null == id || id<=0){
			return "nodata";
		}else{
			try {
				DataDictionary dataDictionary = new DataDictionary();
				dataDictionary.setId(id);
				dataDictionaryService.removeDataDictionary(dataDictionary);
				return "success";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "failed";
			}
		}
	}
	
	/**
	 * 修改类型代码及类型名称
	 * @param session
	 * @param olddic
	 * @param newdic
	 * @return
	 */
	@RequestMapping("/backend/updateDic.html")
	@ResponseBody
	public Object modifylDic(HttpSession session,@RequestParam String olddic,@RequestParam String newdic){
		if(null == newdic || "".equals(newdic) || "".equals(olddic) || "".equals(olddic)){
			return "nodata";
		}else{
			JSONObject newDicObject = JSONObject.fromObject(newdic);
			JSONObject oldDicObject = JSONObject.fromObject(olddic);
			DataDictionary newDataDictionary =  (DataDictionary)JSONObject.toBean(newDicObject, DataDictionary.class);
			DataDictionary oldDataDictionary =  (DataDictionary)JSONObject.toBean(oldDicObject, DataDictionary.class);		
			try {
				List<DataDictionary> ddList = null;
				//第一个typeName是新的typeCode 第二个typeCode是旧的typeCode
				DataDictionary _ddDataDictionary  = new DataDictionary();
				_ddDataDictionary.setTypeName(newDataDictionary.getTypeCode());
				_ddDataDictionary.setTypeCode(oldDataDictionary.getTypeCode());
				ddList = dataDictionaryService.listDataDictionariesNotIn(_ddDataDictionary);
				if(ddList !=null && ddList.size() > 0){//有重名
					return "rename";
				}else{
					newDataDictionary.setValueName(oldDataDictionary.getTypeCode());
					dataDictionaryService.updateDataDictionarys(newDataDictionary);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "failed";
			}
			return "success";
		}	
	}
	
	
	/**
	 * 删除 主数据类型 及子类型
	 * @param session
	 * @param dic
	 * @return
	 */
	@RequestMapping("/backend/delMainDic.html")
	@ResponseBody
	public Object delDic(HttpSession session,@RequestParam String dic){
		if(null == dic || "".equals(dic)){
			return "nodata";
		}else{
			JSONObject roleObject = JSONObject.fromObject(dic);
			DataDictionary dataDictionary =  (DataDictionary)JSONObject.toBean(roleObject, DataDictionary.class);
			try {
				dataDictionaryService.removeDataDictionary(dataDictionary);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "failed";
			}
			return "success";
		}
	}
	
	/**
	 * 新增 主数据类型
	 * @param session
	 * @param dic
	 * @return
	 */
	@RequestMapping("/backend/addDic.html")
	@ResponseBody
	public Object addDic(HttpSession session,@RequestParam String dic){
		if(null == dic || "".equals(dic)){
			return "nodata";
		}else{
			JSONObject roleObject = JSONObject.fromObject(dic);
			DataDictionary dataDictionary =  (DataDictionary)JSONObject.toBean(roleObject, DataDictionary.class);
			try {
				DataDictionary dataDictionarynew = new DataDictionary();
				dataDictionarynew.setTypeCode(dataDictionary.getTypeCode());
				List<DataDictionary> ddList = dataDictionaryService.listDataDictionary(dataDictionarynew);
				//typeCode 不能重复
				if(null != ddList && ddList.size() > 0){
					return "rename";
				}else{
					dataDictionarynew.setTypeCode(null);
					dataDictionarynew.setTypeName(dataDictionary.getTypeName());
					ddList = null;
					ddList = dataDictionaryService.listDataDictionary(dataDictionarynew);
					if(null != ddList  && ddList.size() > 0){
						return "rename";
					}else
						dataDictionaryService.saveDataDictionary(dataDictionary);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "failed";
			}
			return "success";
		}
	}
	
	/**
	 * 新增子数据类型
	 * @param session
	 * @param dic
	 * @return
	 */
	@RequestMapping("/backend/addDicSub.html")
	@ResponseBody
	public String addDicSub(HttpSession session,@RequestParam String dic){
		if(null == dic || "".equals(dic)){
			return "nodata";
		}else{
			JSONObject roleObject = JSONObject.fromObject(dic);
			DataDictionary dataDictionary =  (DataDictionary)JSONObject.toBean(roleObject, DataDictionary.class);
			try {
			    if(dataDictionaryService.typeCodeOrValueIdIsExit(dataDictionary) > 0){//valueId重名
			    	return "rename";
			    }else{
			    	int valueId = dataDictionaryService.maxValueId(dataDictionary)+1;
			    	dataDictionary.setValueId(valueId);
			    	dataDictionaryService.saveDataDictionary(dataDictionary);
			    }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "failed";
			}
			return "success";
		}
	}
	
	
	
	
}
