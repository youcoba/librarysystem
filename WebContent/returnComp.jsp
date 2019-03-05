<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>返却完了｜資料の返却</title>
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

	<h2>資料の返却 返却完了</h2>
	<hr>
	<p>下記の資料の返却が完了しました。</p>
	<table>
		<tr>
			<td>会員ID</td>
			<td>貸出ID</td>
			<td>資料ID</td>
			<td>貸出年月日</td>
			<td>返却年月日</td>
			<td>返却期日</td>
			<td>備考</td>
		</tr>
		<c:forEach items="${infos}" var="info">
			<tr>
				<td>${info.userId }</td>
				<td>${info.rentalId }</td>
				<td>${info.bookStateId }</td>
				<td>${info.rentalRent }</td>
				<td>${info.rentalReturn }</td>
				<td>${info.rentalDeadline }</td>
				<td>${info.comment }</td>
		</c:forEach>
	</table>
	<p>
	<input type="button" onclick="location.href='/slibsys/return.jsp'" class="btn-flat-border" value="戻る">
	</p>
	<jsp:include page="/footer.jsp" />

</body>
</html>