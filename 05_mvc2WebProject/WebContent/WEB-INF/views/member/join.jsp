<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- incloud 헤더 푸터 입력이 기본셋팅 -->
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container">
		<form action="/join" method="post" name="joinFrm">
			<legend>회원가입</legend>
			<div class="form-group">
				<label class="control-label" for="memberId">아이디</label>
				<input type="text" name="memberId" id="memberId" class="form-control">
			</div>
			<div class="form-group">
				<label class="control-label" for="memberPw">비밀번호</label>
				<input type="password" name="memberPw" id="memberPw" class="form-control">
			</div>
			<div class="form-group">
				<label class="control-label" for="memberName">이름</label>
				<input type="text" name="memberName" id="memberName" class="form-control">
			</div>
			<div class="form-group">
				<label class="control-label" for="phone">전화번호</label>
				<input type="text" name="phone" id="phone" class="form-control">
			</div>
			<div class="form-group">
				<label class="control-label" for="address">주소</label>
				<input type="text" name="address" id="address" class="form-control">
			</div>
			<button type="submit" class="btn btn-outline-primary btn-lg btn-block">회원가입</button>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>