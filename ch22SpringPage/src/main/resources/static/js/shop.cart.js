$(function(){
	//장바구니에 담겨있는 상품의 총 구매 금액
	let cart_sum = $('.all-total').attr('data-alltotal');
	
	/*-------------------
	   장바구니 체크박스 제어
	--------------------*/
	
	/*-----전체 선택&해제-----*/
	$('#chk_all').on('click',function(){
		if($('#chk_all').is(':checked')){ //전체 선택 체크
			$('.choice-btn').prop('checked',true);
			$('#order_btn').prop('disabled',false); //구매 가능(주문 버튼 선택 가능)
			
			//개별 상품 정보를 모두 표시
			$('.item-setting').remove();
			$('.order-quantity').show();
			$('.item-price').text(function(){ //여러개일 수 있으므로 루프를 돌면서 세팅
				return Number($(this).attr('data-price')).toLocaleString()+'원';
			});
			$('.delivery-fee').text(function(){
				return Number($(this).attr('data-fee')).toLocaleString()+'원';
			});
			$('.sub-total').show();
			
			//모두 선택하면 원래의 총 구매 금액으로 환원
			$('.all-total').attr('data-alltotal',cart_sum);
			
		}else{ //전체 선택 해제
			$('.choice-btn').prop('checked',false);
			$('#order_btn').prop('disabled',true); //구매 불가(주문 버튼 선택 불가)
			
			//개별 상품 정보를 모두 0 처리
			$('.order-quantity').before('<span class="item-setting">0</span>');
			$('.order-quantity').hide();
			$('.item-price').text('0원');
			$('.delivery-fee').text('0원');
			$('.sub-total').before('<span class="item-setting">0</span>');
			$('.sub-total').hide();
			$('.all-total').text('0원');
			$('.all-total').attr('data-alltotal',0);
		}
		
		//총 구매 금액 표시
		$('.all-total').text(Number($('.all-total').attr('data-alltotal')).toLocaleString()+'원');
		
	});

	
	/*-----개별 선택&해제-----*/
	
	//체크박스를 전부 선택하면 #chk_all 선택, 하나라도 해제하면 #chk_all 해제 
	$('.choice-btn').on('click',function(){
		if($('input[class=choice-btn]:checked').length == $('.choice-btn').length){
			//전체 체크박스 선택
			$('#chk_all').prop('checked',true);
		}else{
			//부분 체크박스 선택
			$('#chk_all').prop('checked',false);
		}
		
		if($(this).is(':checked')){
			//체크하면 수량,가격,배송비,개별상품합계가 정확하게 표시
			$(this).parents('tr').find('.item-setting').remove();
			$(this).parents('tr').find('.order-quantity').show();
			$(this).parents('tr').find('.item-price').text(Number($(this).parents('tr').find('.item-price').attr('data-price')).toLocaleString()+'원');
			$(this).parents('tr').find('.delivery-fee').text(Number($(this).parents('tr').find('.delivery-fee').attr('data-fee')).toLocaleString()+'원');
			$(this).parents('tr').find('.sub-total').show();
		}
		else{
			//체크를 해제하면 수량,가격,배송비,개별상품합계가 모두 0으로 표시
			$(this).parents('tr').find('.order-quantity').before('<span class="item-setting">0원</span>');
			$(this).parents('tr').find('.order-quantity').hide();
			$(this).parents('tr').find('.item-price').text('0원');
			$(this).parents('tr').find('.delivery-fee').text('0원');
			$(this).parents('tr').find('.sub-total').before('<span class="item-setting">0원</span>');
			$(this).parents('tr').find('.sub-total').hide();
		}
		
		//총 구매 금액 처리
		//하나라도 선택된 것이 있으면 true, 아예 없으면 false 반환
		if($('.choice-btn').is(':checked')){
			$('#order_btn').prop('disabled',false); //구매 가능(주문 버튼 선택 가능)
			//총 구매 금액을 선택,미선택에 따라 다시 산출
			if($(this).is(':checked')){ //선택 O
				//연산하여 data-alltotal에 데이터 세팅
				$('.all-total').attr('data-alltotal', 
									  Number($('.all-total').attr('data-alltotal'))
									+ Number($(this).parents('tr').find('.sub-total').attr('data-total')));
			}else{ //선택 X
				//연산하여 data-alltotal에 데이터 세팅
				$('.all-total').attr('data-alltotal', 
									  $('.all-total').attr('data-alltotal') 
									- $(this).parents('tr').find('.sub-total').attr('data-total'));
			}
		}else{ //하나도 선택한 게 없는 경우
			$('#order_btn').prop('disabled',true); //구매 불가(주문 버튼 선택 불가)
			//연산하여 data-alltotal에 데이터 세팅
			$('.all-total').attr('data-alltotal', 
								  $('.all-total').attr('data-alltotal') 
								- $(this).parents('tr').find('.sub-total').attr('data-total'));
		}
		
		//총 구매 금액 표시
		$('.all-total').text(Number($('.all-total').attr('data-alltotal')).toLocaleString()+'원');
		
	});
	
	
	
	/*-------------------
	   장바구니 상품 수량 변경
	--------------------*/
	$('.cart-modify').on('click',function(){
		let input_quantity = $(this).parent().find('input[name="order_quantity"]');
		//공백 체크
		if(input_quantity.val() == ''){
			alert('수량을 입력하세요!');
			input_quantity.focus();
			return;
		}
		//숫자 여부 체크
		if(isNaN(input_quantity.val())){
			//태그에 명시한 원래 value값을 다시 가지고와 세팅(.val->jquery제공)
			input_quantity.val(input_quantity.attr('value'));
			return;
		}
		//최소 수량 체크
		if(input_quantity.val() < 1){
			alert('상품의 최소 수량은 1입니다.');
			//태그에 명시한 원래 value값을 다시 가지고와 세팅(.val->jquery제공)
			input_quantity.val(input_quantity.attr('value'));
			return;
		}
		
		//서버와 통신
		$.ajax({
			url:'modifyCart.do',
			type:'post',
			data:{cart_num:$(this).attr('data-cartnum'),
				  item_num:$(this).attr('data-itemnum'),
				  order_quantity:input_quantity.val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요!');
				}else if(param.result == 'noSale'){
					alert('판매가 중지된 상품입니다.');
					location.href='list.do'; //화면 갱신
				}else if(param.result == 'noQuantity'){
					alert('상품의 수량이 부족합니다.');
					location.href='list.do'; //화면 갱신
				}else if(param.result == 'success'){
					alert('상품 개수가 수정되었습니다.');
					location.href='list.do'; //화면 갱신
				}else{
					alert('장바구니 수량 변경 오류 발생');
				}	
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		
	});
	
	
	/*-------------------
	 	장바구니 상품 삭제
	--------------------*/
	$('.cart-del').on('click',function(){
		//서버와 통신
		$.ajax({
			url:'deleteCart.do',
			type:'post',
			data:{cart_num:$(this).attr('data-cartnum')},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요!');
				}else if(param.result == 'success'){
					alert('선택한 상품을 삭제했습니다.').
					location.href='list.do'; //화면 갱신
				}else{
					alert('장바구니 상품 삭제 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
	
});