package com.sl.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;
import com.sl.common.PageSupport;
import com.sl.common.SLConstants;
import com.sl.common.SQLTools;
import com.sl.pojo.DataDictionary;
import com.sl.pojo.GoodsInfo;
import com.sl.pojo.GoodsPack;
import com.sl.pojo.GoodsPackAffiliated;
import com.sl.pojo.User;
import com.sl.service.datadictionary.DataDictionaryService;
import com.sl.service.goodsinfo.GoodsInfoService;
import com.sl.service.goodspack.GoodsPackService;
import com.sl.service.goodspackaffiliated.GoodsPackAffiliatedService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
public class GoodsPackControoler extends BaseController {
	@Resource
	private GoodsPackService goodsPackService;
	@Resource
	private DataDictionaryService dataDictionaryService;
	@Resource
	private GoodsPackAffiliatedService goodsPackAffiliatedService;
	@Resource
	private GoodsInfoService goodsInfoService;

	/**
	 * 获取列表（分页）
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/backend/goodspacklist.html")
	public ModelAndView goodsPackList(HttpSession session,Model model,
								@RequestParam(value="currentpage",required=false)Integer currentpage ,
								@RequestParam(value="s_goodsPackName",required=false) String s_goodsPackName, 
								@RequestParam(value="s_typeId",required=false) String s_typeId, 
								@RequestParam(value="s_state",required=false) String s_state
								){
		@SuppressWarnings("unchecked")
		Map<String,Object> baseModel= (Map<String,Object>)session.getAttribute(SLConstants.SESSION_BASE_MODEL);
		if (baseModel == null){
			return new ModelAndView("redirect:/");
		} else {
			DataDictionary dataDictionary = new DataDictionary();
			dataDictionary.setTypeCode("PACK_TYPE");
			List<DataDictionary> packTypeList = null;
			try {
				packTypeList = dataDictionaryService.listDataDictionary(dataDictionary);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			GoodsPack goodsPack = new GoodsPack();
			if (null != s_goodsPackName)
				goodsPack.setGoodsPackName("%"+SQLTools.transfer(s_goodsPackName)+"%");
			if (!StringUtils.isNullOrEmpty(s_state)){
				goodsPack.setState(Integer.valueOf(s_state));
			} else {
				goodsPack.setState(null);
			} 	
			if (!StringUtils.isNullOrEmpty(s_typeId)){
				goodsPack.setTypeId(Integer.valueOf(s_typeId));
			} else {
				goodsPack.setTypeId(null);
			}	
			//pages 
			PageSupport page = new PageSupport();
			try{
				page.setTotalCount(goodsPackService.totalCount(goodsPack));
			}catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
				page.setTotalCount(0);
			}
			if (page.getTotalCount() > 0){
				if (currentpage != null)
					page.setCurrentPage(currentpage);
				if (page.getCurrentPage() <= 0)
					page.setCurrentPage(1);
				if (page.getCurrentPage() > page.getPageCount())
					page.setCurrentPage(page.getPageCount());
				goodsPack.setPageNo((page.getCurrentPage() - 1) * page.getPageSize());
				goodsPack.setPageSize(page.getPageSize());
				List<GoodsPack> goodsPackList = null;
				try {
					goodsPackList = goodsPackService.listGoodsPackByPage(goodsPack);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					goodsPackList = null;
					if (page == null){
						page = new PageSupport();
						page.setItems(null);
					}
				}
				page.setItems(goodsPackList);
			} else {
				page.setItems(null);
			}
			model.addAllAttributes(baseModel);
			model.addAttribute("page", page);
			model.addAttribute("packTypeList", packTypeList);
			model.addAttribute("s_goodsPackName", s_goodsPackName);
			model.addAttribute("s_typeId", s_typeId);
			model.addAttribute("s_state", s_state);
			return new ModelAndView("/backend/goodspacklist");
		}
	}
	
	/**
	 * 增加商品套餐
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/backend/addgoodspack.html",method=RequestMethod.GET)
	public ModelAndView addGoodsPack(Model model,HttpSession session){
		@SuppressWarnings("unchecked")
		Map<String,Object> baseModel= (Map<String,Object>)session.getAttribute(SLConstants.SESSION_BASE_MODEL);
		if (baseModel == null){
			return new ModelAndView("redirect:/");
		} else {
			//显示套餐类型
			DataDictionary dataDictionary = new DataDictionary();
			dataDictionary.setTypeCode("PACK_TYPE");
			List<DataDictionary> packTypeList = null;
			try {
				packTypeList = dataDictionaryService.listDataDictionary(dataDictionary);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			model.addAllAttributes(baseModel);
			model.addAttribute("packTypeList", packTypeList);
			return new ModelAndView("/backend/addgoodspack");
		}
	}
	
	/**
	 * 保存增加的商品套餐
	 * @param session
	 * @param addGoodsPack
	 * @return
	 */
	@RequestMapping(value = "/backend/saveaddgoodspack.html",method=RequestMethod.POST)
	public ModelAndView saveAddGoodsPack(HttpSession session,@ModelAttribute("addGoodsPack") GoodsPack addGoodsPack){
		if (session.getAttribute(SLConstants.SESSION_BASE_MODEL) == null){
			return new ModelAndView("redirect:/");
		} else {
			try {
				User user = (User)session.getAttribute(SLConstants.SESSION_USER);
				List<GoodsPackAffiliated> GPAList = null;
				GPAList = getJavaCollection(new GoodsPackAffiliated(),addGoodsPack.getGoodsJson());
				addGoodsPack.setCreateTime(new Date());
				addGoodsPack.setCreatedBy(user.getLoginCode());
				addGoodsPack.setLastUpdateTime(new Date());
				goodsPackService.tm_addGoodsPack(addGoodsPack,GPAList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ModelAndView("redirect:/backend/goodspacklist.html");
		}
	}
	/**
	 * 检验商品套餐编号是否存在
	 * @param goodsPackCode
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/backend/goodspackcodeisexit.html", produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public String goodsPackCodeIsExit(@RequestParam(value="goodsPackCode",required=false) String goodsPackCode,
		   @RequestParam(value="id",required=false) String id){
		String result = "failed";
		GoodsPack goodsPack = new GoodsPack();
		goodsPack.setGoodsPackCode(goodsPackCode);
		if(!id.equals("-1"))
			goodsPack.setId(Integer.valueOf(id));
		try {
			if(goodsPackService.goodsPackCodeIsExit(goodsPack) == 0)
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
	 * 查看商品套餐信息
	 * @param model
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/backend/viewgoodspack.html",method=RequestMethod.GET)
	public ModelAndView viewGoodsPack(Model model,HttpSession session,@RequestParam(value="id",required=false) String id){
		@SuppressWarnings("unchecked")
		Map<String,Object> baseModel= (Map<String,Object>)session.getAttribute(SLConstants.SESSION_BASE_MODEL);
		if (baseModel == null){
			return new ModelAndView("redirect:/");
		} else {
			if (null == id || id.equals("")){
				return new ModelAndView("redirect:/backend/goodspacklist.html");
			}
			DataDictionary dataDictionary = new DataDictionary();
			dataDictionary.setTypeCode("PACK_TYPE");
			List<DataDictionary> packTypeList = null;
			List<GoodsPackAffiliated> goodsList = null;
			GoodsPackAffiliated goodsPackAffiliated = new GoodsPackAffiliated();
			goodsPackAffiliated.setGoodsPackId(Integer.valueOf(id));
			try {
				packTypeList = dataDictionaryService.listDataDictionary(dataDictionary);
				goodsList = goodsPackAffiliatedService.listgoodsPackAffiliated(goodsPackAffiliated);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			GoodsPack goodsPack = new GoodsPack();
			goodsPack.setId(Integer.valueOf(id));
			try {
				goodsPack = goodsPackService.getGoodsPackById(goodsPack);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAllAttributes(baseModel);
			model.addAttribute("packTypeList", packTypeList);
			model.addAttribute("goodsPack", goodsPack);
			model.addAttribute("goodsList", goodsList);
			return new ModelAndView("/backend/viewgoodspack");
		}
	}
	
	/**
	 * 跳转至修改商品套餐页面
	 * @param model
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/backend/modifygoodspack.html",method=RequestMethod.GET)
	public ModelAndView modifyGoodsPack(Model model,HttpSession session,@RequestParam(value="id",required=false) String id){
		@SuppressWarnings("unchecked")
		Map<String,Object> baseModel= (Map<String,Object>)session.getAttribute(SLConstants.SESSION_BASE_MODEL);
		if (baseModel == null){
			return new ModelAndView("redirect:/");
		} else {
			if (null == id || id.equals("")){
				return new ModelAndView("redirect:/backend/goodspacklist.html");
			}
			DataDictionary dataDictionary = new DataDictionary();
			dataDictionary.setTypeCode("PACK_TYPE");
			List<DataDictionary> packTypeList = null;
			List<GoodsPackAffiliated> goodsList = null;
			GoodsPackAffiliated goodsPackAffiliated = new GoodsPackAffiliated();
			goodsPackAffiliated.setGoodsPackId(Integer.valueOf(id));
			try {
				packTypeList = dataDictionaryService.listDataDictionary(dataDictionary);
				goodsList = goodsPackAffiliatedService.listgoodsPackAffiliated(goodsPackAffiliated);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			GoodsPack goodsPack = new GoodsPack();
			goodsPack.setId(Integer.valueOf(id));
			try {
				goodsPack = goodsPackService.getGoodsPackById(goodsPack);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAllAttributes(baseModel);
			model.addAttribute("packTypeList", packTypeList);
			model.addAttribute("goodsList", goodsList);
			model.addAttribute("goodsPack", goodsPack);
			return new ModelAndView("/backend/modifygoodspack");
		}
	}
	
	/**
	 * 保存修改的商品套餐
	 * @param session
	 * @param modifyGoodsPack
	 * @return
	 */
	@RequestMapping(value = "/backend/savemodifygoodspack.html",method=RequestMethod.POST)
	public ModelAndView saveModifyGoodsPack(HttpSession session,@ModelAttribute("modifyGoodsPack") GoodsPack modifyGoodsPack){
		if(session.getAttribute(SLConstants.SESSION_BASE_MODEL) == null){
			return new ModelAndView("redirect:/");
		}else{
			try {
				List<GoodsPackAffiliated> GPAList = null;
				GPAList = getJavaCollection(new GoodsPackAffiliated(),modifyGoodsPack.getGoodsJson());
				modifyGoodsPack.setLastUpdateTime(new Date());
				goodsPackService.tm_modifyGoodsPack(modifyGoodsPack,GPAList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ModelAndView("redirect:/backend/goodspacklist.html");
		}
	}
	
	/**
	 * 删除商品套餐
	 * @param delId
	 * @return
	 */
	@RequestMapping(value = "/backend/delgoodspack.html", produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public String delGoodsPack(@RequestParam(value="delId",required=false) String delId){
		
		String result= "false" ;
		GoodsPack delGoodsPack = new GoodsPack();
		delGoodsPack.setId(Integer.valueOf(delId));
		try {
			if (goodsPackService.removeGoodsPack(delGoodsPack) > 0){
				GoodsPackAffiliated goodsPackAffiliated = new GoodsPackAffiliated();
				goodsPackAffiliated.setGoodsPackId(Integer.valueOf(delId));
				goodsPackAffiliatedService.removeGoodsPackAffiliated(goodsPackAffiliated);
				result = "success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 修改商品套餐上下架状态
	 * @param session
	 * @param state
	 * @return
	 */
	@RequestMapping("/backend/modifygoodspackstate.html")
	@ResponseBody
	public Object modifyGoodsPackState(HttpSession session,@RequestParam String state){
		if(null == state || "".equals(state)){
			return "nodata";
		}else{
			JSONObject goodsPackObject = JSONObject.fromObject(state);
			GoodsPack stateObjGoodsPack =  (GoodsPack)JSONObject.toBean(goodsPackObject, GoodsPack.class);
			try {
				goodsPackService.updateGoodsPack(stateObjGoodsPack);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return "failed";
			}
			return "success";
		}
	}
	@RequestMapping("/backend/goodslist.html")
	public ModelAndView goodslist(HttpSession session, Model model, @RequestParam(value="s_goodsName", required=false) String s_goodsName){
		if (session.getAttribute(SLConstants.SESSION_BASE_MODEL) == null){
			return new ModelAndView("redirect:/");
		} else {
			List<GoodsInfo> goodsInfoList = null;
			GoodsInfo goodsInfo = new GoodsInfo();
			if (null != s_goodsName && !"".equals(s_goodsName)){
				goodsInfo.setGoodsName("%"+SQLTools.transfer(s_goodsName)+"%");
				try {
					goodsInfoList = goodsInfoService.listGoodsInfoByName(goodsInfo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			model.addAttribute("goodsInfoList", goodsInfoList);
			return new ModelAndView("/backend/goodslist");
		}
	}
	
	
	/**
     * 封装将json对象转换为java集合对象
     * @param <T>
     * @param clazz
     * @param jsons 
     * @return
     */
    private <T> List<T> getJavaCollection(T clazz, String jsons) {
        List<T> objs=null;
        JSONArray jsonArray=(JSONArray)JSONSerializer.toJSON(jsons);
        if(jsonArray.size() > 1){
            objs=new ArrayList<T>();
            @SuppressWarnings("rawtypes")
			List list=(List)JSONSerializer.toJava(jsonArray);
            for(int i = 0; i < list.size()-1; i++){
            	JSONObject jsonObject=JSONObject.fromObject(list.get(i));
            	@SuppressWarnings("unchecked")
				T obj=(T)JSONObject.toBean(jsonObject, clazz.getClass());
                objs.add(obj);
            }
        }
        return objs;
    }
    
    
    
}
