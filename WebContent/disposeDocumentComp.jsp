<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>廃棄完了｜資料の廃棄</title>
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
<body>
	<jsp:include page="/header.jsp" />

	<h2>資料の廃棄 廃棄完了</h2>
	<hr>
	<p>下記の資料の廃棄処理が完了しました。</p>
	<c:forEach items="${infos}" var="info">
		<p>
			資料ID：${info.bookStateId }<br>ISBN番号：${info.bookInfoIsbn }<br>分類コード：${info.categoryCode }<br>分類${info.categoryName }
			<br>資料名：${info.bookInfoName }<br> 著者：${info.bookInfoAuthor }
			<br>出版社：${info.publisher } <br>出版日：${info.publishDay }<br>入荷年月日：${info.stockDay }<br>廃棄年月日：${info.disposalDay }<br>備考:${info.comment }
		</p>
	</c:forEach>
	
	<button type="button" onclick="history.back()" class="btn-flat-border">戻る</button>
	<jsp:include page="/footer.jsp" />

</body>
</html>