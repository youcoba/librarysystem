<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>退会完了｜会員の退会</title>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+JP:400,700"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lato:400,900"
	rel="stylesheet">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<link href="/slibsys/styles.css" rel="stylesheet">
</head>
<body>
<body>
	<jsp:include page="/header.jsp" />

	<h2>会員の退会 退会完了</h2>
	<hr>
	<p>下記の会員の退会処理が完了しました。</p>
	<p>
		ID：${unsubscribed.id }
	</p>
	<p>	
		姓：${unsubscribed.familyName } 名：${unsubscribed.lastName }
	</p>
	<p>		
		郵便番号：〒${unsubscribed.postal }
	</p>
	<p>	
		住所：${unsubscribed.address }
	</p>
	<p>		
		電話番号：${unsubscribed.tel }
	</p>
	<p>		
		メールアドレス：${unsubscribed.email }
	</p>
	<p>		
		生年月日：${unsubscribed.birthday }
	</p>
	<p>		
		退会年月日：${unsubscribed.unsubscribeday }
	</p>

	<!-- 余裕があったら備考入力出来るようにする -->

	<jsp:include page="/footer.jsp" />

</body>
</html>