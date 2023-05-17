<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//전송된 데이터 인코딩 처리
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이지 모듈화</title>
<style type="text/css">
table{
	border-collapse:collapse;
	border:solid 1px #000;
	width:500px;
	margin:0 auto;
}
td{
	border:solid 1px #000;
	text-align:center;
	vertical-align:middle;
	height:40px;
}
td.td-middle{
	text-align:left;
	vertical-align:top;
	height:200px;
}
td.td-width{
	width:100px;
}
</style>
</head>
<body>
<table>
	<tr>
		<td colspan="2">
			<%-- include의 내용 안에는 html 주석, jsp 주석 전부 사용 불가--%>
		    <!-- jsp:param = 파라미터 추가 기능. request에 저장. (데이터 인코딩 필수) -->
			<jsp:include page="/ch05-actionTag/module/top.jsp">
				<jsp:param value="서울" name="company"/>
			</jsp:include>
		</td>
	</tr>
	
	<tr>
		<td class="td-middle td-width">
			<!-- 끝에 /를 넣으면 단독태그로도 사용 가능 -->
			<jsp:include page="/ch05-actionTag/module/left.jsp"/>
		</td>
		<td class="td-middle">
			<!-- 내용 부분 시작 -->
			레이아웃 1
			<!-- 내용 부분 끝 -->
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
			<jsp:include page="/ch05-actionTag/module/bottom.jsp"/>
		</td>
	</tr>
</table>
</body>
</html>