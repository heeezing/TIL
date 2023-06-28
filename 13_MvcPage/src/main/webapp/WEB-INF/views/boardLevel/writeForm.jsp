<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글게시판 글쓰기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		//이벤트 연결
		$('#write_form').submit(function(){
			if($('#subject').val().trim()==''){
				alert('제목을 입력하세요!');
				$('#subject').val('').focus();
				return false;
			}
			if($('#content').val().trim()==''){
				alert('내용을 입력하세요!');
				$('#content').val('').focus();
				return false;
			}
		});
	});
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="content-main">
		<h2>답글게시판 글쓰기</h2>
		<form id="write_form" action="write.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="parent_num" value="${parent_num}">
		<input type="hidden" name="depth" value="${depth}">
			<ul>
				<li>
					<label for="subject">제목</label>
					<input type="text" name="subject" id="subject" maxlength="50">
				</li>
				<li>
					<label for="content">내용</label>
					<textarea rows="5" cols="30" name="content" id="content" maxlength="50"></textarea>
				</li>
				<li>
					<label for="image">이미지</label>
					<input type="file" name="image" id="image" accept="image/gif,image/png,image/jpeg">
				</li>				
			</ul>
			<div class="align-center">
				<input type="submit" value="등록">
				<input type="button" value="목록" onclick="location.href='list.do'">
			</div>
		</form>
	</div>
	<!-- 내용 끝 -->
</div>
</body>
</html>