
<%@page import="member.model.vo.NoticeComment"%>
<%@page import="notice.model.vo.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	Notice n = (Notice)request.getAttribute("n");
    ArrayList<NoticeComment> list = (ArrayList<NoticeComment>)request.getAttribute("list");
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container">
		<fieldset>
			<legend>공지사항</legend>
			<table class="table" id="noticeView" style="width:100%";>
				<tr class="table-info">
					<th colspan="4"><%=n.getNoticeTitle() %></th>
				</tr>
				<tr class="table-light">
					<th>작성자</th>
					<th><%=n.getNoticeWriter() %>
					<th>작성일</th>
					<th><%=n.getNoticeDate() %>
				</tr>
				<tr>
					<th>첨부파일</th>
					<th colspan="3">
						<%if(n.getFilename()!=null){ %>
						<img src="/img/file.png" width="16px"><%=n.getFilename() %>
						<a href="/fileDown?noticeNo=<%=n.getNoticeNo()%>"><%=n.getFilename() %></a>
						<%} %>
					</th>
				</tr>
				<tr class="table-light">
					<th>내용</th>
					<th colspan="3">
						<%=n.getNoticContentBr()%>
					</th>
				</tr>
				<tr class="table-light">
					<th colspan="4" style="text-align:center;">
					<button class="btn btn-info" onclick="history.go(-1);">이전화면</button>
						<%if(m!=null && m.getMemberId().equals(n.getNoticeWriter())){ %>
						<a class="btn btn-info" href="/noticeUpdateFrm?noticeNo=<%=n.getNoticeNo() %>">수정하기</a>
						<a class="btn btn-info" href="/noticeDelete?noticeNo=<%=n.getNoticeNo() %>">삭제하기</a>
						<%} %>
					</th>
				</tr>
			</table>
		</fieldset>
		<!-- 로그인이 되야 작성가능하게 -->
		<%if(m!=null){ %>
		<div class="inputCommentBox">
			<form action="/insertComment" method="post">
				<ul>
					<li>
						<i class="far fa-user fa-5x"></i>
					</li>
					<li>
						<input type="hidden" name="ncLevel" value="1">
						<input type="hidden" name="ncWriter" value="<%=m.getMemberId()%>">
						<!-- m객체는 header에서 세션에 저장되어 있기에 사용 가능 -->
						<input type="hidden" name="noticeRef" value="<%=n.getNoticeNo()%>">
						<!-- ncRef의 value값이 0이라서 나중에 오류 -->
						<input type="hidden" name="ncRef" value="0">
						<textarea class="form-control" name="ncContent" style="background-color:#f4f4f4;"></textarea>
					</li>
					<li>
						<button type="submit" class="btn btn-primary btn-lg btn-block">등록</button>
					</li>
				</ul>
			</form>
		</div>
		<%} %>
		<!-- 댓글 출력 시작 -->
		<div class="commentBox">
			<%for(NoticeComment nc : list){ %>
			<%if(nc.getNcLevel()==1){ //레벨이 1인지 확인하는 조건문%>
			<ul class="comments">
				<!-- 아이콘/작성자/날짜 -->
				<li>
					<i class="fas fa-user fa-3x"></i>
					<p><%=nc.getNcWriter() %></p>
					<p><%=nc.getNcDate() %></p>
				</li>
				
				<li>
					<!-- 댓글 내용-->
					<p><%=nc.getNcContentBr() %></p>
					<textarea name="ncContent" class="form-control" style="display:none;"><%=nc.getNcContent()%></textarea>
					<p class="commentsBtn">
					<!-- 작성자라면 -->
					<%if(m != null){ %>
						<%if(m.getMemberId().equals(nc.getNcWriter())) { %>
							<a href="javascript:void(0)" onclick="modifyComment(this,'<%=nc.getNcNo()%>','<%=n.getNoticeNo()%>');">수정</a>
							<a href="javascript:void(0)" onclick="deleteComment(this,'<%=nc.getNcNo()%>','<%=n.getNoticeNo()%>');">삭제</a>
						<%}//댓글 작성자와 로그인 회원이 일치하는 경우 %>
						<a href="javascript:void(0)" class="recShow">답글달기</a>
					<%}//로그인 되어있는 경우 %>
					</p>
					<!-- 로그인되어있다면 -->
					<%if(m !=null) {%>
					<form action="/insertComment" class="recoment">
					<!-- 서블릿에 값을 보내주기위한 값(화면구현에는 필요가 없다) -->
						<input type="hidden" name="ncLevel" value="2">
						<input type="hidden" name="ncWriter" value="<%=m.getMemberId() %>">
						<input type="hidden" name="noticeRef" value="<%=n.getNoticeNo() %>">
						<input type="hidden" name="ncRef" value="<%=nc.getNcNo()%>">
						<!-- 답글에 답글을 구분하기 위한 no를 넣는다 -->
						<textarea class="form-control" name="ncContent"></textarea>
						<div>
							<button type="submit" class="btn btn-outline-primary">등록</button>
							<button type="submit" class="btn btn-outline-primary recCancel">취소</button>
						</div>
					</form>
					<%} %>
				</li>
			</ul>
				
				<%for(NoticeComment ncc : list){ %>
				<!-- 대댓글 : 대댓글레벨이면서 뒤에참조하는 댓글no가 같은지 확인 -->
					<%if(ncc.getNcLevel() == 2 && ncc.getNcRef()==nc.getNcNo()) {%>
						<ul class="recomments">
							<li>
								<i class="fas fa-angle-double-right fa-3x"></i>
							</li>
							<li>
								<i class="fas fa-user fa-3x"></i>
								<p><%=ncc.getNcWriter() %></p>
								<p><%=ncc.getNcDate() %></p>
							</li>
							<li>
								<p><%=ncc.getNcContentBr() %>
								<textarea name="ncContent" class="form-control" style="display:none;"><%=ncc.getNcContent()%></textarea>
								<p class="commentsBtn">
									<%if(m!=null && m.getMemberId().equals(ncc.getNcWriter())) {%>
									
									<a href="javascript:void(0)" onclick="modifyComment(this,'<%=ncc.getNcNo()%>','<%=n.getNoticeNo()%>');">수정</a>
									<a href="javascript:void(0)" onclick="deleteComment(this,'<%=ncc.getNcNo()%>','<%=n.getNoticeNo()%>');">삭제</a>
									<%} %>
								</p>
							</li>
						</ul>
					<%} %>
				<%} //대댓글 반복문 끝나는 지점 %>
				
			<%} //레벨이 1인지 확인하는 조건문%>
			<%} //전체댓글 반복문 끝나는 지점%>
		</div>
	</div>
	<script>
		$(".recShow").click(function(){
			var idx = $(".recShow").index(this);
			$(this).hide();
			
			$(".recoment").eq(idx).css("display","flex");
		});
		$(".recCancel").click(function(){
			var idx = $(".recCancel").index(this);
			$(".recoment").eq(idx).css("display","none");
			$(".recShow").eq(idx).show();
		});
		
		//댓글 수정클릭시 - 버튼변경
		function modifyComment(obj,ncNo,noticeNo){
			//textarea를 보여줌
			$(obj).parent().prev().show();
			//기존본문내용을 숨김
			$(obj).parent().prev().prev().hide();
			//수정 -> 수정완료
			$(obj).html('수정완료');
			$(obj).attr("onclick","modifyComplete(this,'"+ncNo+"','"+noticeNo+"')");
			//삭제 -> 수정취소
			$(obj).next().html('수정취소');
			$(obj).next().attr("onclick","modifyCancel(this,'"+ncNo+"','"+noticeNo+"')");
			//답글달기 버튼 삭제
			$(obj).next().next().hide();
		}
		
		//수정취소시 - 다시 원래버튼으로
		function modifyCancel(obj,ncNo,noticeNo){
			//textarea 숨김
			$(obj).parent().prev().hide();
			//기존본문내용 다시 보여줌
			$(obj).parent().prev().prev().show();
			//수정완료 ->수정
			$(obj).prev().html("수정");
			$(obj).prev().attr("onclick","modifyComment(this,'"+ncNo+"','"+noticeNo+"')");
			//수정 취소 -> 삭제
			$(obj).html("삭제");
			$(obj).attr("onclick","deleteComment(this,'"+ncNo+"','"+noticeNo+"')");
			//답글달기버튼 보이게
			$(obj).next().show();
		}
		//수정완료시
		function modifyComplete(obj,ncNo,noticeNo){
			var form = $("<form action='/noticeCommentUpdate' method='post'></form>");
			form.append($("<input type='text' name='ncNo' value='"+ncNo+"'>"));
			form.append($("<input type='text' name='noticeNo' value='"+noticeNo+"'>"));
			form.append($(obj).parent().prev());
			$("body").append(form);
			form.submit();//js에서 submit누르효과와 동일
		}
		//삭제시
		function deleteComment(obj,ncNo,noticeNo){
			if(confirm("댓글을 삭제하시겠습니까?")){
				location.href="/noticeCommentDelete?ncNo="+ncNo+"&noticeNo="+noticeNo;
			}else{
				system.out.println("안넘김");
			}
		}
	
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>














