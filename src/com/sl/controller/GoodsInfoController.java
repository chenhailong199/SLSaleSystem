package com.sl.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;
import com.sl.common.PageSupport;
import com.sl.common.SLConstants;
import com.sl.common.SQLTools;
import com.sl.pojo.GoodsInfo;
import com.sl.service.goodsinfo.GoodsInfoService;


@Controller
public class GoodsInfoController extends BaseController{
	private Logger logger = Logger.getLogger(GoodsInfoController.class);
	@Resource
	private GoodsInfoService goodsInfoService;
	
	/**
	 * 获取商品管理主页(分页显示)
	 * @param session
	 * @param model
	 * @param currentpage
	 * @param s_goodsName
	 * @param s_state
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/backend/goodsinfolist.html")
	public ModelAndView getGoodsInfoList(HttpSession session,Model model,
			@RequestParam(value="currentPage",required=false)Integer currentPage,
			@RequestParam(value="s_goodsName",required=false) String s_goodsName, 
			@RequestParam(value="s_state",required=false) String s_state
			){
		@SuppressWarnings("unchecked")
		Map<String,Object> baseModel= (Map<String,Object>)session.getAttribute(SLConstants.SESSION_BASE_MODEL);
		if (baseModel == null){
			return new ModelAndView("redirect:/");
		} else {
			GoodsInfo goodsInfo = new GoodsInfo();
			if (null != s_goodsName)
				goodsInfo.setGoodsName("%"+SQLTools.transfer(s_goodsName)+"%");
			if (!StringUtils.isNullOrEmpty(s_state)){
				goodsInfo.setState(Integer.valueOf(s_state));
			} else {
				goodsInfo.setState(null);
			}
			PageSupport page = new PageSupport();
			try {
				page.setTotalCount(goodsInfoService.totalCount(goodsInfo));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				page.setTotalCount(0);
			}
			if (page.getTotalCount() > 0){
				if (currentPage != null){
					page.setCurrentPage(currentPage);
				}
				if (page.getCurrentPage() < 0){
					page.setCurrentPage(1);
				}
				if (page.getCurrentPage() > page.getPageCount()){
					page.setCurrentPage(page.getPageCount());
				}
				goodsInfo.setPageNo((page.getCurrentPage() - 1) * page.getPageSize());
				goodsInfo.setPageSize(page.getPageSize());
				List<GoodsInfo> goodsList = null;
				try {
					goodsList = goodsInfoService.listGoodsInfoByPage(goodsInfo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					goodsList = null;
					if(page == null){
						page = new PageSupport();
						page.setItems(null);
					}
				}
				page.setItems(goodsList);
			} else {
				page.setItems(null);
			}
			model.addAllAttributes(baseModel);
			model.addAttribute("page", page);
			model.addAttribute("s_goodsName", s_goodsName);
			model.addAttribute("s_state", s_state);
			return new ModelAndView("/backend/goodsinfolist");
		}	
	}
	
	
	/**
	 * 增加商品信息
	 * @param session
	 * @param addGoodsInfo
	 * @return
	 */
	@RequestMapping(value="/backend/addgoodsinfo.html")
	public ModelAndView addGoodsInfo(HttpSession session, @ModelAttribute("addGoodsInfo") GoodsInfo addGoodsInfo){
		if (session.getAttribute(SLConstants.SESSION_BASE_MODEL) == null){
			return new ModelAndView("redirect:/");
		} else{
			addGoodsInfo.setCreateTime(new Date());
			addGoodsInfo.setCreatedBy(this.getCurrentUser().getLoginCode());
			addGoodsInfo.setLastUpdateTime(new Date());
			try {
				goodsInfoService.saveGoodsInfo(addGoodsInfo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ModelAndView("redirect:/backend/goodsinfolist.html");
	}
	
	/**
	 * 增加/修改 商品信息时,商品编号唯一性验证
	 * @param goodsSN
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/backend/goodsSNisexit.html", produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public String loginCodeIsExit(@RequestParam(value="goodsSN",required=false) String goodsSN,
								  @RequestParam(value="id",required=false) String id){
		String result = "failed";
		GoodsInfo goodsInfo = new GoodsInfo();
		goodsInfo.setGoodsSN(goodsSN);
		if(!"-1".equals(id))
			goodsInfo.setId(Integer.valueOf(id));
		try {
			if(goodsInfoService.goodsSNIsExit(goodsInfo) == 0)
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
	 * 查看商品信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/backend/getgoodsinfo.html", produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public Object getGoodsInfo(@RequestParam(value="id",required=false) String id){
		String json = "";
		if(null == id || "".equals(id)){
			return "nodata";
		}else{
			try {
				GoodsInfo goodsInfo = new GoodsInfo();
				goodsInfo.setId(Integer.valueOf(id));
				goodsInfo = goodsInfoService.getGoodsInfoById(goodsInfo);
				json = JSON.toJSONString(goodsInfo);
				logger.info("goodsInfo------------->"+json);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "failed";
			}
			return json;
		}
	}
	
	/**
	 * 修改商品信息
	 * @param session
	 * @param modifyGoodsInfo
	 * @return
	 */
	@RequestMapping(value = "/backend/modifygoodsinfo.html",method=RequestMethod.POST)
	public ModelAndView modifyGoodsInfo(HttpSession session,@ModelAttribute("modifyGoodsInfo") GoodsInfo modifyGoodsInfo){
		if(session.getAttribute(SLConstants.SESSION_BASE_MODEL) == null){
			return new ModelAndView("redirect:/");
		}else{
			try {
				modifyGoodsInfo.setLastUpdateTime(new Date());
				goodsInfoService.updateGoodsInfo(modifyGoodsInfo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ModelAndView("redirect:/backend/goodsinfolist.html");
		}
	}
	
	/**
	 * 删除商品
	 * @param delId
	 * @return
	 */
	@RequestMapping(value = "/backend/delgoodsinfo.html", produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public String delGoodsInfo(@RequestParam(value="delId",required=false) String delId){
		String result= "false" ;
		GoodsInfo delGoodsInfo = new GoodsInfo();
		delGoodsInfo.setId(Integer.valueOf(delId));
		try {
			if(goodsInfoService.isExitInPack(delGoodsInfo) > 0){
				result = "isused";
			}else{
				if(goodsInfoService.removeGoodsInfo(delGoodsInfo) > 0)
					result = "success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 修改商品上下架信息
	 * @param session
	 * @param state
	 * @return
	 */
	@RequestMapping("/backend/modifystate.html")
	@ResponseBody
	public Object modifyState(HttpSession session, @RequestParam String state, @RequestParam String id){
		if(null == state || "".equals(state)){
			return "nodata";
		}else{
			GoodsInfo goodsInfo = new GoodsInfo();
			goodsInfo.setState(Integer.valueOf(state));
			goodsInfo.setId(Integer.valueOf(id));
			try {
				goodsInfoService.updateGoodsInfo(goodsInfo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return "failed";
			}
			return "success";
		}
	}
	
}
