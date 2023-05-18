window.onload=function(){
	let myForm = document.getElementById('myForm');
	//이벤트 연결
	myForm.onsubmit=function(){
		let items = document.querySelectorAll('input[type="text"],input[type="password"],textarea');
		for(let i=0 ; i<items.length ; i++){
			if(items[i].value.trim()==''){
				let label = document.querySelector('label[for="'+items[i].id+'"]');
				alert(label.textContent + ' 항목은 필수 입력입니다.');
				items[i].value = ''; //공백 입력 시 공백 제거
				items[i].focus();
				return false;
			}
		}
	};
};