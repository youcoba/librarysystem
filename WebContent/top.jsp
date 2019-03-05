<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップメニュー</title>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+JP:400,700"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lato:400,900"
	rel="stylesheet">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<link href="/slibsys/styles.css" rel="stylesheet">
</head>
<body>
	<h1> 新宿図書館 図書管理システム</h1>
	<h2>トップメニュー</h2>
	<div class="container">
		<div class="member">
			<h3>会員管理</h3>
			<a class="btn-flat-border" href="/slibsys/addMember.jsp">会員の登録</a> <a
				class="btn-flat-border" href="/slibsys/searchMember.jsp">会員の検索</a>
		</div>
		<div class="books">
			<h3>資料管理</h3>
			<a class="btn-flat-border" href="/slibsys/addDocument.jsp">新規資料の追加</a>
			<a class="btn-flat-border" href="/slibsys/searchDocument.jsp">資料の検索</a>
		</div>
		<div class="rental">
			<h3>貸出／返却</h3>
			<a class="btn-flat-border" href="/slibsys/rent.jsp">資料の貸出</a> <a
				class="btn-flat-border" href="/slibsys/searchRent.jsp">貸出履歴の検索</a> <a
				class="btn-flat-border" href="/slibsys/return.jsp">資料の返却</a>
		</div>
	</div>


	<jsp:include page="/footer.jsp" />

</body>
</html>