<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料台帳を変更｜資料情報の変更</title>
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

	<h2>資料情報の変更 資料台帳の変更</h2>
	<hr>

	<form action="/slibsys/SearchDocumentServlet" method="post">
		<p>ID：${id }</p>


		<c:forEach items="${infos }" var="info">
			<p>ISBN番号：${info.bookISBN }</p>
			<p>
				資料名：${info.bookInfoName }<input type="hidden" name="bookInfoName"
					value="${info.bookInfoName }">
			</p>
			<p>
				入荷年月日：<input type="text" name="stockDayY" value="${stockDay[0] }"
					size="" maxlength="">年<input type="text" name="stockDayM"
					value="${stockDay[1] }" size="" maxlength="">月：<input
					type="text" name="stockDayD" value="${stockDay[2] }" size=""
					maxlength="">日
			</p>
			<p>
				廃棄年月日：<input type="text" name="disposalDayY"
					value="${disposalDay[0] }" size="" maxlength="">年<input
					type="text" name="disposalDayM" value="${disposalDay[1] }" size=""
					maxlength="">月：<input type="text" name="disposalDayD"
					value="${disposalDay[2] }" size="" maxlength="">日
			</p>

			<p>
				備考：<input type="text" name="stateComment"
					value="${info.stateComment }" size="" maxlength="">
			</p>
		</c:forEach>
		<p>
			<input type="submit" value="確認画面へ" size="" class="btn-flat-border">
			<input type="hidden" name="action" value="updateStateConf">
		</p>
	</form>

	<jsp:include page="/footer.jsp" />

</body>
</html>