package com.sl.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sl.common.SLConstants;
import com.sl.pojo.Authority;
import com.sl.pojo.Function;
import com.sl.pojo.Menu;
import com.sl.pojo.User;
import com.sl.service.function.FunctionService;
import com.sl.service.user.UserService;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class LoginController extends BaseController{
	
	private Logger logger = Logger.getLogger(LoginController.class);
	
	@Resource
	private UserService userService;
	/*@Resource
	private RedisAPI redisAPI;*/
	@Resource
	private FunctionService functionService;

	@RequestMapping("/main.html")
	public ModelAndView main(HttpSession session){
		logger.info("main---------------------->");
		User currentUser = this.getCurrentUser();
		/*根据当前用户角色ID返回相应的功能菜单 menu list*/
		List<Menu> mList = null;
		if (null != currentUser){
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("currentUser", currentUser);
			/**
			 * 如果redis里有数据,直接从redis里取
			 * 如果redis里没有数据,从数据库取,再保存到redis
			 * key:menuList+roleId -- eg:"menuList2"
			 * value:mList
			 * redis -- start redis未安装
			 * */
	/*		if (!redisAPI.exists("menuList"+currentUser.getRoleId())){
				//redis没有数据,从数据库取
				mList = getFunctionByCurrentUser(currentUser.getRoleId());
				if (null != mList){
					String json = JSON.toJSONString(mList);
					System.out.println("from mysql" + json);
					model.put("mList", json);
					//将mList存入redis
					redisAPI.set("menuList"+currentUser.getRoleId(), json);
				}
			} else {
				//redis 里有数据,直接从redis取
				String redisMenuList = redisAPI.get("menuList"+currentUser.getRoleId());
				System.out.println("from redis" + redisMenuList);
				if (null != redisMenuList && !"".equals(redisMenuList)){
					model.put("mList", redisMenuList);
				} else {
					return  new ModelAndView ("redirect:/");
				}				
			}
			if (!redisAPI.exit("Role"+user.getRoleId()+"UrlList")){
				//get all role url list to redis
				Authority authority = new Authority();
				authority.setRoleId(Integer.valueOf(idsArrayStrings[0]));
				List<Function> functionList = functionService.listFunctionByRoId(authority);
				if(null != functionList || functionList.size() >= 0){
					StringBuffer sBuffer = new StringBuffer();
					for(Function f:functionList){
						sBuffer.append(f.getFuncUrl());
					}
					redisAPI.set("Role"+user.getRoleId()+"UrlList", sBuffer.toString());
				}
			}	
			session.setAttribute(SLConstants.SESSION_BASE_MODEL, model);
			return new ModelAndView("main",model);*/
			/**
			 * redis -- end
			 * */
		   mList = getFunctionByCurrentUser(currentUser.getRoleId());
			if (null != mList){
				String json = JSON.toJSONString(mList);
				System.out.println(json);
				model.put("mList", json);
				session.setAttribute(SLConstants.SESSION_BASE_MODEL, model);
				return new ModelAndView("main",model);
			}
		}
		return  new ModelAndView ("redirect:/");
	}
	
	@RequestMapping("/login.html")
	@ResponseBody
	public Object login(HttpSession session,@RequestParam String user){
		logger.info("userLogin---------------------->"+user.toString());
		if(null == user || "".equals(user)){
			return "nodata";
		}else{
			JSONObject userObject = JSONObject.fromObject(user);
			User userObj =  (User)JSONObject.toBean(userObject, User.class);
			try {
				if(userService.loginCodeIsExit(userObj) ==  0){//不存在这个登陆账号
					return "nologincode";
				}else{
					User _user = userService.getLoginUser(userObj);
					if(null != _user){
						session.setAttribute(SLConstants.SESSION_USER, _user);
						User updateLoginTimeUser = new User();
						updateLoginTimeUser.setId(_user.getId());
						updateLoginTimeUser.setLastLoginTime(new Date());
						userService.updateUser(updateLoginTimeUser);
						updateLoginTimeUser = null;
						return "success";
					}else {
						return "pwderror";
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return "failed";
			}
		}
	}
	
	@RequestMapping("/logout.html")
	public String logout(HttpSession session){
		session.removeAttribute(SLConstants.SESSION_USER);
		session.invalidate();
		this.setCurrentUser(null);
		return "index";
	}
	
	
	/**
	 * 根据当前用户的角色id得到功能列表（对应菜单）
	 */
	protected List<Menu> getFunctionByCurrentUser(int roleId){
		List<Menu> menuList = new ArrayList<Menu>();
		Authority authority = new Authority();
		authority.setRoleId(roleId);
		try {
			
			List<Function> mList = functionService.listMainFunction(authority);
			if(null != mList){
				for (Function function : mList) {
					Menu menu = new Menu();
					menu.setMainMenu(function);
					function.setRoleId(roleId);
					List<Function> subList = functionService.listSubFunction(function);
					if(null != subList)
						menu.setSubMenu(subList);
					menuList.add(menu);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuList;
	}
	
	@RequestMapping("/regsuccess.html")
	public ModelAndView regSuccess(User user){
		int result;
		try {
			result = userService.saveUser(user);
		
			if(result > 0){
				user = userService.getLoginUser(user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("regsuccess");
	}
	
	/**
	 * 没有访问权限
	 * @param user
	 * @return
	 */
	@RequestMapping("/401.html")
	public ModelAndView noRole(User user){
		return new ModelAndView("401");
	}


}
