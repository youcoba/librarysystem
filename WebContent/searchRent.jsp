<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>貸出の検索</title>
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

	<h2>貸出の検索</h2>
	<hr>
	<p>
		検索したい貸出の会員ID、資料IDを入力してください。<br>何も入力せずに「検索」ボタンを押すと、全ての貸出履歴が表示されます。
	</p>
	<p>
	<form action="/slibsys/SearchRentServlet" method="post">
		<p>
		会員ID：<input type="text" name="userId" size="" maxlength="">
		</p>
		<p>
		資料ID：<input type="text" name="bookStateId" size="" maxlength=""><br>
		</p>
		<p>
		<input type="submit" value="検索" class="btn-flat-border"><input type="hidden"
			name="action" value="search">
		</p>
	</form>

	<jsp:include page="/resultSearchRent.jsp" />

	<jsp:include page="/footer.jsp" />

</body>
</html>