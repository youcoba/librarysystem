<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>内容確認｜資料台帳を変更</title>
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

	<h2>資料情報の変更 資料台帳を変更 内容確認</h2>
	<hr>
	<p>
		下記の内容で資料情報を変更します。よろしければ下にある「変更する」ボタンを押してください。<br>
		内容を修正する場合は「戻る」ボタンを押して下さい。
	</p>

	<p>
		資料ID：${id}<br>ISBN番号：${isbn }<br>
		資料名：${updatestate.bookInfoName}<br>入荷年月日：${updatestate.stockDay}<br>廃棄年月日：${updatestate.disposalDay }<br>備考：${updatestate.stateComment}
	</p>
	<button type="button" onclick="history.back()" class="btn-flat-border">戻る</button>
	<form action="/slibsys/SearchDocumentServlet" method="post">
		<input type="submit" value="変更する" class="btn-flat-border"> <input
			type="hidden" name="action" value="updateState">
	</form>

	<jsp:include page="/footer.jsp" />

</body>
</html>