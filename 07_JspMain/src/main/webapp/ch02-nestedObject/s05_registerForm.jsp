<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실습[회원가입]</title>
<script type="text/javascript">
	window.onload=function(){
		let myForm = document.getElementById('myForm');
		myForm.onsubmit=function(){
			if(!check('name','이름')) return false;
			if(!check('id','아이디')) return false;
			if(!check('password','비밀번호')) return false;
			if(!check('phone','전화번호')) return false;
			if(!check('content','자기소개')) return false;
		};

		function check(item,name){
			let check_item = document.getElementById(item);
			
			if(check_item.value.trim()==''){
				alert(name + ' 필수 입력입니다.');
				check_item.value='';
				check_item.focus();
				return false;
			}
			
			if(item == 'id' && !/^[A-Za-z0-9]{4,12}$/.test(check_item.value)){
				alert('아이디는 영문 또는 숫자 사용, 최소 4자~최대 12자를 사용하세요!');
				check_item.value='';
				check_item.focus();
				return false;
			}
			
			if(item == 'phone' && !/^\d{3}-\d{4}-\d{4}$/.test(check_item.value)){
				alert('전화번호는 000-0000-0000 형식에 맞게 입력하세요');
				check_item.value='';
				check_item.focus();
				return false;
			}
			
			return true;
		};
	}
</script>
</head>
<body>
<form action="s06_register.jsp" method="post" id="myForm">
	이름 : <input type="text" name="name" size="10" id="name"><br>
	ID : <input type="text" name="id" size="10" id="id"><br>
	비밀번호 : <input type="password" name="password" size="10" id="password"><br>
	전화번호 : <input type="text" name="phone" size="30" id="phone"><br>
	취미 : <input type="checkbox" name="hobby" value="영화보기">영화보기
		  <input type="checkbox" name="hobby" value="음악감상">음악감상
		  <input type="checkbox" name="hobby" value="등산">등산
		  <input type="checkbox" name="hobby" value="낚시">낚시
		  <input type="checkbox" name="hobby" value="댄스">댄스
	<br>
	자기소개<br>
	<textarea rows="5" cols="60" name="content" id="content"></textarea>
	<br>
	<input type="submit" value="전송">
</form>
</body>
</html>