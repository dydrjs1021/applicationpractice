package kr.or.nextit.login;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.nextit.member.model.Member;
import kr.or.nextit.member.service.MemberService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	MemberService memberService;
		
	@RequestMapping("/login")
	public String login(
			@RequestParam(value="mem_id", required=true) String mem_id,
			@RequestParam("mem_pwd") String mem_pwd,
			@RequestParam(value="remember_me", required=false) String remember_me,
			HttpSession session,
			HttpServletResponse response,
			Model model
			) throws Exception {
		
		Member member = memberService.getMember(mem_id);
		
		boolean isError = true;
		String message = "";
		
		if(member != null) {
			if(mem_pwd.equals(member.getMem_pwd())) {
				// 로그인 성공
				session.setAttribute("LOGIN_USER", member);
				message = member.getMem_name() + "님, 환영합니다.";
				isError = false;
				
			}else {
				message = "비밀번호가 일치하지 않습니다.";
			}
			
		}else {
			message = "해당 아이디가 존재하지 않습니다.";
		}
		
		model.addAttribute("isError", isError);
		model.addAttribute("message", message);
		model.addAttribute("locationURL", "/");		
		
		return "common/message";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "login/loginForm";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
}






