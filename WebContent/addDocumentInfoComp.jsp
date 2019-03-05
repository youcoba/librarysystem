<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>追加完了｜資料目録の追加</title>
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
	<h2>新規資料の追加 資料目録に追加 追加完了</h2>
	<hr>
	<p>下記の内容で資料を追加しました。</p>
	<h3>
		資料名：${infocomp.bookInfoName }
	</h3>
	<h3>	
		分類コード：${infocomp.categoryCode }
	</h3>
	<h3>	
		著者：${infocomp.bookInfoAuthor } 
	</h3>
	<h3>	
		出版社：${infocomp.publisher } 
	</h3>
	<h3>	
		出版日：${infocomp.publishDay }
	</h3>
	<h3>	
		備考：${infocomp.infoComment }
	</h3>

	<jsp:include page="/footer.jsp" />
</body>
</html>