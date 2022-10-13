<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스트랩 : 비밀번호 찾기</title>
<script src="/resources/js/jquery-3.6.1.min.js"></script>
	<style>
		.findIdResultForm{
		margin:auto;
		text-align: center;
		width: 500px;
		}
		span.email, #certificationNumber{
			display:none;
			font-size:12px;
			top:12px;
			right:10px;
			
		}
		span.error{color:red}
	</style>
</head>
<body>
	<div class="findIdResultForm">
		<form action="/member/findPwdresult.strap" method="post">
			<h3>비밀번호 찾기</h3>
			<hr>
			<label for="memberId">아이디</label><br>
			<input type="text" id="memberId" name="memberId" placeholder="찾고싶은 아이디"><br>
			<label for="memberEmail">이메일</label><br>
			<input type="email" id="memberEmail" name="memberEmail" placeholder="이메일"><br>
			<span class="email error">이메일 주소를 다시 확인해주세요</span>
			<input type="text" id="certificationNumber" name="certificationNumber" placeholder="인증번호"><br>
			<button type="button" id="certificationBtn">인증요청</button><br><br>
			<button type="submit" id="findPwdBtn" disabled="disabled">비밀번호 찾기</button>	
		</form>
	</div>
	<script>
		$("#memberEmail").on("keyup",function(){
			var memberEmail = $("#memberEmail").val()
			var regExt = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
			if(regExt.test(memberEmail)){
				$(".email.error").hide();
			}else{
				$(".email.error").show();
			}
		});
		
		var authNumber = "";
		$("#certificationBtn").on("click",function(){
			var memberId = $("#memberId").val();
			var memberEmail = $("#memberEmail").val();
			if($(".email.error").css("display") == 'none' && memberEmail.length>1){
				console.log("아이디와 이메일 일치 시 인증번호 발송");
				$.ajax({
					url:"/member/idEmailCheck.strap",
					data:{"memberId":memberId, "memberEmail":memberEmail},
					type:"post",
					success:function(result){
						if(result.send=="ok"){
							console.log("일치");
							console.log(result);
							$("#certificationNumber").show();
							$("#findPwdBtn").removeAttr("disabled");
							authNumber = result.num;
							console.log(authNumber);
							window.alert("입력하신 이메일 주소로 인증번호를 발송했습니다.");
						} else {
							console.log("불일치")
							window.alert("아이디와 비밀번호를 확인해주세요");
						}
						
					}
				});
				}
		});
		
	</script>
</body>
</html>