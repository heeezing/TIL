$(function(){
	/*-------------------
	 	  주문 수량 변경
	--------------------*/
	$('#order_quantity').on('keyup mouseup',function(){
		if($('#order_quantity').val() == ''){
			$('#item_total_txt').text('총 주문 금액 : 0원');
			return;
		}	
		if($('#order_quantity').val() <= 0){
			$('#order_quantity').val('');
			return;
		}
		if(Number($('#item_quantity').val()) < $('#order_quantity').val()){
			alert('수량이 부족합니다.');
			$('#order_quantity').val('');
			$('#item_total_txt').text('총 주문 금액 : 0원');
			return;
		}
		
		//총 주문 금액 연산 (가격*수량) - .toLocaleString으로 세자리씩 끊어서 표시
		let total = $('#item_price').val() * $('#order_quantity').val();
		$('#item_total_txt').text('총 주문 금액 : ' + total.toLocaleString() + '원');
	});
	
	
	/*-------------------
 	 	  장바구니 담기
	--------------------*/
	$('#item_cart').submit(function(event){
		if($('#order_quantity').val() == ''){
			alert('수량을 입력하세요');
			$('#order_quantity').focus();
			return false;
		}
		
		let form_data = $(this).serialize();
		//서버와 통신
		$.ajax({
			url:'../cart/write.do',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용 가능합니다.');
				}else if(param.result == 'success'){
					alert('장바구니에 담았습니다.');
					location.href='../cart/list.do';
				}else if(param.result == 'overquantity'){
					alert('기존에 주문한 상품입니다. 개수를 추가 시 재고가 부족합니다.');
				}else{
					alert('장바구니 담기 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		//기본 이벤트(submit) 제거
		event.preventDefault();
		
	});
	
	
	
	
	
	
	
	
	
	
	
});