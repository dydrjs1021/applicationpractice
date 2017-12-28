package kr.or.nextit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.nextit.member.model.Member;

public interface MemberService {

	public List<Member> getMemberList(Map<String, Object> paramMap) throws Exception;
	
	public Member getMember(String mem_id) throws Exception;
	
	public int insertMember(Member member) throws Exception;
	
	public int updateMember(Member member) throws Exception;
	
	public int deleteMember(String mem_id) throws Exception;
}
