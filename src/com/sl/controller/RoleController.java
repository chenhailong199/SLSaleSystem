package com.sl.controller;

import java.util.Date;
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
import com.sl.pojo.Role;
import com.sl.pojo.User;
import com.sl.service.role.RoleService;
import com.sl.service.user.UserService;




/**
 * RoleController
 * @author chen
 *
 */
@Controller
public class RoleController extends BaseController{
private Logger logger = Logger.getLogger(RoleController.class);
	
	@Resource
	private RoleService roleService;
	@Resource
	private UserService userService;
	
	/**
	 * /backend/rolelist.html
	 * 加载角色列表
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/backend/rolelist.html")
	public ModelAndView roleList(HttpSession session,Model model){
		@SuppressWarnings("unchecked")
		Map<String,Object> baseModel= (Map<String,Object>)session.getAttribute(SLConstants.SESSION_BASE_MODEL);
		if(baseModel == null){
			return new ModelAndView("redirect:/");
		}else{
			List<Role> roleList = null;
			try {
				roleList = roleService.listRole();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				roleList = null;
			}
			model.addAllAttributes(baseModel);
			model.addAttribute(roleList);
			return new ModelAndView("/backend/rolelist");
		}
	}
	
	/**
	 * /backend/addRole.html
	 * 新增角色
	 * @param session
	 * @param role
	 * @return
	 */
	@RequestMapping("/backend/addRole.html")
	@ResponseBody
	public Object addRole(HttpSession session, @RequestParam String roleCode, @RequestParam String roleName){
		logger.info("/backend/addRole.html----------->"+roleName);
		if(null == roleCode || "".equals(roleCode)){
			return "nodata";
		}else{
			Role newRole = new Role();
			newRole.setRoleCode(roleCode);
			newRole.setRoleName(roleName);
			newRole.setCreateDate(new Date());
			newRole.setIsStart(1);
			newRole.setCreatedBy(((User)session.getAttribute(SLConstants.SESSION_USER)).getLoginCode());
			try {
				if(roleService.getRoleR(newRole) !=  null){
					return "rename";
				}else{
					roleService.saveRole(newRole);
				}
			} catch (Exception e) {
				//新增角色保存失败
				return "failed";
			}
			return "success";
		}
	}
	
	/**
	 * /backend/modifyRole.html
	 * 修改角色,需使用事务
	 * @param session
	 * @param role
	 * @return
	 */
	@RequestMapping("/backend/modifyRole.html")
	@ResponseBody
	public Object modifyRole(HttpSession session,@RequestParam String role){	
		if(null == role || "".equals(role)){
			return "nodata";
		}else{
			Role newRole = JSON.parseObject(role, Role.class);
			newRole.setCreateDate(new Date());
			newRole.setCreatedBy(((User)session.getAttribute(SLConstants.SESSION_USER)).getLoginCode());
			try {
				roleService.tm_modifyRole(newRole);
			} catch (Exception e) {
				// 角色修改失败
				return "failed";
			}
			return "success";
		}	
	}
	
	/**
	 * 修改角色状态
	 * @param session
	 * @param role
	 * @return
	 */
	@RequestMapping("/backend/modifyRoleStatus.html")
	@ResponseBody
	public Object modifyRoleStatus(HttpSession session, @RequestParam String role){	
		logger.info("modifyRoleStatus------------------>"+role.toString());
		if(null == role || "".equals(role)){
			return "nodata";
		}else{
			Role newRole = JSON.parseObject(role, Role.class);
			logger.info("newRole--------"+newRole.toString());
			if (newRole.getIsStart() == 1){
				newRole.setIsStart(0);
			} else {
				newRole.setIsStart(1);
			}
			try {
				roleService.updateRole(newRole);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "failed";
			}
			return "success";
		}	
	}
	
	/**
	 * 删除角色
	 * @param session
	 * @param role
	 * @return
	 */
	@RequestMapping("/backend/delRole.html")
	@ResponseBody
	public Object delRole(HttpSession session,@RequestParam String role){
	    logger.info("delRole------------>"+role.toString());
		if(null == role || "".equals(role)){
			return "nodata";
		}else{
			Role delRole = JSON.parseObject(role, Role.class);
			try {
				User user = new User();
				List<User> uList = null;
				user.setRoleId(delRole.getId());
				uList = userService.listUserBySearch(user);
				if(uList == null || uList.size() == 0){
					roleService.removeRole(delRole);
				}else{
					String flag = "";
					for(int i = 0; i < uList.size(); i++){
						flag += uList.get(i).getLoginCode();
						flag += ","; 
					logger.info(flag);
					}
					return flag;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return "failed";
			}
			return "success";
		}
	}
	
}
