window.onload=function(){
	let myForm = document.getElementById('myForm');
	//이벤트 연결
	myForm.onsubmit=function(){
		let products = document.querySelectorAll('input[type="text"],input[type="number"]');
		for(let i=0 ; i<products.length ; i++){
			if(products[i].value.trim()==''){
				let label = document.querySelector('label[for="'+products[i].id+'"]');
				alert(label.textContent + ' 항목은 필수 입력입니다.');
				products[i].value = ''; //공백 입력 시 공백 제거
				products[i].focus();
				return false;
			}
		}
	};
};