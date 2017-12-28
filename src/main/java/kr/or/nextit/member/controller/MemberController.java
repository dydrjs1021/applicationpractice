package kr.or.nextit.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.or.nextit.member.model.Member;
import kr.or.nextit.member.service.MemberService;

@Controller
@RequestMapping("/member")
@SessionAttributes("member")
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping("/memberList")
	public String memberList(
			HttpServletRequest request,
			@RequestParam(value="searchType", required=false, defaultValue="id") String searchType,
			@RequestParam(value="searchWord", required=false, defaultValue="") String searchWord,
			@RequestParam(value="currentPage", defaultValue="1") int currentPage,
			@RequestParam(value="pageSize", defaultValue="10") int pageSize,
			Model model
			) throws Exception{
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		if(StringUtils.isNotBlank(searchWord)) {
			paramMap.put("searchType", searchType);
			paramMap.put("searchWord", searchWord);
		}
		
		List<Member> memberList = memberService.getMemberList(paramMap);
		
		model.addAttribute("memberList", memberList);
		
		return "member/memberList";
	}
	
	@RequestMapping("/memberView")
	public String memberView(
			@RequestParam(value="mem_id", required=true) String mem_id,
			Model model
			) throws Exception {
		
		Member member = memberService.getMember(mem_id);
		
		model.addAttribute("member", member);
		
		return "member/memberView";
	}
	
	@RequestMapping("/memberForm")
	public String memberForm(
			@RequestParam(value="mem_id", required=false) String mem_id,
			HttpSession session,
			Model model) throws Exception {
		
		Member member = new Member();
		
		if(StringUtils.isNotBlank(mem_id)) {
			member = memberService.getMember(mem_id);
		}
		
		if(session.getAttribute("member") != null) {
			member = (Member)session.getAttribute("member");
		}
		
		model.addAttribute("member", member);
		
		return "member/memberForm";
	}
	
	@RequestMapping(value="/memberInsert", method=RequestMethod.POST)
	public String memberInsert(
			@ModelAttribute("member") Member member,
			SessionStatus sessionStatus,
			Model model) {
		
		String viewPage = "common/message";
		
		boolean isError = false;
		String message = "회원가입이 정상적으로 처리되었습니다.";
		
		if(member.getMem_id() == null || member.getMem_id().isEmpty()) {
			isError = true;
			message = "아이디를 입력하세요.";
		}
		
		if(StringUtils.isBlank(member.getMem_name())) {
			isError = true;
			message = "이름을 입력하세요";
			
			model.addAttribute("isError", isError);
			model.addAttribute("message", message);
			
			return "member/memberForm";
		}
		
		try {
			if(!isError) {
				int updCnt = memberService.insertMember(member);
				
				if(updCnt == 0) {
					message = "회원가입에 실패하였습니다.";
					isError = true;
				}
			}
		} catch(Exception e) {
			message = "회원가입에 실패하였습니다.";
			isError = true;
		}
		
		model.addAttribute("isError", isError);
		model.addAttribute("message", message);
		model.addAttribute("locationURL", "/member/memberList.do");
		
		return viewPage;
	}
	
	@RequestMapping(value="/memberUpdate", method=RequestMethod.POST)
	public String memberUpdate(
			@ModelAttribute("member") Member member,
			Model model
			) {
		
		String viewPage = "common/message";
		
		boolean isError = false;
		
		String message = "회원수정이 정상적으로 처리 되었습니다.";		

		if(!isError) {
			try{					
				int updCnt = memberService.updateMember(member); 
				
				if(updCnt == 0){
					// error.
					isError = true;
					message = "회원수정에 실패하였습니다.";
				}
					
				} catch(Exception e) {
					// error.
					isError = true;
					message = "회원수정에 실패하였습니다.";
				} 
		}
		
		model.addAttribute("isError", isError);
		model.addAttribute("message", message);
		model.addAttribute("locationURL", "/member/memberView.do?mem_id=" + member.getMem_id());
		
		return viewPage;

	}
	
	@RequestMapping("/memberDelete")
	public String memberDelete(
			@RequestParam(value="mem_id", required=true) String mem_id,
			Model model
			) {
		
		String viewPage = "common/message";
		
		boolean isError = false;

		String message = "회원삭제가 정상적으로 처리 되었습니다.";
		
		try{
			
			int updCnt = memberService.deleteMember(mem_id);
			
			if(updCnt == 0){
				message = "삭제에 실패하였습니다.";
				isError = true;
			}
			
		} catch(Exception e) {
			message = "삭제에 실패하였습니다.";
			isError = true;
		} 
		
		model.addAttribute("isError", isError);
		model.addAttribute("message", message);
		model.addAttribute("locationURL", "/member/memberList.do");
		
		return viewPage;
	}
	
	@RequestMapping("/memberExists")
	@ResponseBody		// response의 content 영역에 들어감.
	public Map<String, Object> memberExists(
			@RequestParam(value="mem_id", required=true) String mem_id
			) throws Exception {

		Member member = memberService.getMember(mem_id);
		
		Map<String, Object> resultMap = new HashMap<>();
		
		if(member != null) {
			resultMap.put("result", "true");
		} else {
			resultMap.put("result", "false");
		}
				
		return resultMap;
	}
}
