<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>photoWriteFrm</title>
	</head>

	<body>
		<%@include file="/WEB-INF/views/common/header.jsp" %>
			<div class="container">
				<form action="/photoWrite" method="post" enctype="multipart/form-data">
					<fieldset>
						<legend>사진게시판 작성</legend>
						<table class="table" style="width: 100%;">
							<tr class="table-active">
								<th>작성자</th>
								<td>
									<input type="hidden" name="photoWriter" value="<%=m.getMemberId()%>">
									<%=m.getMemberId()%>
								</td>
								<th>첨부파일</th>
								<td style="text-align: left;">
									<input type="file" name="filename" onchange="loadImg(this);">
								</td>
							</tr>
							<tr class="table-active">
								<th>이미지</th>
								<td colspan="3">
									<div id="img-viewer">
										<img id="img-view" width="500px">
									</div>
								</td>
							</tr>
							<tr class="table-active">
								<th>내용</th>
								<td colspan="3">
									<textarea name="photoContent" class="form-control"></textarea>
								</td>
							</tr>
							<tr class="table-active">
								<th colspan="4">
									<button type="submit" class="btn btn-danger btn-block">등록하기</button>
								</th>
							</tr>
						</table>
					</fieldset>
				</form>
			</div>
			<script>
				function loadImg(f) {
					if (f.files.length != 0) { // 첨부파일이 없는 경우
						var reader = new FileReader(); // 첨부파일을 읽어올 객체
						reader.readAsDataURL(f.files[0]); // 해당 파일의 경로를 읽어옴
						// 경로를 다 읽어오면 실행할 함수 지정
						reader.onload = function (e) { // 로딩이 다 되는 경우
							$("#img-view").attr("src", e.target.result); // 사진 미리보기
						}
					} else { 	//첨부파일이 없는 경우
						$("#img-view").attr("src", "");
					}
				}
			</script>
			<%@include file="/WEB-INF/views/common/footer.jsp" %>
	</body>

	</html>