<%@page import="kr.or.nextit.member.service.impl.MemberServiceImpl"%>
<%@page import="kr.or.nextit.member.service.MemberService"%>
<%@page import="kr.or.nextit.member.model.Member"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">

<script type="text/javascript">
function fn_modi(mem_id){
	location.href="memberForm?mem_id=" + mem_id;
}

function fn_del(mem_id){
	location.href="memberDelete?mem_id="+ mem_id;
}

function fn_list(){
	location.href="memberList";	
}
</script>

<title>Insert title here</title>
</head>
<body>

<div class="container">

	<h2>회원 상세 정보</h2>
	
	<table class="table table-bordered">
				<tr>
					<td>아이디</td>
					<td>${member.mem_id }</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>${member.mem_name}</td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td>${member.mem_birth}</td>
				</tr>
				<tr>
					<td>핸드폰번호</td>
					<td>${member.mem_phone}</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>${member.mem_email}</td>
				</tr>
				<tr>
					<td>주소</td>
					<td>
						<p>${member.mem_zipcode}</p>
						<p>${member.mem_addr_master}</p>
						<p>${member.mem_addr_detail}</p>
					</td>
				</tr>
				<tr>
					<td>가입일자</td>
					<td>${member.reg_date}</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="button" class="btn btn-warning" value="수정" onclick="fn_modi('${member.mem_id }')">
						<input type="button" class="btn btn-danger" value="삭제" onclick="fn_del('${member.mem_id }')">
						<input type="button" class="btn btn-primary" value="목록" onclick="fn_list()">
					</td>
				</tr>
					
	</table>


</div>
</body>
</html>