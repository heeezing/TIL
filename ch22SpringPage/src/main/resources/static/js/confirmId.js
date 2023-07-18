$(function(){
	let checkId = 0;
	
	//아이디 중복 체크
	$('#confirmId').click(function(){
		if($('#id').val().trim() == ''){
			$('#message_id').css('color','red').text('아이디를 입력하세요');
			$('#id').val('').focus();
		}
		
		$('#message_id').text(''); //메시지 초기화
		$.ajax({
			url:'confirmId.do',
			type:'post',
			data:{id:$('#id').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'idNotFound'){
					$('#message_id').css('color','#000').text('등록 가능 ID');
					checkId = 1;
				}else if(param.result == 'idDuplicated'){
					$('#message_id').css('color','red').text('중복된 ID');
					$('#id').val('').focus();
					checkId = 0;
				}else if(param.result == 'notMatchPattern'){
					$('#message_id').css('color','red').text('영문,숫자 4자이상 12자이하 입력');
					$('#id').val('').focus();
					checkId = 0;
				}else{
					checkId = 0;
					alert('아이디 중복 체크 오류');
				}
			},
			error:function(){
				checkId = 0;
				alert('네트워크 오류 발생');
			}
		});
	}); //end of click
	
	
	//아이디 중복 값 & 아이디 중복 안내 메시지 초기화
	$('#member_register #id').keydown(function(){
		checkId = 0; //중복값 초기화
		$('#message_id').text(''); //메시지 초기화
	}); //end if keydown
	
	
	//submit 이벤트 발생 시 아이디 중복 체크 여부 확인
	$('#member_register').submit(function(){
		if(checkId == 0){
			$('#message_id').css('color','red').text('아이디 중복 체크 필수');
			
			if($('#id').val().trim() == ''){
				$('#id').val('').focus();
			}
			return false;
		}
	}); //end of submit
	
});