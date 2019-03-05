<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員の登録 | 内容確認</title>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+JP:400,700"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lato:400,900"
	rel="stylesheet">
<link href="/slibsys/styles.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/header.jsp" />

	<h2>会員の登録 内容確認</h2>
	<hr>
	<p>
		次の内容で登録してよろしければ下にある「登録する」ボタンを押してください。<br>
		内容を修正する場合は「戻る」ボタンを押して下さい。
	</p>
	<p>
		姓：${addmember.familyName } &emsp;名：${addmember.lastName }
	</p>
	<p>
		郵便番号：〒${addmember.postal }
	</p>
	<p>
		住所：${addmember.address }
	</p>
	<p>
		電話番号：${addmember.tel }
	</p>
	<p>	
		メールアドレス：${addmember.email }
	</p>
	<p>
		生年月日：${addmember.birthday }
	</p>
	<button type="button" onclick="history.back()" class="btn-flat-border">戻る</button><br><br>
	<form action="/slibsys/AddMemberServlet" method="post">
		<input type="submit" value="登録する" class="btn-flat-border"> 
		<input type="hidden"	name="action" value="comp">
	</form>

	<jsp:include page="/footer.jsp" />

</body>
</html>