<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新完了｜会員情報の更新</title>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+JP:400,700"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lato:400,900"
	rel="stylesheet">
<link href="/slibsys/styles.css" rel="stylesheet">
</head>
<body>
<body>
	<jsp:include page="/header.jsp" />

	<h2>会員情報の更新 更新完了</h2>
	<hr>
	<p>下記の内容で会員を更新しました。</p>
	
	<h3>
		会員ID：${showmember.id }
	</h3>
	<p>
		姓：${showmember.familyName } &emsp;名：${showmember.lastName }
	</p>
	<p> 
		郵便番号：〒${showmember.postal }
	</p>
	<p> 
		住所：${showmember.address }
	</p>
	<p> 	
		電話番号：${showmember.tel }
	</p>
	<p> 
		メールアドレス：${showmember.email }
	</p>
	<p> 	
		生年月日：${showmember.birthday }
	</p>

	<jsp:include page="/footer.jsp" />

</body>
</html>