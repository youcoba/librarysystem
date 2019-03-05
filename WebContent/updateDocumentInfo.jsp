<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料目録を変更｜資料情報の変更</title>
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

	<h2>資料情報の変更 資料目録の変更</h2>
	<hr>

	<form action="/slibsys/SearchDocumentServlet" method="post">
		<c:forEach items="${infos }" var="info">
			<p>ISBN番号：${info.bookISBN }</p>
			<p>
				資料名：<input type="text" name="bookInfoName"
					value="${info.bookInfoName }" size="" maxlength="">
			</p>

			<p>
				分類コード：<input type="text" name="categoryCode"
					value="${info.categoryCode }" size="" maxlength="">
			</p>
			<p>
				著者：<input type="text" name="bookInfoAuthor"
					value="${info.bookInfoAuthor }" size="" maxlength="">
			</p>
			<p>
				出版日：<input type="text" name="publishDayY" value="${publishDay[0]}"
					size="" maxlength="">年<input type="text" name="publishDayM"
					value="${publishDay[1]}" size="" maxlength="">月：<input
					type="text" name="publishDayD" value="${publishDay[2]}" size=""
					maxlength="">日
			</p>

			<p>
				出版社：<input type="text" name="publisher" value="${info.publisher }"
					size="" maxlength="">
			</p>


		</c:forEach>

		<p>
			<input type="submit" value="確認画面へ" size="" class="btn-flat-border">
			<input type="hidden" name="action" value="updateInfoConf">
		</p>

	</form>
	<jsp:include page="/footer.jsp" />
</body>
</html>