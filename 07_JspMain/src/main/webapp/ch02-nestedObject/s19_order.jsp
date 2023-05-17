<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Set" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문서 확인</title>
</head>
<body>
<%--
[가격 정보] - Hashmap, 배열 선택 사용
짜장면 : 4,000원
짬뽕 : 5,000원
볶음밥 : 6,000원

[출력 예시]
짜장면 2개
짬뽕 1개
총 지불 금액 : 13,000원
 --%>
 
<%
	//데이터 인코딩
	request.setCharacterEncoding("utf-8");

	//가격 저장
	HashMap<String,Integer> map = new HashMap<String,Integer>();
	map.put("짜장면",4000);
	map.put("짬뽕",5000);
	map.put("볶음밥",6000);
	
	String[] foodName = {"짜장면","짬뽕","볶음밥"};
	int[] price = {4000,5000,6000};
	
		
	
	Iterator<Integer> keys = map.keySet().iterator();
		
	while(keys.hasNext()) {
	Integer key = keys.next(); //key반환
	System.out.println(key + "," + map.get(key));

	}
	

%>

	
	
	
	
	
	
	
	
	
	
</body>
</html>