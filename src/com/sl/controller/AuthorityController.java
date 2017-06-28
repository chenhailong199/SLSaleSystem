package com.sl.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sl.common.SLConstants;
import com.sl.pojo.Authority;
import com.sl.pojo.Function;
import com.sl.pojo.Role;
import com.sl.pojo.RoleFunctions;
import com.sl.pojo.User;
import com.sl.service.authority.AuthorityService;
import com.sl.service.function.FunctionService;
import com.sl.service.role.RoleService;


@Controller
public class AuthorityController extends BaseController {
	private Logger logger = Logger.getLogger(AuthorityController.class);
	@Resource
	private RoleService roleService;
	@Resource
	private FunctionService functionService;
	@Resource
	private AuthorityService authorityService;
	/*@Resource
	private RedisAPI redisAPI;*/
	@Resource
	private LoginController loginController;
	
	/**
	 * /backend/authoritymanage.html
	 * 权限管理主页
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/backend/authoritymanage.html")
	public ModelAndView authorityManage(HttpSession session, Model model){
		@SuppressWarnings("unchecked")
		Map<String,Object> baseModel= (Map<String,Object>)session.getAttribute(SLConstants.SESSION_BASE_MODEL);	
		if(baseModel == null){
			return new ModelAndView("redirect:/");
		}else{
			List<Role> roleList = null;
			try {
				roleList = roleService.listRole();
			} catch (Exception e) {
				e.printStackTrace();
				roleList = null;
			}
			model.addAllAttributes(baseModel);
			model.addAttribute(roleList);
			return new ModelAndView("/backend/authoritymanage");
		}
	}
	
	/**
	 * /backend/functions.html
	 * 获得功能列表
	 * @return
	 */
	@RequestMapping(value = "/backend/functions.html", produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public String getfunctions(){
		String result = "nodata";
		Function function = new Function();
		try {
			function.setId(0);
			List<Function> fList = functionService.listSubFunctionForAuthority(function);
			List<RoleFunctions> rList = new ArrayList<RoleFunctions>();
			if(null != fList){
				for(Function func : fList){
					RoleFunctions rFunctions = new RoleFunctions();
					rFunctions.setMainFunction(func);
					rFunctions.setSubFunctions(functionService.listSubFunctionForAuthority(func));
					rList.add(rFunctions);
				}
				//result = JSONArray.fromObject(rList).toString();
				result = JSON.toJSONString(rList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("functions------------>"+result);
		return result;
	}
	/**
	 * 根据角色ID 和 功能id 回显已有的权限功能
	 * @param rid
	 * @param fid
	 * @return
	 */
	@RequestMapping(value = "/backend/getAuthorityDefault.html", produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public String getAuthorityDefault(@RequestParam Integer rid, @RequestParam Integer fid){
		logger.info("getAuthorityDefault---------->"+rid+"\t"+fid);
		String result = "nodata";
		Authority authority = new Authority();
		authority.setRoleId(rid);
		authority.setFunctionId(fid);
		try {
			//判断 rid fid 是否存在
			if(authorityService.getAuthorityById(authority) != null){
				result =  "success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "failed";
		}
		return result;
	}
	
	/**
	 * 修改角色权限
	 * @param session
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/backend/modifyAuthority.html", produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public Object modifyAuthority(HttpSession session, @RequestParam String ids){
		String result = "nodata";
		try {
			if(null != ids){
				String[] idsArrayStrings = StringUtils.split(ids, "-");
				logger.info("ids------------->"+ids.toString());
				if(idsArrayStrings.length > 0){
					User user = this.getCurrentUser();
					/**
					 * 权限表更新操作 au_authority
					 * 先把此角色下所有功能删除,再根据 functionIds 重新授权
					 * */
					authorityService.tm_saveAuthority(idsArrayStrings, user.getLoginCode());
					/*获取功能列表,存入redis*/
					/*List<Menu> mList = null; 
					mList = loginController.getFunctionByCurrentUser(Integer.valueOf(idsArrayStrings[0]));
					String json = JSON.toJSONString(mList);	
					redisAPI.set("menuList"+idsArrayStrings[0], json);
					//get all role url list to redis
					Authority authority = new Authority();
					authority.setRoleId(Integer.valueOf(idsArrayStrings[0]));
					List<Function> functionList = functionService.listFunctionByRoId(authority);
					if(null != functionList || functionList.size() >= 0){
						StringBuffer sBuffer = new StringBuffer();
						for(Function f:functionList){
							sBuffer.append(f.getFuncUrl());
						}
						redisAPI.set("Role"+idsArrayStrings[0]+"UrlList", sBuffer.toString());
					}*/
					/*获取功能列表,存入redis 结束*/
					result = "success";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
