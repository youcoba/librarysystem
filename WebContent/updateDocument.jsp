<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料情報の変更</title>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+JP:400,700"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lato:400,900"
	rel="stylesheet">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
	rel="stylesheet">
<link href="/slibsys/styles.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/header.jsp" />

	<h2>資料情報の変更</h2>
	<hr>
	<p>変更したいデータを選択してください</p>

	<form action="/slibsys/SearchDocumentServlet" method="post">
		<input type="submit" value="資料台帳" class="btn-flat-border"><input
			type="hidden" name="action" value="showState">
	</form>
	<br>
	<form action="/slibsys/SearchDocumentServlet" method="post">
		<input type="submit" value="資料目録" class="btn-flat-border"><input
			type="hidden" name="action" value="showInfo">
	</form>

	<jsp:include page="/footer.jsp" />

</body>
</html>