$(function(){
	/*----------------------
 	   회원 주소 읽기(배송지 지정)
	-----------------------*/
	
	$('#member_address').click(function(){
		//서버와 통신
		$.ajax({
			url:'getMemberAddress.do',
			type:'get',
			//세션에 있는 회원 정보 불러올거라 전달할 데이터는 없음
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 회원 주소를 불러올 수 있습니다.');
				}else if(param.result == 'success'){
					$('#zipcode').val(param.zipcode);
					$('#address1').val(param.address1);
					$('#address2').val(param.address2);
					$('#receive_phone').val(param.phone);
				}else{
					alert('회원 주소 읽기 오류');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		
	});
	
	
});