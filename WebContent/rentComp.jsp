<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>貸出完了｜資料の貸出</title>
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

	<h2>資料の貸出 貸出完了</h2>
	<hr>
	<p>下記の内容で貸出完了しました。</p>
	<h4>
	会員ID：${userid}
	</h4>
	<table style="width: 500px;">
	<tr>
		<td style="width: 100px;">貸出ID</td>
		<td style="width: 100px;">資料ID</td>
		<td style="font-weight: bold; font-size: 20px;">返却期日</td>
	</tr>
	<c:forEach items="${addrental2}" var="add">
	<tr>
		<td>
		${add.rentalId} 
		</td>
		<td>
		${add.bookStateId} 
		</td>
		<td style="font-weight: bold; font-size: 20px;">
		${add.returnDeadLine}
		</td>
	<tr>
	</c:forEach>
	</table>

	<jsp:include page="/footer.jsp" />

	<!-- 余裕があれば借りた分だけ表示する
今は5行出て来る -->

</body>
</html>