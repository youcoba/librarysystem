<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>内容確認｜資料の貸出</title>
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

	<h2>資料の貸出 内容確認</h2>
	<hr>
	<p>
		下記の内容で貸出を行います。よろしければ下にある「貸出処理」ボタンを押してください。<br>
		内容を修正する場合は「戻る」ボタンを押して下さい。
	</p>
	<h4>
		会員ID：${userid}
	</h4>
		<c:forEach items="${addrental}" var="book">
		<h4>
		資料ID：${book.bookStateId}
		</h4>
	</c:forEach>
	<p><button type="button" onclick="history.back()" class="btn-flat-border">戻る</button></p>
	

	<form action="/slibsys/RentServlet" method="post">
	<p><input type="submit" value="貸出処理" class="btn-flat-border"></p>
	<input type="hidden" name="action" value="comp">
	</form>
	
	<jsp:include page="/footer.jsp" />

</body>
</html>