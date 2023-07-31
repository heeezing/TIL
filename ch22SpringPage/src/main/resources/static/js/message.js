$(function(){
	let member_list = []; //채팅 회원 저장
	
	//채팅방 멤버를 저장하는 배열에 회원을 저장
	/*jquery는 배열로 값을 받아오는데 길이가 0이라는 건 해당 페이지에 접근을 못했다는 뜻! 
	  0 이상일 경우 값을 받아왔다는 뜻.*/
	if($('#user').length > 0){ //채팅 방 생성할 때
		member_list = [$('#user').attr('data-id')];
	}else if($('#chat_member').length > 0){ //채팅할 때
		//split() : 배열로 반환
		member_list = $('#chat_member').text().split(',');
	}
	
	/*-------------------
		  채팅방 생성하기	
	 --------------------*/
	//회원 정보 검색
	$('#member_search').keyup(function(){
		if($('#member_search').val().trim() == ''){
			$('#search_area').empty();
			return;
		}
		//서버와 통신
		$.ajax({
			url:'memberSearchAjax.do',
			type:'post',
			data:{id:$('#member_search').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					$('#member_search').attr('disabled',true); //입력을 못하게 함
					$('#member_search').val('로그인해야 회원 검색이 가능합니다.');
				}else if(param.result == 'success'){
					$('#search_area').empty(); 
					$(param.member).each(function(index,item){
						//includes()메서드를 활용해 member_list에 찾는 id가 있는지 확인.
						//이미 member_list(채팅회원목록)에 있는 회원이면 이중으로 뜨지 않도록 설정.
						if(!member_list.includes(item.id)){
							let output = '';
							output += '<li data-num="'+item.mem_num+'">';
							output += item.id;
							output += '</li>';
							$('#search_area').append(output);
						}
					});
				}else{
					alert('회원 검색 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
	
	
	//검색된 회원 선택하기 (미래 태그 생성)
	$(document).on('click','#search_area li',function(){
		let id = $(this).text(); //선택한 아이디
		let mem_num = $(this).attr('data-num'); //선택한 회원번호
		member_list.push(id);
		//선택한 아이디를 화면에 표시
		let choice_id = '<span class="member-span" data-id="'+id+'">';
		choice_id += '<input type="hidden" name="members" value="'+mem_num+'">';
		choice_id += id+'<sup>&times;</sup></span>'; //x버튼 생성
		$('#talk_member').append(choice_id);
		$('#member_search').val('');
		$('#search_area').empty(); //ul태그 초기화		
		
		//채팅방 이름 자동 설정 체크 여부
		if($('#name_checked').is(':checked')){
			makeRoom_name();
		}
	});
		
	
	//선택한 채팅방 멤버 삭제하기
	$(document).on('click','.member-span',function(){
		//삭제할 멤버의 아이디 구하기
		let id = $(this).attr('data-id'); 
		//채팅 멤버가 저장된 배열에서 삭제할 멤버의 id 제거
		member_list.splice(member_list.indexOf(id),1);
		//이벤트가 발생한 태그 제거
		$(this).remove(); 
		//채팅방이름이 자동생성으로 체크된 경우 채팅방 이름을 다시 만듬
		if($('#name_checked').is(':checked')){
			makeRoom_name();
		}
		//모든 채팅 멤버를 전부 삭제했을 경우 초기화 작업
		if($('#talk_member span').length == 0){
			$('#name_span').text('');
			$('#basic_name').val('');
		}
	});
	
	
	//채팅방 이름 생성 방식 정하기 (자동or수동)
	$('#name_checked').click(function(){
		if($('#name_checked').is(':checked')){ //자동 생성
			$('#basic_name').attr('type','hidden');
			//1명이라도 들어가 있으면 채팅방 생성
			if(member_list.length > 1){
				makeRoom_name();
			}
		}else{ //수동 생성
			$('#basic_name').attr('type','text');//보여지게 처리
			$('#name_span').text(''); //채팅방 이름 표시 텍스트 초기화
		}
	});
	

	//채팅방 이름 생성
	function makeRoom_name(){
		let name = '';
		/* each메서드로 돌면서 0번 데이터 먼저 나열, 이후 1번부터는 ','를 붙혀 연결 
	 	  (0아이템,1아이템,2아이템...) */
		$.each(member_list,function(index,item){
			if(index > 0) 
			name += ','; 
			name += item; 
		});
		
		if(name.length > 55){ //55자 이후는 '...'으로 표시
			name = name.substring(0,55) + '...';
		}
		$('#basic_name').val(name);
		$('#name_span').text(name);
	}	
	
	
	//채팅방 생성을 위한 데이터 전송 (전송된 데이터는 TalkRoomVO에 담김)
	$('#talk_form').submit(function(){
		/*이미 배열에 현재 로그인한 유저가 기본 등록되어 있기 때문에
		  로그인한 유저 포함 최소 2명이 되어야 채팅이 가능하도록 조건 체크*/
		if(member_list.length <= 1){
			alert('채팅에 참여할 회원을 검색하세요!');
			$('#member_search').focus();
			return false;
		}
	});
	
	
	/*-------------------
			채팅하기	
	--------------------*/
	
	//채팅 데이터 읽기
	function selectMsg(){
		//서버와 통신
		$.ajax({
			url:'../talk/talkDetailAjax.do',
			type:'post',
			data:{talkroom_num:$('#talkroom_num').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요!');
				}else if(param.result == 'success'){
					//메시지 표시 UI 초기화
					$('#chatting_message').empty();
					
					let chat_date = '';
					$(param.list).each(function(index,item){
						let output = '';
						//chat_date는 String타입으로 설정되어있어 날짜,시간이 다 나옴(년월일 시분초)
						//날짜 추출 - 공백을 기준으로 날짜,시간 분리하면 0번 인덱스에 저장됨
						if(chat_date != item.chat_date.split(' ')[0]){
							chat_date = item.chat_date.split(' ')[0];
							output += '<div class="date-position"><span>'+chat_date+'</span></div>';
						}
						
						//채팅 멤버 등록&탈퇴 (들어오거나 나갈 때) 메시지 처리
						if(item.message.indexOf('@{member}@') >= 0){
							//신규, 탈퇴 회원 메시지
							output += '<div class="member-message">';
												//'@{member}@' 이 메시지는 안 보이게 처리
							output += item.message.substring(0, item.message.indexOf('@{member}@'));
							output += '</div>';
						}else{
							//일반 메시지
							if(item.mem_num == param.user_num){ //내가 보낸 메시지
								output += '<div class="from-position">'+item.id;
								output += '<div>';
							}else{ //다른 사람이 보낸 메시지
								output += '<div class="to-position">';
								output += '<div class="space-photo">';
								output += '<img src="../member/viewProfile.do?mem_num='+item.mem_num+'" width="40" height="40" class="my-photo">';
								output += '</div><div class="space-message">';
								output += item.id;
							}
							//공통
							output += '<div class="item">';
							output += item.read_count 
									  + ' <span>'+item.message.replace(/\r\n/g,'<br>')
															  .replace(/\r/g,'<br>')
															  .replace(/\n/g,'<br>')+'</span>';
							//시간 추출 - 공백을 기준으로 날짜,시간 분리하면 1번 인덱스에 저장됨
							output += '<div class="align-right">'+item.chat_date.split(' ')[1]+'</div>';
							output += '</div>';
							output += '</div><div class="space-clear"></div>';
							output += '</div>';
						}
						
						//문서 객체 추가
						$('#chatting_message').append(output);
						//스크롤을 하단에 위치시킴
						$('#chatting_message').scrollTop($('#chatting_message')[0].scrollHeight);
					});
				}else{
					alert('채팅 메시지 읽기 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	}
	
	
	//메시지 입력 후 enter 이벤트 처리
	$('#message').keydown(function(event){
		//enter(13) 클릭 시 전송, shift+enter 클릭 시 줄바꿈
		if(event.keyCode == 13 && !event.shiftKey){
			$('#detail_form').trigger('submit');
		}
	});
	
	
	//채팅 등록
	$('#detail_form').submit(function(event){
		if($('#message').val().trim() == ''){
			alert('메시지를 입력하세요!');
			$('#message').val('').focus();
			return false;
		}
		if($('#message').val().length > 1333){
			alert('메시지는 1333까지만 입력 가능합니다.');
			return false;
		}
		
		let form_data = $(this).serialize();
		//서버와 통신
		$.ajax({
			url:'../talk/writeTalk.do',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 작성할 수 있습니다.');
				}else if(param.result == 'success'){
					//폼 초기화
					$('#message').val('').focus();
					selectMsg();
				}else{
					alert('채팅 등록 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		
		//ajax통신을 위해 기본 이벤트 제거
		event.preventDefault();
	});	
	
	
	//최초 호출
	selectMsg();
			
});