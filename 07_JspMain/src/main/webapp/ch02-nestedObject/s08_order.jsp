<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 내역</title>
</head>
<body>
<%--
 구매 금액이 30만원 미만이면 배송비 5000원 추가
 [출력 예시]
 이름 : 
 배송 희망일 : 
 구입 내용 : 신발, 식사권 
 배송비 : 
 총 구매 비용(배송비 포함) : (배열형태 혹은 hashmap사용)
--%>
<%	
	//전송된 데이터 인코딩 처리
	request.setCharacterEncoding("utf-8");

	//가격 정보
	HashMap<String,Integer> map = new HashMap<String,Integer>();
	map.put("가방",200000);
	map.put("신발",100000);
	map.put("옷",50000);
	map.put("식사권",150000);
	
	//배송비
	int delivery_fee = 5000;
%>

이름 : <%= request.getParameter("name") %><br>
배송 희망일 : <%= request.getParameter("order_date") %><br>
구입 내용 : 
<%
	String[] items = request.getParameterValues("item");
	int sum = 0;
	if(items!=null){
		for(int i=0 ; i<items.length ; i++){
			//가격 누적
			sum += map.get(items[i]);
			//상품 표시
			if(i>0) out.print(",");
%>
			<%= items[i] %>
<%
		}//end of for
		
		//배송비 포함 여부 체크
		if(sum<300000) sum += delivery_fee;
		else delivery_fee = 0;
%>
		<br>
		배송비 : <%= String.format("%,d원", delivery_fee) %><br>
		총 구매 비용 (배송비 포함) : <%= String.format("%,d원", sum) %>
<%		
	}//end of if
%>
</body>
</html>