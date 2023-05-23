<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		let count = 0;
		//아이디 중복 체크 이벤트 연결
		$('#confirm_id').click(function(){
			if($('#id').val().trim()==''){
				alert('아이디를 입력하세요');
				$('#id').focus();
				$('#id').val('');
				return;
			}
			
			if(!/^[A-Za-z0-9]{4,12}$/.test($('#id').val())){
				alert('영문 또는 숫자 사용, 최소 4자~최대 12자를 사용하세요');
				$('#id').val('');
				$('#id').focus();
				return;
			}
			
			$.ajax({
				url:'confirmId.jsp',
				type:'post',
				data:{id:$('#id').val()},
				dataType:'json',
				success:function(param){
					if(param.result == 'idDuplicated'){
						count = 0;
						$('#id_signed').text('아이디 중복').css('color','red');
						$('#id').val('').focus();
					}else if(param.result == 'idNotFound'){
						count = 1;
						$('#id_signed').text('사용 가능 아이디').css('color','black');
					}else{
						count = 0;
						alert('아이디 중복 체크 오류 발생');
					}
				},
				error:function(){
					count = 0;
					alert('네트워크 오류 발생');
				}
			}); 

		}); //end of click

		$('#register_form #id').keydown(function(){
			count = 0;
			$('#id_signed').text('');
		}); //end of keydown
		
		$('#register_form').submit(function(){
			let items = document.querySelectorAll('.input-check');
			for(let i=0 ; i<items.length ; i++){
				if(items[i].value.trim()==''){
					let label = document.querySeletor('label[for="'+items[i].id+'"]');
					alert(label.textContent + '항목 필수 입력');
					items[i].value = '';
					items[i].focus();
					return false;
				}
				if(items[i].id=='id' && count==0){
					alert('아이디 중복 체크 필수');
					return false;
				}
			}
		}); //end of submit
		
	});
</script>
</head>
<body>
<div class="page-main">
	<h1>회원 가입</h1>
	<form action="registerUser.jsp" method="post" id="register_form">
		<ul>
			<li>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id" size="7"
					   maxlength="12" autocomplete="off" class="input-check">
				<input type="button" id="confirm_id" value="ID중복확인">
				<span id="id_signed"></span>
			</li>
			<li>
				<label for="name">이름</label>
				<input type="text" name="name" id="name" maxlength="10" class="input-check">
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<input type="password" name="passwd" id="passwd" maxlength="12" class="input-check">
			</li>
			<li>
				<label for="email">이메일</label>
				<input type="email" name="email" id="email" maxlength="50" class="input-check">
			</li>
			<li>
				<label for="phone">전화번호</label>
				<input type="text" name="phone" id="phone" maxlength="15">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="등록">
			<input type="button" value="홈으로" onclick="location.href='main.jsp'">
		</div>
	</form>
</div>
</body>
</html>