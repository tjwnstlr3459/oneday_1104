<%@page import="kr.or.iei.member.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.iei.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
  	//1.인코딩
    request.setCharacterEncoding("utf-8");
    //2.값추출
    //받을게 없음 그냥 출력이라서
    //3.로직처리
    MemberDao dao = new MemberDao();
	ArrayList<Member> list = dao.printAllMemeber();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전체 회원 조회</h1>
	<%if(list.size()==0){ %>
	<p>회원이 존재하지 않습니다.</p>
	<% }else{ %>
	<table border='1px'>
		<tr>
			<td>번호</td><td>ID</td><td>이름</td><td>전화번호</td><td>주소</td>
		</tr>
		
			<%for(int i=0; i<list.size(); i++){ %>
		<tr>
			<td><%=list.get(i).getMemberNo() %></td>
			<td><%=list.get(i).getMemberId() %></td>
			<td><%=list.get(i).getMemberName() %></td>
			<td><%=list.get(i).getPhone() %></td>
			<td><%=list.get(i).getAddress() %></td>
		</tr>
			<%} %>
	</table>
	<% } %>
	
</body>
</html>