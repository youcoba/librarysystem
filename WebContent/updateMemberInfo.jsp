<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報の更新</title>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+JP:400,700"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lato:400,900"
	rel="stylesheet">
<link href="/slibsys/styles.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/header.jsp" />

	<h2>会員情報の更新</h2>
	<hr>
<form action="/slibsys/UpdateMemberServlet" method="post">
	   <h4>会員ID:${before.id }</h4> 
<p>	
		姓：<input type="text" name="familyName" value="${before.familyName }" size="" maxlength="">&emsp;
		名：<input type="text" name="lastName" value="${before.lastName }" size="" maxlength="" >
</p>
<p>
		郵便番号(ハイフン"‐"不要、7桁数字)：<input type="text" name="postal" value="${before.postal }" size="" maxlength="">
</p>
<p>
		住所：<input type="text" name="address" value="${before.address }" size="" maxlength="" style="width: 600px;">
</p>
<p>
		電話番号：<input type="text" name="tel" value="${before.tel }" size="" maxlength=""> 
</p>
<p>
		メールアドレス：<input type="text" name="email" value="${before.email }" size="" maxlength="" style="width:400px;">
</p>
<p>
		生年月日：<input type="text" name="byear" value="${birthday[0] }" size="5" maxlength="">&emsp;年&emsp;<input
			type="text" name="bmonth" value="${birthday[1] }" size="5" maxlength="">&emsp;月&emsp;<input
			type="text" name="bdate" value="${birthday[2] }" size="5" maxlength="">&emsp;日
</p>
<br>
		<button type="button" onclick="history.back()" class="btn-flat-border">戻る</button><br><br>
		<input type="submit" value="確認画面へ" size="" class="btn-flat-border">
		<input type="hidden" name="action" value="conf">
</form>
	

	<jsp:include page="/footer.jsp" />

</body>
</html>