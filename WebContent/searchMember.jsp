<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員の検索</title>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+JP:400,700"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lato:400,900"
	rel="stylesheet">
<link href="/slibsys/styles.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/header.jsp" />

	<h2>会員の検索</h2>
	<p>
		検索したい会員のメールアドレスを入力してください。<br>何も入力せずに「検索」ボタンを押すと全会員の情報が表示されます。
	</p>
	<form action="/slibsys/SearchMemberServlet" method="post">
		
		メールアドレス：<input type="text" name="email" value="" size="" maxlength="" style="width:300px;">
		<input type="submit" value="検索" class="btn-flat-border">
	</form>
	<jsp:include page="/resultSearchMember.jsp" />

	<jsp:include page="/footer.jsp" />

</body>
</html>