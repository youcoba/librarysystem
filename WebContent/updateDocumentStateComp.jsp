<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>変更完了｜資料台帳を変更</title>
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

	<h2>資料情報の変更 資料台帳を変更 変更完了</h2>
	<hr>
	<p>下記の内容で資料情報を変更しました。</p>
	<p>
		資料ID：${id}<br>ISBN番号：${isbn }<br>
		資料名：${updatestate.bookInfoName}<br>入荷年月日：${updatestate.stockDay}<br>廃棄年月日：${updatestate.disposalDay }<br>備考：${updatestate.stateComment}
	</p>
	<jsp:include page="/footer.jsp" />

</body>
</html>