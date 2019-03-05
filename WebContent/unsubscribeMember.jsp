<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>内容確認｜会員の退会</title>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+JP:400,700"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lato:400,900"
	rel="stylesheet">
<link href="/slibsys/styles.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/header.jsp" />

	<h2>会員の退会 内容確認</h2>
	<hr>
	<p>
		次の会員の退会処理を行います。よろしければ「退会処理」ボタンを押して下さい。
	</p>
	<p>
		会員ID:&emsp;${show.id }
	</p>
	<p>	
		姓：&emsp;${show.familyName } 
	</p>
	<p>	
		名：&emsp;${show.lastName }
	</p>
	<p>
		郵便番号：&emsp;〒${show.postal }
	</p>
	<p>
		住所：&emsp;${show.address }
	</p>
	<p>	
		電話番号：&emsp;${show.tel }
	</p>
	<p>
		メールアドレス：&emsp;${show.email }
	</p>
	<p>
		生年月日：&emsp;${show.birthday }
	</p>
	<br>
	<form action="/slibsys/UnsubscribeMemberServlet" method="post">
	<button type="button" onclick="history.back()" class="btn-flat-border">戻る</button><br><br>
	<input type="submit" value="退会処理" class="btn-flat-border">
	<input type="hidden" name="action" value="comp">
	</form>
	<jsp:include page="/footer.jsp" />

</body>
</html>