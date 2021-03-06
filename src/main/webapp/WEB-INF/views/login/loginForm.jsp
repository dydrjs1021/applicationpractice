<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<title>Insert title here</title>
<script type="text/javascript">
function fn_login() {
	var frm = document.loginForm;
	
	if(frm.mem_id.value == ""){
		alert("아이디를 입력하세요.");
		return false;
	}
	
	if(frm.mem_pwd.value == ""){
		alert("비밀번호를 입력하세요.");
		return false;
	}
	
	frm.action ="login";
	
	frm.submit();
}

</script>
</head>
<body>

<div class="container">

	<h1 align="center">넥스트아이티 교육센터</h1>

	<div class="panel panel-default" style="width: 400px; margin: auto;">
		
		<div class="panel-heading">
			로그인 <span style="float: right;"><a href="${pageContext.request.contextPath}">Home</a></span>
		</div>
		
		<div class="panel-body">
			
			<form class="form-horizontal" name="loginForm" method="post">
				<div class="form-group">
					<div class="col-xs-12">
						<label>아이디</label><input type="text" name="mem_id" value="${empty cookie.REMEMBER_ID ? '': cookie.USER_ID.value}" class="form-control" placeholder="아이디를 입력하세요.">
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-12">
						<label>비밀번호</label><input type="password" name="mem_pwd" value="" class="form-control" placeholder="비밀번호를 입력하세요.">
					</div>
				
				</div>
				<div class="form-group">
					<div class="col-xs-12">
						<label><input type="checkbox" name="remember_id" value="Y" ${empty cookie.REMEMBER_ID ? '' : 'checked'}>아이디 저장</label>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-xs-12">
						<input type="button" value="login" class="btn btn-success btn-block" onclick="fn_login();">
					</div>
				</div>
			</form>
			
			<hr>
			
			<div align="center">
				<a href="#">아이디 찾기</a> |
				<a href="#">비밀번호 찾기</a> |				
				<a href="${pageContext.request.contextPath}/member/memberForm?type=I">회원가입</a> 				
			</div>
			
		</div>
	</div>
</div>

</body>
</html>