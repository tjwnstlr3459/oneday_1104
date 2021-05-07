<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>mail</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<h3>EMAIL 보내기</h3>
		<input type="text" id="email" class="short form-control" placeholder="email주소입력">
		<button id="sendMail" class="btn btn-success">인증코드 보내기</button>
		<div id="auth">
			<input type="text" id="authCode" class="short form-control" placeholder="인증코드입력">
			<button class="btn btn-primary" id=authBtn>인증하기</button>
			<span id="timeZone"></span>
			<span id="authMsg"></span>
		</div>
	</div>
	<script>
	var mailCode;
	
	$("#sendMail").on("click", function () {
	    var email = $("#email").val();

	    $.ajax({
	      url: "/sendMail",
	      data: { email: email },
	      success: function (data) {
	    	mailCode = data;
	        $("#auth").slideDown();
	        authTime();
	      }
	    });
	  });
		
	$("#authBtn").click(function () {
	    if ($("#authCode").val() == mailCode) {
	      $("#authMsg").html("인증성공");
	      $("#authMsg").css("color", "blue");
	      clearInterval(intervalId); // interval 종료
	      $("#timeZone").empty();
	    } else {
	      $("#authMsg").html("인증번호를 확인하세요");
	      $("#authMsg").css("color", "red");
	    }
	  });
	
	var intervalId;
	
	function authTime() {
	    $("#timeZone").append("<span id = 'min'>3</span> : <span id='sec'>00</span>")
	    intervalId = window.setInterval(function() {
	    	timeCount();
	    }, 1000);
	  }

	function timeCount() {
	    var min = Number($("#min").html());
	    var sec = $("#sec").html();

	    // 00초 처리
	    if (sec == "00") {
	      // 0분 인 경우
	      if (min == 0) {
	        mailCode = null;
	        clearInterval(intervalId); // interval 종료
	      } else {
	        $("#min").html(--min);
	        $("#sec").html(59);
	      }
	    } else { // 초 감소
	      var newSec = Number(sec);
	      newSec--;

	      // 1자리 초 처리
	      if (newSec < 10) {
	        $("#sec").html("0" + newSec);
	      } else {
	        $("#sec").html(newSec);
	      }
	    }
	  }
		
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>