<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container">
	<form action="/noticeWrite" method="post" enctype="multipart/form-data">
		<fieldset>
			<legend>공지사항 작성</legend>
			<table class="table" style="width:100%;">
				<tr class="table-active">
					<th>제목</th>
					<td colspan="3"><input type="text" class="form-control" name="noticeTitle"></td>	
				</tr>
				<tr class="table-active">
					<th>작성자</th>
					<td>
						<input type="hidden" name="noticeWriter" value="<%=m.getMemberId()%>">
						<%=m.getMemberId() %>
					</td>
					<th>첨부파일</th>
					<td style="text-align: left">
						<input type="file" name="filename">
					</td>
				</tr>
				<tr class="table-active">
					<th>내용</th>
					<td colspan="3"><textarea name="noticeContent" class="form-control"></textarea></td>
				</tr>
				<tr class="table-active">
					<th colspan="4">
						<button type="submit" class="btn btn-danger btn-block">공지사항등록</button>
					</th>
				</tr>
			</table>
		</fieldset>
	</form>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>






