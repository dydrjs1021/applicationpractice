package kr.or.nextit.member.mapper;

import java.util.List;
import java.util.Map;

import kr.or.nextit.member.model.Member;

public interface MemberMapper {

	List<Member> selectMemberList(Map<String, Object> paramMap) throws Exception;
	
	Member selectMember(String mem_id) throws Exception;
	
	int insertMember(Member member) throws Exception;
	
	int updateMember(Member member) throws Exception;
	
	int deleteMember(String mem_id) throws Exception;
}
