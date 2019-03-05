<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員の登録 | 登録完了</title>
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

	<h2>会員の登録 登録完了</h2>
	<hr>
	<p>下記の内容で会員を登録しました。</p>
		<c:forEach items="${addmember }" var="member">
		<h3>
			会員ID：${member.id }
		</h3>
		<p>
			姓：${member.familyName } &emsp;名：${member.lastName }
		</p>
		<p>
			郵便番号：〒${member.postal }
		</p>
		<p>
			住所：${member.address }
		</p>
		<p>	
			電話番号：${member.tel }
		</p>
		<p>	
			メールアドレス：${member.email }
		</p>
		<p>
			生年月日：${member.birthday }
		</p>
	</c:forEach>

	<jsp:include page="/footer.jsp" />

</body>
</html>