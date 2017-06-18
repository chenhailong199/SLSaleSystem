package com.sl.controller.user;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sl.common.SLConstants;
import com.sl.pojo.user.User;

public class BaseController {
	//private Logger logger  = Logger.getLogger(BaseController.class);
	
	private User currentUser;

	public User getCurrentUser() {
		if (null == this.currentUser){
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();    
			HttpSession session = request.getSession(false);
			if (null != session){
				currentUser= (User)session.getAttribute(SLConstants.SESSION_USER);		
			} else {
				currentUser = null;
			}
		}
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	/**
	 * 日期国际化
	 * 
	 */
	@org.springframework.web.bind.annotation.InitBinder
	public void InitBinder(WebDataBinder dataBinder){
		dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport(){
			/*匿名内部类*/
			public void setAsText(String value){
				try {
					setValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					setValue(null);
				}
			}
			public String getAsText(){
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)getValue());
			}
		});
	}
}
