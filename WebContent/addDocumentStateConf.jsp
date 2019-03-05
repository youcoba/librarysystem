<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>内容確認｜新規資料の追加</title>
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

	<h2>新規資料の追加 内容確認</h2>
	<hr>
	<p>
		下記の内容で追加します。よろしければ下にある「追加」ボタンを押してください。<br>
		内容を修正する場合は「戻る」ボタンを押して下さい。
	</p>
	<h3>
		ISBN番号：${adddocument.bookISBN }
	</h3>
	<h3>
		冊数：${number }冊
	</h3>	
	<h3>
		入荷年月日：${adddocument.stockDay }
	</h3>
	<h3>	
		備考：${adddocument.stateComment }
	</h3>
	<button type="button" onclick="history.back()" class="btn-flat-border">戻る</button>
	<form action="/slibsys/AddDocumentServlet" method="post">
		<p>
		<input type="submit" value="追加" class="btn-flat-border">
		</p>
		<p>
		<input type="hidden" name="action" value="comp">
		</p>
	</form>
	

	<jsp:include page="/footer.jsp" />
</body>
</html>