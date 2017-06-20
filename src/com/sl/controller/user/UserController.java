package com.sl.controller.user;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;
import com.sl.common.PageSupport;
import com.sl.common.RedisAPI;
import com.sl.common.SLConstants;
import com.sl.common.SQLTools;
import com.sl.pojo.Authority;
import com.sl.pojo.Function;
import com.sl.pojo.Menu;
import com.sl.pojo.role.Role;
import com.sl.pojo.user.User;
import com.sl.service.function.FunctionService;
import com.sl.service.role.RoleService;
import com.sl.service.user.UserService;


@Controller
public class UserController extends BaseController{
	private Logger logger  = Logger.getLogger(UserController.class);
	@Resource
	private UserService userService;
	@Resource
	private FunctionService functionService;
	@Resource
	private RoleService roleService;
	/*@Resource
	private RedisAPI redisAPI;*/
	/**
	 * 获得登录页面
	 * @return
	 */
	@RequestMapping(value="/getLogin.html")
	public String getLogin(){
		return "userLogin";
	}
	
	/**
	 * 登录验证
	 * @param loginCode
	 * @param password
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/userLogin.do")
	@ResponseBody
	public String userLogin(@RequestParam String loginCode, @RequestParam String password, Model model, HttpSession session){
		User user = new User();
		user.setLoginCode(loginCode);
		user.setPassword(password);
		if (user.getLoginCode() == null || "".equals(user.getLoginCode())){
			return "nodata";
		} else {
			try {
				if (userService.loginCodeIsExit(user) == 0){
					//不存在这个登录账号
					return "nologinCode";
				} else {
					User loginUser = userService.getLoginUser(user);
					if (loginUser != null){
						//登录成功,存到session中
						session.setAttribute(SLConstants.SESSION_USER, loginUser);
						//更新当前用户登录的lastLoginTime
						User updateUser = new User();
						updateUser.setId(loginUser.getId());
						Date date = new Date();
						Timestamp tamp = new Timestamp(date.getTime());
						updateUser.setLastLoginTime(tamp);
						updateUser.setStatus(1);//状态启用
						userService.updateUser(updateUser);
						updateUser = null; //置为空,不再使用
						return "success";
					} else {
						//密码错误
						return "pwdError";
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "failed";
			}
		}
	}
	
	/**
	 * 登录成功,转到主页
	 * @return
	 */
	@RequestMapping(value="/main.html")
	public ModelAndView getMain(HttpSession session){
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
	
	/**
	 * 根据当前用户角色ID获取功能列表(对应的菜单)
	 * @param roleId
	 * @return
	 */
	protected List<Menu> getFunctionByCurrentUser(int roleId){
		List<Menu> menuList = new ArrayList<Menu>();
		Authority authority = new Authority();
		authority.setRoleId(roleId);
		try {
			//主菜单
			List<Function> mainList = functionService.listMainFunction(authority);
			if (mainList != null){
				for (Function function:mainList){
					Menu menu = new Menu();
					menu.setMainMenu(function);
					function.setRoleId(roleId);
					//子菜单
					List<Function> subList = functionService.listSubFunction(function);
					if (null != subList){
						menu.setSubMenu(subList);
					}
					menuList.add(menu);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuList;
	}
	
	/**
	 * 注销功能
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logOut.html")
	public String logout(HttpSession session){
		session.removeAttribute(SLConstants.SESSION_USER);
		session.invalidate();
		this.setCurrentUser(null);
		return "index";
	}
	/**
	 * 修改密码
	 * @param userJson (新密码,旧密码)
	 * @return
	 */
	@RequestMapping(value="/background/modifypwd.html")
	public String modifypwd(@RequestParam String newpwd, @RequestParam String oldpwd ){
		User currentUser = this.getCurrentUser();
		if (newpwd == null || "".equals(newpwd)){
			return "nodata";
		} else {
			User user = new User();
			user.setId(currentUser.getId());
			user.setLoginCode(currentUser.getLoginCode());
			user.setPassword(oldpwd);
			try {
				if (userService.getLoginUser(user) != null){
					//旧密码正确
					user.setPassword(newpwd);
					userService.updateUser(user);
				} else {//旧密码不正确
					return "oldpwdwrong";
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
	 * 查询用户列表
	 * @param model
	 * @param session
	 * @param s_loginCode
	 * @param s_referCode
	 * @param s_roleId
	 * @param s_status
	 * @return
	 */
	@RequestMapping(value="/background/userlist.html")
	public ModelAndView userList(Model model,HttpSession session,
			@RequestParam(value="s_loginCode",required=false) String s_loginCode,
			@RequestParam(value="s_referCode",required=false) String s_referCode,
			@RequestParam(value="s_roleId",required=false) String s_roleId,
			@RequestParam(value="s_status",required=false) String s_status,
			@RequestParam(value="currentPage",required=false,defaultValue="1")Integer currentPage){
		@SuppressWarnings("unchecked")
		Map<String, Object> baseModel = (Map<String, Object>) session.getAttribute(SLConstants.SESSION_BASE_MODEL);
		if (baseModel == null){
			
			return new ModelAndView("redirect:/");
		} else {
			/*添加rolelist*/
			List<Role> roleList = null;
			try {
				roleList = roleService.listRole();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*条件查询*/
			User user = new User();
			//账号
			if (s_loginCode != null){
				user.setLoginCode("%"+SQLTools.transfer(s_loginCode)+"%");
			}
			//推荐人
			if (s_referCode != null){
				user.setReferCode("%"+SQLTools.transfer(s_referCode)+"%");
			}
			//角色
			if (!StringUtils.isNullOrEmpty(s_roleId)){
				user.setRoleId(Integer.valueOf(s_roleId));
			} else {
				user.setStatus(null);
			}
			//状态
			if (!StringUtils.isNullOrEmpty(s_status)){
				user.setStatus(Integer.valueOf(s_status));
			} else {
				user.setStatus(null);
			}
			//page分页列表
			PageSupport page = new PageSupport();
			try {
				page.setTotalCount(userService.totalCount(user));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				page.setTotalCount(0);
			}
			if (page.getTotalCount() > 0){
				if (currentPage != null){
					if (currentPage <= 0){
						currentPage = 1;
					}
					if (currentPage > page.getPageCount()){
						currentPage = page.getPageCount();
					}
					page.setCurrentPage(currentPage);
				}
				//分页查询---limit ?,?
				user.setPageNo((page.getCurrentPage()-1)*page.getPageSize());
				user.setPageSize(page.getPageSize());
				List<User> userList = null;
				try {
					userList = userService.listUser(user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					userList = null;
					if (page == null){
						page = new PageSupport();
						page.setItems(null);
					}
				}
				page.setItems(userList);
				
			} else {
				page.setItems(null);
			}
			
			model.addAllAttributes(baseModel);
			model.addAttribute("roleList", roleList);
			model.addAttribute("page", page);
			model.addAttribute("s_loginCode", s_loginCode);
			model.addAttribute("s_referCode", s_referCode);
			model.addAttribute("s_roleId", s_roleId);
			model.addAttribute("s_status", s_status);
			return new ModelAndView("/backend/userlist");
		}
		
	}
	
	

}
