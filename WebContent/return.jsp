<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料の返却</title>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+JP:400,700"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lato:400,900"
	rel="stylesheet">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<link href="/slibsys/styles.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/header.jsp" />

	<h2>資料の返却</h2>
	<hr>
	<p>
		返却を行う会員の会員IDを入力し「検索」ボタンを押すと<br> 会員の未返却の貸出情報が表示されます。<br>
		何も入力せずに「検索」ボタンを押すと、全ての会員の未返却の貸出履歴が表示されます。
	</p>
	会員ID：
	<form action="/slibsys/ReturnServlet" method="post">
		<input type="text" name="userId" size="" maxlength=""><input
			type="submit" value="検索" class="btn-flat-border"><input type="hidden" name="action"
			value="search">
	</form>

	<jsp:include page="/resultSearchReturn.jsp" />

	<jsp:include page="/footer.jsp" />

</body>
</html>