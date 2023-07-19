$(function(){
	//새 비밀번호 = 새 비밀번호 확인 일치 여부 체크 
	$('#passwd').keyup(function(){
		if($('#confirm_passwd').val()!='' && 
		   $('#confirm_passwd').val()!=$(this).val()){
			$('#message_id').text('비밀번호 불일치').css('color','red');
		}else if($('#confirm_passwd').val()!='' &&
				 $('#confirm_passwd').val()==$(this).val()){
			$('#message_id').text('비밀번호 일치').css('color','#000');	
		}
	});
	
	//새 비밀번호 확인 = 새 비밀번호 일치 여부 체크 
	$('#confirm_passwd').keyup(function(){
		if($('#passwd').val()!='' && 
		   $('#passwd').val()!=$(this).val()){
			$('#message_id').text('비밀번호 불일치').css('color','red');
		}else if($('#passwd').val()!='' &&
				 $('#passwd').val()==$(this).val()){
			$('#message_id').text('비밀번호 일치').css('color','#000');	
		}
	});
	
	
	//submit 이벤트 발생 시 
	$('#member_change').submit(function(){
		//미입력(공백) 체크
		if($('#now_passwd').val().trim()==''){
			alert('현재 비밀번호를 입력하세요!');
			$('#now_passwd').val('').focus();
			return false;
		}
		if($('#passwd').val().trim()==''){
			alert('새 비밀번호를 입력하세요!');
			$('#passwd').val('').focus();
			return false;
		}
		if($('#confirm_passwd').val().trim()==''){
			alert('새 비밀번호 확인을 입력하세요!');
			$('#confirm_passwd').val('').focus();
			return false;
		}
		//새 비밀번호 = 새 비밀번호 확인 일치 여부 체크
		if($('#passwd').val() != $('#confirm_passwd').val()){
			$('#message_id').text('비밀번호 불일치').css('color','red');
			return false;
		}
	});
	
});