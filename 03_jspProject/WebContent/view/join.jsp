<%@page import="kr.or.iei.member.dao.JoinDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    //1.인코딩
   	request.setCharacterEncoding("utf-8");
    //2.값추출
    String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String pwRe = request.getParameter("pwRe");
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	String addr = request.getParameter("addr");
    //3.로직처리
    JoinDao dao = new JoinDao();
    int result = dao.insertMember(id,pw,name,phone,addr);
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입 결과</h1>
	<%if(result>0){ %>
	<h2>가입성공!</h2>
	<%}else{ %>
	<h2>가입실패</h2>
	<%} %>
</body>
</html>