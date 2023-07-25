$(function(){
	let rowCount = 10;
	let currentPage;
	let count;
	
	//댓글 목록
	function selectList(pageNum){
		
	}
	
	//다음 댓글 보기 버튼 클릭 시 데이터 추가
	
	
	//댓글 등록 (기본이벤트 제거를 위해 event를 인자로 받음)
	$('#re_form').submit(function(event){
		if($('#re_content').val().trim() == ''){
			alert('내용을 입력하세요!');
			$('#re_content').val('').focus();
			return false;
		}
		//입력한 정보 읽기
		//.serialize()를 통해 form에 기입된 데이터를 한 번에 가져옴
		let form_data = $(this).serialize();
		//서버와 통신
		$.ajax({
			url:'writeReply.do',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 작성할 수 있습니다.');
				}else if(param.result == 'success'){
					//폼 초기화
					initForm();
					//새로 삽입한 글을 포함해서 첫번째 페이지의 게시글을 다시 호출
					selectList(1);
				}else{
					alert('댓글 등록 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		
		//기본 이벤트 제거
		event.preventDefault();
	}); //end of submit
	
	
	//댓글 지정 폼 초기화
	function initForm(){
		$('textarea').val('');
		$('#re_first .letter-count').text('300/300');
	}
	
	
	//초기 데이터(목록) 호출
	selectList(1);
	
});