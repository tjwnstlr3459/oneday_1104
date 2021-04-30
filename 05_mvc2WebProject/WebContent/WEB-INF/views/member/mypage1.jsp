<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
Member member = (Member)request.getAttribute("member");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container">
		<form action="/updateMember1" method="post">
			<legend>내정보</legend>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="memberId">아이디</label>
					<input type="text" id="memberId" name="memberId" 
							class="form-control" value="<%=member.getMemberId()%>" readonly>
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="memberPw">비밀번호</label>
					<input type="password" id="memberPw" name="memberPw" 
							class="form-control" value="<%=member.getMemberPw()%>">
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="memberName">이름</label>
					<input type="text" id="memberName" name="memberName" 
							class="form-control" value="<%=member.getMemberName()%>" readonly>
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="phone">전화번호</label>
					<input type="text" id="phone" name="phone" 
							class="form-control" value="<%=member.getPhone()%>">
				</fieldset>
			</div>
			
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="address">주소</label>
					<input type="text" id="address" name="address" 
							class="form-control" value="<%=member.getAddress()%>">
				</fieldset>
			</div>
			
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="enrollDate">가입일</label>
					<input type="text" id="enrollDate" name="enrollDate" 
							class="form-control" value="<%=member.getEnrollDate()%>" readonly>
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset style="text-align:center">
					<button type="submit" class="btn btn-outline-primary">정보수정</button>
					<%if(member.getMemberLevel()==1){ %>
					<a href="adminPage" class="btn btn-outline-danger">회원관리</a>
					<%}else{ %>
					<a href="/deleteMember?memberNo=<%=member.getMemberNo()%>" class="btn btn-outline-danger">회원탈퇴</a>
					<%} %>
				</fieldset>
			</div>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>