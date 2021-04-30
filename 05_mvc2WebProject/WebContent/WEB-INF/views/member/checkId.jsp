<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String memberId = (String)request.getAttribute("memberId");
	boolean result = (Boolean)request.getAttribute("result");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<style>
	#checked-container{
	text-align: center;
	padding-top:50px;
	}
	#checked{
		color:red;
		font-weight:bold;
	}
</style>
</head>
<body>
	<div id="checked-container">
		<%if(result){ %>
		[<span><%=memberId %></span>]는 사용이 가능합니다.
		<br><br>
		<button onclick="setMemberId('<%=memberId %>')">닫기</button>
		<%}else{ %>
		[<span id="checked"><%=memberId %></span>]는 이미 사용중입니다.
		<br><br>
		<!-- 중복검사 -->
		<!-- submit을 하면 다시 chekId 서블릿으로 이동해서 결과값에 따라 checkId.jsp에서 화면구현 -->
		<form action="/checkId" method="post">
			<input type="text" name="checkId" placeholder="사용할 아이디를 입력하세요"><br>
			<input type="submit" value="중복검사">
		</form>
		<%} %>
	</div>
	
	<script>
		//닫기버튼.클릭시
		function setMemberId(memberId){
			//닫기버튼 클릭시 id의 input창에 중복체크했던 아이디값 입력
			$("#idChk",opener.document).prev().val(memberId);
			//닫기버튼 클릭시 혼자 창이 꺼지도록
			self.close();
		}
	</script>
</body>
</html>

















