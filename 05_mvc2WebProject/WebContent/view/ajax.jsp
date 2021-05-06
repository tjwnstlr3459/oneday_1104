<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container">
		<filedset>
			<legend>AJAX</legend>
			<div>
				<h3>1.서버로 데이터 보내기</h3>
				<input type="text" id="ajax1">
				<button class="btn btn-info" onclick="jsAjax();">보내기(JS)</button>
				<button class="btn btn-danger" id="jQ1">보내기(jQuery)</button>
			</div>
			
			<!-- input창에 값을 적어 ajaxResult에 뜨게 하기 -->
			<div>
				<h3>2.서버에서 기본형 데이터 받기</h3>
				<button class="btn btn-danger" id="jQ2">데이터받기</button>
				<p class="ajaxResult"></p>
			</div>
			
			<!-- 두수의 합 계산-->
			<div>
				<h3>3. 서버의 기본형 데이털 보내고 기본형 데이터 받기</h3>
				<input type="text" id ="firstNum">
				<input type="text" id ="secondNum">
				<button class="btn btn-danger" id="jQ3">두수의 합</button>
				<p class="ajaxResult"></p>
			</div>
			
			<!-- 객체보내기 -->
			<div>
				<h3>4.서버로 객체 데이터 보내기</h3>
				<input type="text" id="name"><br>
				<input type="text" id="age"><br>
				<input type="text" id="addr"><br>
				<button class="btn btn-danger" id="jQ4">객체보내기</button>
			</div>
			<!--  -->
			<div>
				<h3>5. 서버로 기본형 데이터를 보내고, 결과로 객체받기</h3>
				<input type="text" id="ajax5">
				<button class="btn btn-danger" id="jQ5">객체받기</button>
				<p class="ajaxResult"></p>
			</div>
			<div>
				<h3>6. 서버에서 리스트타입 결과 받기</h3>
				<button class ="btn btn-danger" id="jQ6">조회</button>
				<p class="ajaxResult"></p>
			</div>
		</filedset>
	</div>
	<script>
		
	</script>
	
	<script>
		function jsAjax(){
			//1. XMLHttpRequest 객체 생성
			var xhttp = new XMLHttpRequest();
			var msg = document.getElementById("ajax1").value;	//input입력값
			//2.요청정보를 설정
			xhttp.open("GET","/ajaxTest1?msg="+msg,true);
			//3.데이터 처리에따라 동작할 함수 설정
			xhttp.onreadystatechange = function(){
				if(this.readyState == 4 && this.status == 200){	//정상적으로 처리되었을때 호출되는 함수
					console.log("서버 전송 성공");
				}else if(this.readyState == 4 && this.status == 404){//요청할 페이지가 없는경우
					console.log("서블릿 없음");
				}
			}
			//4.서버에 요청
			xhttp.send();
		}
		
		//jQuery ajax방식
		//1.서버로 데이터 보내기
		$("#jQ1").click(function(){
			var msg = $("#ajax1").val();
			$.ajax({
				url : "/ajaxTest1",
				data : {msg:msg},	//값을 여러개 줄수도 있다
				type : "get",
				success : function(){	//성공시
					console.log("서버 전송 성공");
				},
				error : function(){		//실패시
					console.log("에러가 났습니다!~");
				},
				complete : function(){	//끝나고 무조건 실행
					console.log("성공/실패 여부와 상관없이 호출");
				}
			});
		});
		
		// jQuery ajax방식데이터받기
		//2.서버에서 기본형 데이터 받기
		$("#jQ2").click(function(){
			var result = $(this).next();
			$.ajax({	//보내줄 데이터 없으면 안적음
				url :"/ajaxTest2",
				type : "get",
				success : function(data){	//서버가 주는 데이터가 배개면수로 들어옴
					result.html(data);
				},
				error:function(){
					console.log("실패");
				}			
			});		
		});
		
		//3. 서버의 기본형 데이털 보내고 기본형 데이터 받기
		$("#jQ3").click(function(){
			var result = $(this).next();
			var msg1 = $("#firstNum").val();
			var msg2 = $("#secondNum").val();
			$.ajax({
				url:"/ajaxTest3",
				data:{msg1:msg1,msg2:msg2},
				type:"post",
				success : function(data){	//서버가 주는 데이터가 배개면수로 들어옴
					result.html(data);
				},
				error:function(){
					console.log("실패");
				}	
			});
		});
		
		//4.서버로 객체 데이터 보내기
		$("#jQ4").click(function(){
			var name = $("#name").val();
			var age = $("#age").val();
			var addr = $("#addr").val();
			
			//객체생성
			var student ={name:name,age:age,addr:addr};
			$.ajax({
				url:"/ajaxTest4",
				type:"post",
				data:student,	//미리객체생성해서 변수명만 입력
				success:function(){
					console.log("성공");	
				}
			});
		});
		
		//5. 서버로 기본형 데이터를 보내고, 결과로 객체받기
		$("#jQ5").click(function(){
			var memberId = $("#ajax5").val();
			var result = $(this).next();
			console.log(memberId);
			$.ajax({
				url:"/ajaxTest5",
				type:"get",
				data : {memberId:memberId},
				success: function(data){
					//리턴된 data는 객체타입으로 꺼내서 사용해야함
					if(data == null){
						result.html("회원 정보가 없습니다.");
					}else{
						var memberNo = data.memberNo;
						result.append("회원번호 :"+memberNo+"<br>");
						var memberId = data.memberId;
						result.append("아이디 :"+memberId+"<br>");
						
						var memberName1 = data.memberName1;
						result.append("이름1 :"+memberName1+"<br>");
						
						var memberName2 = data.memberName2;
						result.append("이름2 :"+memberName2+"<br>");
						
						var memberName3 = decodeURIComponent(data.memberName3);
						result.append("이름3 :"+memberName3);
					}
				}
			});	
		});
		
		//6. 서버에서 리스트타입 결과 받기
		$("#jQ6").click(function(){
			var result = $(this).next();
			$.ajax({
				url:"/ajaxTest6",
				type : "get",
				success : function(data){
					if(data.length != 0){
						var html="";
						for(var i=0; i<data.length; i++){
							var memberNo = data[i].memberNo;
							var memberName = decodeURIComponent(data[i].memberName);
							var phone = data[i].phone;
							html += "회원번호 : "+memberNo+" /이름 : "+memberName+" / 전화번호 : "+phone+"<br>";
						}
						result.html(html);
					}else{
						result.html("회원정보가 없습니다.");
					}
				}
			});
		});
	</script>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>













