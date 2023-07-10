<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/hello.do">HelloController</a><br>
													    <!-- 파라미터명=값 -->
<a href="${pageContext.request.contextPath}/search/internal.do?query=seoul">SearchController - internal.do</a><br>
<a href="${pageContext.request.contextPath}/search/external.do">SearchController - external.do</a><br>
<a href="${pageContext.request.contextPath}/article/newArticle.do">NewArticleController</a><br>
<a href="${pageContext.request.contextPath}/cookie/make.do">CookieController - make.do</a><br>
<a href="${pageContext.request.contextPath}/cookie/view.do">CookieController - view.do</a><br>
<a href="${pageContext.request.contextPath}/search/main.do">GameSearchController</a><br>
<a href="${pageContext.request.contextPath}/account/create.do">CreateAccountController</a><br>
<a href="${pageContext.request.contextPath}/login/login.do">LoginController</a><br>
<a href="${pageContext.request.contextPath}/report/submitReport.do">SubmitReportController</a><br>
<a href="${pageContext.request.contextPath}/member/write.do">MemberWriteController</a><br>
<a href="${pageContext.request.contextPath}/file.do">DownloadController</a><br>
<a href="${pageContext.request.contextPath}/pageRanksExcel.do">/pageRanksExcel.do(엑셀)</a><br>
<a href="${pageContext.request.contextPath}/pageJsonReport.do">/pageJsonReport.do(JSON)</a><br>
</body>
</html>