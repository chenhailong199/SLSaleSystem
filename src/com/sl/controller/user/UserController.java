package com.sl.controller.user;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.sl.common.SLConstants;
import com.sl.pojo.Authority;
import com.sl.pojo.Function;
import com.sl.pojo.Menu;
import com.sl.pojo.user.User;
import com.sl.service.function.FunctionService;
import com.sl.service.user.UserService;


@Controller
public class UserController extends BaseController{
	private Logger logger  = Logger.getLogger(UserController.class);
	@Resource
	private UserService userService;
	@Resource
	private FunctionService functionService;
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
		user.setStatus(1);
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
	
	
	@RequestMapping(value="/getRegister.html")
	public String getRegister(){
		return "userRegister";
	}
	@RequestMapping(value="userRegister.do")
	public String userRegister(User user, Model model){	
		try {
			int rows = userService.saveUser(user);
			if (rows > 0){
				user = userService.getLoginUser(user);
				model.addAttribute("user", user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "registerSuccess";
	}
	
	@RequestMapping(value="/getExit.html")
	public String getExit(){
		return "userExit";
	}

}
