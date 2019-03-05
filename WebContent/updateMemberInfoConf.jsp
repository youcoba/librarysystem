<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>内容確認｜会員情報の更新</title>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+JP:400,700"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lato:400,900"
	rel="stylesheet">
<link href="/slibsys/styles.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/header.jsp" />

	<h2>会員の情報の更新 内容確認</h2>
	<hr>
	<p>
		次の内容で更新してよろしければ下にある「変更する」ボタンを押してください。<br>
		内容を修正する場合は「戻る」ボタンを押して下さい。
	</p>
	<p>
		ID:&emsp;${updatemember.id }
	</p>
	<p>	
		姓：&emsp;${updatemember.familyName } 
	</p>
	<p>	
		名：&emsp;${updatemember.lastName }
	</p>
	<p>	
		 郵便番号：&emsp;〒${updatemember.postal }
	</p>
	<p> 
		 住所：&emsp;${updatemember.address }
	</p>
	<p>
		 電話番号：&emsp;${updatemember.tel }
	</p>
	<p>
		メールアドレス：&emsp;${updatemember.email }
	</p>
	<p>	
		生年月日：&emsp;${updatemember.birthday }
	</p>
	<br>
	<button type="button" onclick="history.back()" class="btn-flat-border">戻る</button><br><br>
	<form action="/slibsys/UpdateMemberServlet">
	<input type="submit" value="更新する" class="btn-flat-border">
	<input type="hidden" name="action" value="comp">
	</form>

	<jsp:include page="/footer.jsp" />

</body>
</html>