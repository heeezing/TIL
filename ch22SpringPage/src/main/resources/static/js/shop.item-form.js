$(function(){
	let temp_limit = $('#delivery_limit').val(); //배송비 면제 금액의 값
	
	//등록 폼에서 유효성 체크 시 폼을 다시 호출할 때 또는 수정폼 호출 시 기본값 세팅
	//1.등록 폼 - 배송비 입력 시 배송비 면제 여부 버튼이 보여짐 
	if($('#item_register #delivery_fee').val() > 0){
		$('.deli-limit').show();
	}
	//2-1.수정 폼 - 배송비 O, 배송비 면제 없음 선택 시
	if($('#item_modify #delivery_fee').val() > 0 
			&& $('#item_modify #delivery_limit').val() == 0){
		$('.deli-limit').show();
		$('#delivery_radio1').attr('checked',true);
	}
	//2-2.수정 폼 - 배송비 O, 배송비 면제 선택 시 - 금액 입력란 보여짐
	if($('#item_modify #delivery_fee').val() > 0 
			&& $('#item_modify #delivery_limit').val() > 0){
		$('.deli-limit').show();
		$('#delivery_radio2').attr('checked','true');
		$('#delivery_limit').attr('type','number');
		$('#delivery_limit').attr('min',$('#price').val());
	}
	
	//배송비 입력 시 이벤트 처리
	$('#delivery_fee').on('keyup mouseup',function(){
		if($('#price').val() == '' || $('#price').val() == 0){
			alert('가격을 입력해야 배송비를 지정할 수 있습니다.');
			$('#price').focus();
			return;
		}
		if($('#delivery_fee').val() < 0){
			alert('0 이상만 입력 가능');
			$('#delivery_fee').val('');
			return;
		}
		if($('#delivery_fee').val() == 0){
			$('.deli-limit').hide();
			$('input[name="delivery_radio"]').prop('checked',false);
			$('.deli-limit').find('#delivery_limit').val(0);
			return;
		}
		if($('#delivery_fee').val() > 0){
			$('.deli-limit').show();
		}
	});
	
	//배송비 면제 금액 체크박스 클릭 이벤트 처리
	$('input[name="delivery_radio"]').click(function(){
		if($(this).val() == 0){ //배송비 면제 없음(0) 클릭
			$('#delivery_limit').attr('type','hidden');
			$('#delivery_limit').val(0);
		}else{ //배송비 면제(1) 클릭
			$('#delivery_limit').attr('type','number');
			if($('#item_modify #delivery_limit').length > 0){
				$('#delivery_limit').val(temp_limit).attr('min',$('#price').val());
			}else{
				$('#delivery_limit').val($('#price').val()).attr('min',$('#price').val());
			}
		}
	});
	
});