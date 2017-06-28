package com.sl.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import com.sl.common.SLConstants;
import com.sl.pojo.User;

/**
 * 拦截器,未登录不允许访问
 * @author chen
 *
 */
public class SysInterceptor extends HandlerInterceptorAdapter{
	
	/*@Resource
	private RedisAPI redisAPI;*/

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		//String urlPath = request.getRequestURI();
		User user = (User) session.getAttribute(SLConstants.SESSION_USER);
		if (null == user){
			//返回登录首页
			response.sendRedirect("/");
			return false;
		} /*else { //查询redis 缓存
			   String keyString = "Role"+user.getRoleId() + "UrlList";
			   String urlsString = "url:"+redisAPI.get(keyString);
			   if(null != urlsString && !"".equals(urlsString) && urlsString.indexOf(urlPath) > 0){
				   return true;  
			   }else {
				   response.sendRedirect("/401.html");
				   return false;
			}
		}*/
		return true;
	}
	
	
}
