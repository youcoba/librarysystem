<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>追加完了｜新規資料の追加</title>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+JP:400,700"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lato:400,900"
	rel="stylesheet">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<link href="/slibsys/styles.css" rel="stylesheet">
</head>
<body>
<body>
	<jsp:include page="/header.jsp" />
	<h2>新規資料の追加 資料台帳に追加 追加完了</h2>
	<hr>
	<p>下記の内容で資料を追加しました。</p>
	<table>
		<tr>
		<td style="width: 100px;">資料ID</td>
		<td style="width: 100px;">ISBN番号</td>
		<td style="width: 100px;">入荷年月日</td>
		<td style="width: 300px;">備考</td>
		</tr>
		
		<c:forEach items="${addbookstate }" var="book">
		<tr><td style="width: 100px; font-size: 18px;">${book.bookStateId }</td>
		<td style="width: 100px; font-size: 18px;">${book.bookISBN }</td>
		<td style="width: 100px; font-size: 18px;">${book.stockDay }</td>
		<td style="width: 300px; font-size: 18px;">${book.stateComment }</td>
		</tr>
		</c:forEach>
	
	</table>

	<jsp:include page="/footer.jsp" />
</body>
</html>
