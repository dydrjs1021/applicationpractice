package kr.or.nextit.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.nextit.web.servlet.Controller;

public class LoginFormController implements Controller{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "login/loginForm";
	}

}



